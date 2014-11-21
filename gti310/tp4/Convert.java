package gti310.tp4;

public class Convert {
	
	public int[] RGBToYCbCr(int r, int g, int b){
		int[] ycbcr= new int[3];
		int y  = (int)(( 0.299   * r)*255 + (0.587 * g)*255 + (0.114 * b)*255);
		int cb = (int)((-0.16874 * r)*255 - (0.33126 * g)*255 + (0.50000 * b)*255);
		int cr = (int)( (0.50000 * r)*255 - (0.41869 * g)*255 - (0.08131 * b)*255);
		ycbcr[0] = y;
		ycbcr[1] = cb;
		ycbcr[2] = cr; 	
		return ycbcr;
	}
	
	public int[] YCbCrToRGB(int y, int cb, int cr){
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
