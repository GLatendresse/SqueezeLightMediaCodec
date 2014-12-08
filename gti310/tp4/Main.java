package gti310.tp4;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Franï¿½ois Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		//PPM or SLZ or extension not supported? 
		String extension = "";
		String fileName = args[0];
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
		    extension = fileName.substring(index+1);
		}
		
		//PPM
		if (extension.equals("ppm")){
			//Ask the user for the quality factor and compressed filename
			JFrame frameQuality = new JFrame();
			int qualityFactor = Integer.parseInt(JOptionPane.showInputDialog(frameQuality, "Enter the quality factor:"));
			JFrame frameFileName = new JFrame();
			String savedFileName = JOptionPane.showInputDialog(frameFileName, "Enter the SZL filename:");
			
			
			//Image convert (RGB to YCbCr) + read file
			int[][][] imageYCbCr = Convert.extractImageRGB(PPMReaderWriter.readPPMFile(fileName));
			
			//Separate the image in 8x8 pixel blocks and stock them into a container EightXEightBlock[color space][i localisation][j localisation]
			EightXEightBlock[][][] eightXEightBlocksContainer = EightXEightBlocksMethods.imageToEightXEightBlocks(imageYCbCr);
			
			//Image pretraitment
			for (int i=0; i<COLOR_SPACE_SIZE;i++)
				for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
					for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
						int[][] quantifiedEightxEightBlock = Quantization.quantizationOperation(DCT.dCTOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()), i, qualityFactor);
						int[][] zigzagedEightxEightBlock = Zigzag.zigzagOperation(quantifiedEightxEightBlock);
						EightXEightBlock eightXEightBlockResult = new EightXEightBlock(zigzagedEightxEightBlock);
						eightXEightBlocksContainer[i][j][k] = eightXEightBlockResult;
						
					}
			
			//DC coefficients writer
			int[] encodedDCCoefficients = DPCM.dPCMOperation(eightXEightBlocksContainer);
			for(int i = 0; i < encodedDCCoefficients.length; i++)
				Entropy.writeDC(encodedDCCoefficients[i]);
			
			//AC coefficients writer
			for (int i=0; i<COLOR_SPACE_SIZE;i++)
				for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
					for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
						
						int[][] rLCCoefficients = RLC.rLCOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix());
						for(int u = 0; u < rLCCoefficients.length; u++)
							Entropy.writeAC(rLCCoefficients[u][RLC.RUNLENGTH], rLCCoefficients[u][RLC.VALUE]);
						
					}
			
			//Save compressed file
			SZLReaderWriter.writeSZLFile(savedFileName, imageYCbCr[0].length, imageYCbCr[0][0].length, qualityFactor);
			System.out.println("Encoding done");
			
		}else{
			
			//SLZ
			if (extension.equals("szl")){
				//Ask the user for the decompressed filename
				JFrame frameFileName = new JFrame();
				String savedFileName = JOptionPane.showInputDialog(frameFileName, "Enter the PPM filename:");
				
				
				//Read compressed file
				int[] sZLHeader = SZLReaderWriter.readSZLFile(fileName);
				int qualityFactor = sZLHeader[3];
				int numberOfEightxEightBlocks = ((sZLHeader[0]*sZLHeader[1])/(BLOCK_SIZE*BLOCK_SIZE))*COLOR_SPACE_SIZE;
				
				//initialize container with zeros
				EightXEightBlock[][][] eightXEightBlocksContainer = new EightXEightBlock[COLOR_SPACE_SIZE][sZLHeader[0]/BLOCK_SIZE][sZLHeader[1]/BLOCK_SIZE];
				
				for (int i=0; i<COLOR_SPACE_SIZE;i++)
					for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
						for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
							
							int[][] initializer = new int[BLOCK_SIZE][BLOCK_SIZE];
							
							for (int u=0; u<BLOCK_SIZE;u++)
								for (int v =0;v<BLOCK_SIZE;v++)
									initializer[u][v] = 0;
							
							eightXEightBlocksContainer[i][j][k] = new EightXEightBlock(initializer);
							
						}
							
							
						
				//DC coefficients reader
				int[] encodedDCCoefficients = new int[numberOfEightxEightBlocks];
				for(int i = 0; i < encodedDCCoefficients.length; i++)
					encodedDCCoefficients[i] = Entropy.readDC();
				DPCM.iDPCMOperation(encodedDCCoefficients, eightXEightBlocksContainer);
				
				//AC coefficients reader
				ArrayList<int[]> rLCCoefficients = new ArrayList<int[]>();
				
				int[] rLCCoefficient = {1,1};
				for (int i=0; i<COLOR_SPACE_SIZE;i++)
					for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
						for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
							
							
							while(rLCCoefficient[RLC.RUNLENGTH] != 0 && rLCCoefficient[RLC.VALUE] != 0){
								
								rLCCoefficient = Entropy.readAC();
								rLCCoefficients.add(rLCCoefficient);
								
								
							}
							
							int[][] rLCCoefficientsArray = new int[rLCCoefficients.size()][2];
							for(int u = 0; u < rLCCoefficientsArray.length; u++){
								
								rLCCoefficientsArray[u][RLC.RUNLENGTH] = rLCCoefficients.get(u)[RLC.RUNLENGTH];
								rLCCoefficientsArray[u][RLC.VALUE] = rLCCoefficients.get(u)[RLC.VALUE];
								
							}
							
							
							
							RLC.iRLCOperation(rLCCoefficientsArray, eightXEightBlocksContainer[i][j][k]);
							
							//image post traitment
							int[][] zigzagedEightxEightBlock = eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix();
							int[][] unzigzagedEightxEightBlock = Zigzag.unzigzagOperation(zigzagedEightxEightBlock);
							int[][] unquantifiedEightxEightBlock = DCT.inverseDCTOperation(Quantization.deQuantizationOperation(unzigzagedEightxEightBlock, i, qualityFactor));
							EightXEightBlock eightXEightBlockResult = new EightXEightBlock(unquantifiedEightxEightBlock);
							eightXEightBlocksContainer[i][j][k] = eightXEightBlockResult;
							
							
						}
				
				//Fusion of the 8x8 blocks
				int[][][] newImageYCbCr = EightXEightBlocksMethods.eightXEightBlocksToImage(eightXEightBlocksContainer);
				
				//Save PPM file and conversion YcbCr -> RGB
				PPMReaderWriter.writePPMFile(savedFileName, Convert.extractImageYCbCr(newImageYCbCr));
				System.out.println("Decoding done");
			
			//Error message
			}else{
				JOptionPane.showMessageDialog(null, "Désolé, le format n'est pas supporté.");
				System.exit(0);
			}
		}
		
		
	}
	
}
