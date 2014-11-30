package gti310.tp4;

public class DPCM {
	
	public static int[] dPCMOperation(EightXEightBlock[][][] eightXEightBlocksContainer){
		
		
		int nbOfDCCoefficients = eightXEightBlocksContainer.length*eightXEightBlocksContainer[0].length*eightXEightBlocksContainer[0][0].length;
		int[] encodedDCCoefficients = new int[nbOfDCCoefficients];
		int u = 0;
		int precedentDCValue = 0;
		int[] DCCoefficients = new int[nbOfDCCoefficients];
		
		for(int i = 0; i < eightXEightBlocksContainer.length; i++)
			for(int j = 0; j < eightXEightBlocksContainer[0].length; j++)
				for(int k = 0; k < eightXEightBlocksContainer[0][0].length; k++){
					
					encodedDCCoefficients[u] = eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[0][0] - precedentDCValue;
					precedentDCValue = eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[0][0];
					
					u++;
				}
			
		
		return encodedDCCoefficients;
	
	}

}
