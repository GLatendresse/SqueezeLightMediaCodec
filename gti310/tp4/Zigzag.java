package gti310.tp4;

public class Zigzag {
	
	
	public static int[][] zigzagOperation(int[][] rawEightXEightBloc){
		
		int[][] zigzagedEightXEightBloc = new int[8][8];
		boolean alreadyHitBorder = false;
		boolean directionIsRight = true;
		boolean firstCaseVisited = false;
		int i = 0; 
		int j = 0;
		
		for(int u = 0; u < zigzagedEightXEightBloc.length; u++)
			for(int v = 0; v < zigzagedEightXEightBloc.length; v++){
				
				//Première case
				if(i == 0 && j == 0 && !firstCaseVisited){
					
					zigzagedEightXEightBloc[u][v] = rawEightXEightBloc[i][j];
					firstCaseVisited = true;
				
				}
				
				//Chemin du zigzag
				else{
					
					if(i == 0){
						
						if(!alreadyHitBorder){
							
							j++;
							alreadyHitBorder = true;
							
						}
						
						else{
							
							i++;
							j--;
							directionIsRight = false;
							alreadyHitBorder = false;
							
						}
							
					}
					
					else if(i == zigzagedEightXEightBloc.length - 1){
						
						if(!alreadyHitBorder){
							
							j++;
							alreadyHitBorder = true;
							
						}
						
						else{
							
							i--;
							j++;
							directionIsRight = true;
							alreadyHitBorder = false;
							
						}
					}
					
					else if(j == 0){
						
						if(!alreadyHitBorder){
							
							i++;
							alreadyHitBorder = true;
							
						}
						
						else{
							
							i--;
							j++;
							directionIsRight = true;
							alreadyHitBorder = false;
							
						}
						
					}
					
					else if(j == zigzagedEightXEightBloc.length - 1){
						
						if(!alreadyHitBorder){
							
							i++;
							alreadyHitBorder = true;
							
						}
						
						else{
							
							i++;
							j--;
							directionIsRight = false;
							alreadyHitBorder = false;
							
						}
					}
					
					else if(directionIsRight){
						
						i--;
						j++;
						
					}
					
					else{
						
						i++;
						j--;
						
					}
					
					zigzagedEightXEightBloc[u][v] = rawEightXEightBloc[i][j];
					
				}
				
				
					
			}
			
		return zigzagedEightXEightBloc;
		
	}
	
}
