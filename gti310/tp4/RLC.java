package gti310.tp4;

public class RLC {

	public final static int BLOCKSIZE = 64;
	public final static int RUNLENGTH = 0;
	public final static int VALUE = 1;
	
	//RLC
	public static int[][] rLCOperation(int[][] rawEightXEightBloc) {

		int zeroCounter = 0;

		for (int i = 0; i < rawEightXEightBloc.length; i++)
			for (int j = 0; j < rawEightXEightBloc.length; j++) {
				
				if(i == 0 && j == 0){
					
					
				}
				
				else if (rawEightXEightBloc[i][j] == 0)
					zeroCounter++;

			}
		
		// Premier indice correspond au RUNLENGTH(0) et au VALUE(1)
		int[][] rLCCoefficients = new int[BLOCKSIZE - zeroCounter][2];
		
		zeroCounter = 0;
		int u = 0;
		
		for (int i = 0; i < rawEightXEightBloc.length; i++)
			for (int j = 0; j < rawEightXEightBloc.length; j++) {
				
				if(i == 0 && j == 0){
					
					
				}
				
				else if (u == rLCCoefficients.length - 1){
					
					rLCCoefficients[u][RUNLENGTH] = 0;
					rLCCoefficients[u][VALUE] = 0;
					
				}
				
				else if (rawEightXEightBloc[i][j] == 0)
					zeroCounter++;
				
				else{
					
					rLCCoefficients[u][RUNLENGTH] = zeroCounter;
					rLCCoefficients[u][VALUE] = rawEightXEightBloc[i][j];
					u++;
					zeroCounter = 0;
				}
					
			}
		
		
		
		
		return rLCCoefficients;
	}
	
	//RLC inverse
	public static void iRLCOperation(int[][] rLCCoefficients, EightXEightBlock eightXEightBlock){
		
		int u = 0;
		int zeroCounter = rLCCoefficients[u][RUNLENGTH];
		
		for(int i = 0; i < eightXEightBlock.getEightXEightBlockMatrix().length; i++)
			for(int j = 0; j < eightXEightBlock.getEightXEightBlockMatrix().length; j++){
					
				
				if(i == 0 && j==0){
						
						
				}
				
				else if(u >= rLCCoefficients.length){
					
					eightXEightBlock.setValue(0, i, j);
					
				}
					
				else if(zeroCounter > 0){
						
					eightXEightBlock.setValue(0, i, j);
					zeroCounter--;
						
				}
						
				else{
							
					eightXEightBlock.setValue(rLCCoefficients[u][VALUE], i, j);
					u++;
					
					if(u < rLCCoefficients.length)
						zeroCounter = rLCCoefficients[u][RUNLENGTH];
							
				}
				
					
			}
		
		int t = 0;
	
	}

}
