package gti310.tp4;

public class Convert {
	
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
	
	private static int[] RGBToYCbCr(int r, int g, int b){
		int[] ycbcr= new int[3];
		int y  = (int)((( 0.299   * r) + (0.587 * g) + (0.114 * b))+128);
		int cb = (int)(((-0.16874 * r) - (0.33126 * g) + (0.50000 * b))+128);
		int cr = (int)(((0.50000 * r) - (0.41869 * g) - (0.08131 * b))+128);
		ycbcr[0] = y;
		ycbcr[1] = cb;
		ycbcr[2] = cr; 	
		return ycbcr;
	}
	
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
	
	private static int[] YCbCrToRGB(int y, int cb, int cr){
		int[] rgb = new int[3];
		int r  = (int)(( 1.0   * ((y * 219) + 16) + 0 * ((cb * 224) + 128)  + 1.402 * ((cr * 224) + 128))/255);
		int g = (int)((1.0 * ((y * 219) + 16) - 0.33126 * ((cb * 224) + 128) + 0.50000 * ((cr * 224) + 128))/255);
		int b = (int)(( 1.0 * ((y * 219) + 16) - 0.41869 * ((cb * 224) + 128) - 0.08131 * ((cr * 224) + 128))/255);
		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b; 	
		return rgb;
	}

}
