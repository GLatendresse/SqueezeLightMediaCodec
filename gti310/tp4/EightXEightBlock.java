package gti310.tp4;

public class EightXEightBlock {
	
	private int[][] eightXEightBlockMatrix;
	
	public EightXEightBlock(int[][] eightXEightBlockMatrix){
		
		this.eightXEightBlockMatrix = eightXEightBlockMatrix;
		
	}
	
	public int[][] getEightXEightBlockMatrix(){
		
		return eightXEightBlockMatrix;
		
	}
	
	public void setValue(int value, int i, int j){
		
		this.eightXEightBlockMatrix[i][j] = value; 
		
	}
	
	
}
