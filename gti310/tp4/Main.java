package gti310.tp4;

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
			int height = imageYCbCr[0].length;
			
			//Separate the image in 8x8 pixel blocks and stock them into a container EightXEightBlock[color space][i localisation][j localisation]
			EightXEightBlock[][][] eightXEightBlocksContainer = EightXEightBlocksMethods.imageToEightXEightBlocks(imageYCbCr);
			
			//Image pretraitment
			for (int i=0; i<3;i++)
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
			for (int i=0; i<3;i++)
				for (int j =0;j<eightXEightBlocksContainer[0].length;j++)
					for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
						
						int[][] rLCCoefficients = RLC.rLCOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix());
						for(int u = 0; u < rLCCoefficients.length; u++)
							Entropy.writeAC(rLCCoefficients[u][RLC.RUNLENGTH], rLCCoefficients[u][RLC.VALUE]);
						
					}
			
			//Save compressed file
			SZLReaderWriter.writeSZLFile(savedFileName, imageYCbCr[0].length, imageYCbCr[0][0].length, qualityFactor);
			System.out.println("done");
			
		}else{
			//SLZ
			if (extension.equals("slz")){
				//Ask the user for the decompressed filename
				JFrame frameFileName = new JFrame();
				String savedFileName = JOptionPane.showInputDialog(frameFileName, "Enter the PPM filename:");
				//Read compressed file
				SZLReaderWriter.readSZLFile(fileName);
				
				//Save PPM file
				//writePPMFile(savedFileName, imageYCbCr);
			
			}else{
				JOptionPane.showMessageDialog(null, "Désolé, le format n'est pas supporté.");
				System.exit(0);
			}
		}
		
		//test print values
		/*System.out.println("-----------------------");
		for (int i=0; i<3;i++)
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
