package gti310.tp4;

public class Convert {
	
	//Conversion de chaque pixel RGB à YCbCr
	public static int[][][] extractImageRGB(int[][][] image){
		int r, g, b;
		int[] ycbcr;
		int[][][] imageYCbCr = new int[image.length][image[0].length][image[0].length];
		for (int i=0; i<image[0].length;i++){
			for (int j=0;j<image[0].length;j++){
				r = image[0][i][j];
				g = image[1][i][j];
				b = image[2][i][j];
				ycbcr = RGBToYCbCr(r,g,b);
				imageYCbCr[0][i][j] = ycbcr[0];
				imageYCbCr[1][i][j] = ycbcr[1];
				imageYCbCr[2][i][j] = ycbcr[2];
			}
		}
		return imageYCbCr;
	}
	
	//Formule de conversion
	private static int[] RGBToYCbCr(int r, int g, int b){
		int[] ycbcr= new int[3];
		int y  = (int)((( 0.299   * r) + (0.587 * g) + (0.114 * b)));
		int cb = (int)(((-0.16874 * r) - (0.33126 * g) + (0.50000 * b))+128);
		int cr = (int)(((0.50000 * r) - (0.41869 * g) - (0.08131 * b))+128);
		ycbcr[0] = y;
		ycbcr[1] = cb;
		ycbcr[2] = cr; 	
		return ycbcr;
	}
	
	//Conversion de chaque pixel YCbCr à RGB
	public static int[][][] extractImageYCbCr(int[][][] image){
		int y, cb, cr;
		int[] rgb;
		int[][][] imageRGB = new int[image.length][image[0].length][image[0].length];
		for (int i=0; i<image[0].length;i++){
			for (int j=0;j<image[0].length;j++){
				y = image[0][i][j];
				cb = image[1][i][j];
				cr = image[2][i][j];
				rgb = YCbCrToRGB(y,cb,cr);
				imageRGB[0][i][j] = rgb[0];
				imageRGB[1][i][j] = rgb[1];
				imageRGB[2][i][j] = rgb[2];
			}
		}
		return imageRGB;
	}
	
	//Formule de conversion
	private static int[] YCbCrToRGB(int y, int cb, int cr){
		int[] rgb = new int[3];
		int r  = (int)(y + (1.402 * (cr-128)));
		int g = (int)(y - (0.34414 * (cb - 128)) - (0.71414 * (cr -128)));
		int b = (int)(y + (1.772 * (cb - 128)));
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b; 	
		return rgb;
	}

}
