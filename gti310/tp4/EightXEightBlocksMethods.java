package gti310.tp4;

public class EightXEightBlocksMethods {
	
	public static EightXEightBlock[][][] imageToEightXEightBlocks(int[][][] imageValues){
		
		int eightXEightBlocksContainerLength = imageValues[0].length/8; 
		
		//System.out.println(eightXEightBlocksContainerLength);
		
		EightXEightBlock[][][] eightXEightBlocksContainer = new EightXEightBlock[3]
				[eightXEightBlocksContainerLength][eightXEightBlocksContainerLength];
		
		for(int i = 0; i < 3; i++) 
			for(int j = 0; j < imageValues[0].length; j+=8) 
				for(int k = 0; k < imageValues[0].length; k+=8) 
					for(int u = 0; u < eightXEightBlocksContainerLength; u++)
						for(int v = 0; v < eightXEightBlocksContainerLength; v++){
							int[][] eightXEightBlockMatrix = new int[8][8];
							for(int x = 0; x < 8; x++)
								for(int y = 0; y < 8; y++)
									eightXEightBlockMatrix[x][y] = imageValues[i][j+x][k+y]; 
									
							EightXEightBlock eightXEightBlock = new EightXEightBlock(eightXEightBlockMatrix);
							
							eightXEightBlocksContainer[i][u][v] = eightXEightBlock;
					
						}
						
		return eightXEightBlocksContainer;
	}
	
	public static int[][][] eightXEightBlocksToImage(EightXEightBlock[][][] eightXEightBlocksContainer){
		
		
		int imageValuesLength = eightXEightBlocksContainer[0].length*8;
		int[][][] imageValuess = new int[3][imageValuesLength][imageValuesLength];
		
		for(int i = 0; i < 3; i++)
			for(int u = 0; u < eightXEightBlocksContainer[0].length; u++)
				for(int v = 0; v < eightXEightBlocksContainer[0].length; v++)
					for(int j = 0; j < imageValuesLength; j+=8) 
						for(int k = 0; k < imageValuesLength; k+=8) 
							for(int x = 0; x < 8; x++)
								for(int y = 0; y < 8; y++)
									imageValuess[i][j+x][k+y] = eightXEightBlocksContainer[i][u][v].getEightXEightBlockMatrix()[x][y];
		
		return imageValuess;
		
	}

}
