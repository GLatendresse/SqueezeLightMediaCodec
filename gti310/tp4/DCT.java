package gti310.tp4;

public class DCT {
	
	public static int[][] dCTOperation(int[][] rawEightXEightBloc){
		
		double cu, cv, summationValue;
		
		int[][] dCTEightXEightBloc = new int[8][8];
		
		for(int u = 0 ; u < rawEightXEightBloc.length; u++)
			for(int v = 0 ; v < rawEightXEightBloc.length; v++){
				
				cu = 1;
				cv = 1;
				summationValue = 0;
				
				if(u == 0)
					cu = 1/(Math.sqrt(2));
				
				if(v == 0)
					cv = 1/(Math.sqrt(2));
				
				for(int i = 0 ; i < rawEightXEightBloc.length; i++)
					for(int j = 0 ; j < rawEightXEightBloc.length; j++){
						
						summationValue = summationValue + rawEightXEightBloc[i][j]*Math.cos(((2*i)+1)*u*Math.PI/16)*Math.cos(((2*j)+1)*v*Math.PI/16);
						
					}
				
				dCTEightXEightBloc[u][v] = (int) ((cu*cv/4)*summationValue);
			}
		
		return dCTEightXEightBloc;
		
	}
	
	public static int[][] inverseDCTOperation(int[][] dCTEightXEightBloc){
		
		double cu, cv, summationValue;
		
		int[][] EightXEightBloc = new int[8][8];
		
		for(int i = 0 ; i < EightXEightBloc.length; i++)
			for(int j = 0 ; j < EightXEightBloc.length; j++){
				
				summationValue = 0;
				
				for(int u = 0 ; u < EightXEightBloc.length; u++)
					for(int v = 0 ; v < EightXEightBloc.length; v++){
						
						cu = 1;
						cv = 1;
						
						if(u == 0)
							cu = 1/(Math.sqrt(2));
						
						if(v == 0)
							cv = 1/(Math.sqrt(2));
						
						summationValue = summationValue + (cu*cv/4)*dCTEightXEightBloc[u][v]*Math.cos((2*i+1)*u*Math.PI/16)*Math.cos((2*j+1)*v*Math.PI/16);
						
					}
				
				EightXEightBloc[i][j] = (int) summationValue;
			}
		
		return EightXEightBloc;
		
	}

}
