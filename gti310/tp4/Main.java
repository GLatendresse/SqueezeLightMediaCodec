package gti310.tp4;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Fran�ois Caron
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
		
		//Image convert (RGB to YCbCr) + read file
		imageRGB = Convert.extractImageRGB(PPMReaderWriter.readPPMFile(args[1]));
		int qualityFactor = Integer.parseInt(args[0]);
		
		int[][][] imageYCbCr = Convert.extractImageRGB(imageRGB);
		
		EightXEightBlock[][][] eightXEightBlocksContainer = EightXEightBlocksMethods.imageToEightXEightBlocks(imageYCbCr);
		
		
		
		//int[][] test8x8 = Quantization.quantizationOperation(DCT.dCTOperation(eightXEightBlocksContainer[0][0][0].getEightXEightBlockMatrix()), Quantization.LUMINANCEQUANTIZATION, 80);
		//test8x8 = Zigzag.zigzagOperation(test8x8);
		
		//Save compressed file
		//writeSZLFile(args[2], (height), (width), qualityFactor);
		
		//test
		System.out.println("-----------------------");
		for (int i=0; i<3;i++){
			for (int j =0;j<eightXEightBlocksContainer[0].length;j++){
				for (int k=0;k<eightXEightBlocksContainer[0].length;k++){
					int[][] quantifiedEightxEightBlock = Quantization.quantizationOperation(DCT.dCTOperation(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()), i, qualityFactor);
					int[][] zigzagedEightxEightBlock = Zigzag.zigzagOperation(quantifiedEightxEightBlock);
					int[][] unzigzagedEightxEightBlock = Zigzag.unzigzagOperation(zigzagedEightxEightBlock);
					int[][] unquantifiedEightxEightBlock = DCT.inverseDCTOperation(Quantization.deQuantizationOperation(unzigzagedEightxEightBlock, i, qualityFactor));
					EightXEightBlock eightXEightBlockResult = new EightXEightBlock(unquantifiedEightxEightBlock);
					eightXEightBlocksContainer[i][j][k] = eightXEightBlockResult;
					System.out.println(eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[j][k]);
				}
			}
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
		}
		
		

		//DPCM.iDPCMOperation(DPCM.dPCMOperation(eightXEightBlocksContainer), eightXEightBlocksContainer);
		//DCT.inverseDCTOperation(DCT.dCTOperation(eightXEightBlocksContainer[0][0][0].getEightXEightBlockMatrix()));
		//int[][][] image = EightXEightBlocksMethods.eightXEightBlocksToImage(eightXEightBlocksContainer);
		//System.out.println(eightXEightBlocksContainer[2][1][1].getEightXEightBlockMatrix()[6][6]);
				
		
		
	}
	
	
}
