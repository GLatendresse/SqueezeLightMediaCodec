package gti310.tp4;

public class DPCM {
	
	//DPCM
	public static int[] dPCMOperation(EightXEightBlock[][][] eightXEightBlocksContainer){
		
		
		int nbOfDCCoefficients = eightXEightBlocksContainer.length*eightXEightBlocksContainer[0].length*eightXEightBlocksContainer[0][0].length;
		int[] encodedDCCoefficients = new int[nbOfDCCoefficients];
		int u = 0;
		int precedentDCValue = 0;
		
		for(int i = 0; i < eightXEightBlocksContainer.length; i++)
			for(int j = 0; j < eightXEightBlocksContainer[0].length; j++)
				for(int k = 0; k < eightXEightBlocksContainer[0][0].length; k++){
					
					encodedDCCoefficients[u] = eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[0][0] - precedentDCValue;
					precedentDCValue = eightXEightBlocksContainer[i][j][k].getEightXEightBlockMatrix()[0][0];
					
					u++;
				}
			
		
		return encodedDCCoefficients;
	
	}
	
	//DPCM inverse
	public static void iDPCMOperation(int[] encodedDCCoefficients, EightXEightBlock[][][] eightXEightBlocksContainer){
		
		int u = 0;
		int precedentDCValue = 0;
		
		for(int i = 0; i < eightXEightBlocksContainer.length; i++)
			for(int j = 0; j < eightXEightBlocksContainer[0].length; j++)
				for(int k = 0; k < eightXEightBlocksContainer[0][0].length; k++){
					
					eightXEightBlocksContainer[i][j][k].setValue(encodedDCCoefficients[u] + precedentDCValue, 0, 0);
					precedentDCValue = encodedDCCoefficients[u] + precedentDCValue;
					u++;
				}
	
	}

}
