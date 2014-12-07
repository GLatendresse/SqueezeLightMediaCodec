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
		
		int[][][] imageRGB = { { 
			  { 234, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 34, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 98, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,}, 
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 150, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,}},
			  
			{ { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 78, 4,},
			  { 1, 236, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
			  { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,}, 
			  { 10, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 144 }, 
			  { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,}},

			{ { 255, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,},
		      { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,}, 
		      { 1, 88, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 110, 2 }, 
		      { 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4,} } }; 
		
		
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
			String savedFileName = JOptionPane.showInputDialog(frameFileName, "Enter the SLZ filename:");
			
			
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
			if (extension.equals("slz")){
				//Ask the user for the decompressed filename
				JFrame frameFileName = new JFrame();
				String savedFileName = JOptionPane.showInputDialog(frameFileName, "Enter the PPM filename:");
				
				
				//Read compressed file
				int[] sLZHeader = SZLReaderWriter.readSZLFile(fileName);
				int qualityFactor = sLZHeader[3];
				int numberOfEightxEightBlocks = ((sLZHeader[0]*sLZHeader[1])/(BLOCK_SIZE*BLOCK_SIZE))*COLOR_SPACE_SIZE;
				
				//initialize container with zeros
				EightXEightBlock[][][] eightXEightBlocksContainer = new EightXEightBlock[COLOR_SPACE_SIZE][sLZHeader[0]/BLOCK_SIZE][sLZHeader[1]/BLOCK_SIZE];
				int[][] initializer = new int[BLOCK_SIZE][BLOCK_SIZE];
				
				for (int i=0; i<BLOCK_SIZE;i++)
					for (int j =0;j<BLOCK_SIZE;j++)
						initializer[i][j] = 0;
						
				EightXEightBlock eightXEightBlockInitializer = new EightXEightBlock(initializer);
				
				for (int i=0; i<COLOR_SPACE_SIZE;i++)
					for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
						for (int k=0;k<eightXEightBlocksContainer[0].length;k++)
							eightXEightBlocksContainer[i][j][k] = eightXEightBlockInitializer;
							
						
				//DC coefficients reader
				int[] encodedDCCoefficients = new int[numberOfEightxEightBlocks];
				for(int i = 0; i < encodedDCCoefficients.length; i++)
					encodedDCCoefficients[i] = Entropy.readDC();
				DPCM.iDPCMOperation(encodedDCCoefficients, eightXEightBlocksContainer);
				
				//AC coefficients reader
				int[] rLCCoefficient = {1,1};
				ArrayList<int[]> rLCCoefficients = new ArrayList<int[]>();
				
				for (int i=0; i<COLOR_SPACE_SIZE;i++)
					for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
						for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
							
							while(rLCCoefficient[RLC.RUNLENGTH] != 0 && rLCCoefficient[RLC.VALUE] != 0){
					
								rLCCoefficient = Entropy.readAC();
								rLCCoefficients.add(rLCCoefficient);
								
							}
							
							int[][] rLCCoefficientsArray = new int[2][rLCCoefficients.size()];
							for(int u = 0; u < rLCCoefficientsArray.length; u++){
								
								rLCCoefficientsArray[u][RLC.RUNLENGTH] = rLCCoefficients.get(u)[RLC.RUNLENGTH];
								rLCCoefficientsArray[u][RLC.VALUE] = rLCCoefficients.get(u)[RLC.VALUE];
								
							}
								
							
							RLC.iRLCOperation(rLCCoefficientsArray, eightXEightBlocksContainer[i][j][k]);
							
							//image post traitment
							int[][] unzigzagedEightxEightBlock = Zigzag.unzigzagOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix());
							int[][] unquantifiedEightxEightBlock = DCT.inverseDCTOperation(Quantization.deQuantizationOperation(unzigzagedEightxEightBlock, i, qualityFactor));
							EightXEightBlock eightXEightBlockResult = new EightXEightBlock(unquantifiedEightxEightBlock);
							eightXEightBlocksContainer[i][j][k] = eightXEightBlockResult;
							
							
						}
				
				//Fusion of the 8x8 blocks
				int[][][] newImageYCbCr = EightXEightBlocksMethods.eightXEightBlocksToImage(eightXEightBlocksContainer);
				
				//Save PPM file and conversion YcbCr -> RGB
				PPMReaderWriter.writePPMFile(savedFileName, Convert.extractImageYCbCr(newImageYCbCr));
			
			}else{
				JOptionPane.showMessageDialog(null, "Désolé, le format n'est pas supporté.");
				System.exit(0);
			}
		}
		
		//test print values
		/*System.out.println("-----------------------");
		for (int i=0; i<COLOR_SPACE_SIZE;i++)
			for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
				for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
					int[][] quantifiedEightxEightBlock = Quantization.quantizationOperation(DCT.dCTOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()), i, qualityFactor);
					int[][] zigzagedEightxEightBlock = Zigzag.zigzagOperation(quantifiedEightxEightBlock);
					int[][] unzigzagedEightxEightBlock = Zigzag.unzigzagOperation(zigzagedEightxEightBlock);
					int[][] unquantifiedEightxEightBlock = DCT.inverseDCTOperation(Quantization.deQuantizationOperation(unzigzagedEightxEightBlock, i, qualityFactor));
					EightXEightBlock eightXEightBlockResult = new EightXEightBlock(unquantifiedEightxEightBlock);
					eightXEightBlocksContainer[i][j][k] = eightXEightBlockResult;
					System.out.println(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[j][k]);
				}
			
		
		
		int[][][] newImageYCbCr = EightXEightBlocksMethods.eightXEightBlocksToImage(eightXEightBlocksContainer);
		int[][][] newImageRGB = Convert.extractImageYCbCr(newImageYCbCr);
		System.out.println("-----------------------");
		for (int i=0;i<newImageRGB[0].length;i++){
			for (int j=0;j<newImageRGB[0].length;j++){
				System.out.println(newImageRGB[0][i][j]);
				System.out.println(newImageRGB[1][i][j]);
				System.out.println(newImageRGB[2][i][j]);
			}
		}*/
		//fin test print values
	}
}
