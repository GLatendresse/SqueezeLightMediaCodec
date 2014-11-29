package gti310.tp4;

public class Quantization {
	
	//Tables de quantification
	final static int[][] QY = {{ 16, 40, 40, 40, 40, 40, 51, 61}, 
						{ 40, 40, 40, 40, 40, 58, 60, 55},
						{ 40, 40, 40, 40, 40, 57, 69, 56}, 
						{ 40, 40, 40, 40, 51, 87, 80, 62},
						{ 40, 40, 40, 56, 68, 109, 103, 107}, 
						{ 40, 40, 55, 64, 81, 104, 113, 92},
						{ 49, 64, 78, 87, 103, 121, 120, 101}, 
						{ 72, 92, 95, 98, 112, 100, 103, 95}};
	
	final static int[][] QU = {{ 17, 40, 40, 95, 95, 95, 95, 95}, 
						{ 40, 40, 40, 95, 95, 95, 95, 95},
						{ 40, 40, 40, 95, 95, 95, 95, 95}, 
						{ 40, 40, 95, 95, 95, 95, 95, 95},
						{ 95, 95, 95, 95, 95, 95, 95, 95}, 
						{ 95, 95, 95, 95, 95, 95, 95, 95},
						{ 95, 95, 95, 95, 95, 95, 95, 95}, 
						{ 95, 95, 95, 95, 95, 95, 95, 95}};
	
	final static int[][] QV = QU;
	
	final static int MAXQUALITYVALUE = 100;
	final static int MINQUALITYVALUE = 1;
	final static int MIDQUALITYVALUE = 50;
	final static int LUMINANCEQUANTIZATION = 0;
	final static int CHROMINANCEQUANTIZATION = 1;
	
	
	
	public static int[][] quantizationOperation(int[][] rawEightXEightBloc, int quantizationType, int qualityValue){
		
		int[][] quantifiedEightXEightBloc = new int[8][8];
		int[][] quantizationMatrix = new int[8][8];
		double alpha = 1;
		
		if(quantizationType == LUMINANCEQUANTIZATION)
			quantizationMatrix = QY;
		
		else
			quantizationMatrix = QU;
		
		if(qualityValue < MINQUALITYVALUE)
			qualityValue = MINQUALITYVALUE;
			
		
		if(qualityValue >= MAXQUALITYVALUE)
			quantifiedEightXEightBloc = rawEightXEightBloc;
		
		else{
			
			if(qualityValue >= MINQUALITYVALUE && qualityValue <= MIDQUALITYVALUE )
				alpha = 50D/(double)qualityValue;
			
			else
				alpha = (200D-2D*(double)qualityValue)/100D;
			
			for(int u = 0; u < rawEightXEightBloc.length; u++)
				for(int v = 0; v < rawEightXEightBloc.length; v++){
					
					quantifiedEightXEightBloc[u][v] = (int) (rawEightXEightBloc[u][v]/(alpha*quantizationMatrix[u][v]));
					
			}
				
			
		}
		
		
		return quantifiedEightXEightBloc;
		
	}
	
	public static int[][] deQuantizationOperation(int[][] quantifiedEightXEightBloc, int quantizationType, int qualityValue){
		
		int[][] dequantifiedEightXEightBloc = new int[8][8];
		int[][] quantizationMatrix = new int[8][8];
		double alpha = 1;
		
		if(quantizationType == LUMINANCEQUANTIZATION)
			quantizationMatrix = QY;
		
		else
			quantizationMatrix = QU;
		
		if(qualityValue < MINQUALITYVALUE)
			qualityValue = MINQUALITYVALUE;
			
		
		if(qualityValue >= MAXQUALITYVALUE)
			dequantifiedEightXEightBloc = quantifiedEightXEightBloc;
		
		else{
			
			if(qualityValue >= MINQUALITYVALUE && qualityValue <= MIDQUALITYVALUE )
				alpha = 50D/(double)qualityValue;
			
			else
				alpha = (200D-2D*(double)qualityValue)/100D;
			
			for(int u = 0; u < quantifiedEightXEightBloc.length; u++)
				for(int v = 0; v < quantifiedEightXEightBloc.length; v++){
					
					dequantifiedEightXEightBloc[u][v] = (int) (quantifiedEightXEightBloc[u][v]*alpha*quantizationMatrix[u][v]);
					
			}
				
			
		}
		
		
		return dequantifiedEightXEightBloc;
		
	}
	
}
