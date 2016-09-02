package lib;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;

	public class Image {
	  
	  public static boolean sameAs (String path1, String path2) throws FileNotFoundException {
	    boolean res = false;
	    FileInputStream fis1 = new FileInputStream(path1);
	    Bitmap bitmap1  = BitmapFactory.decodeStream(fis1);
	    
	    FileInputStream fis2 = new FileInputStream(path2);
	    Bitmap bitmap2  = BitmapFactory.decodeStream(fis2);
	    
	    res = sameAs(bitmap1,bitmap2);
	  
	    return res;
	      
	  }
	  
	  public static boolean sameAs (String path1, String path2,double percent) throws FileNotFoundException {
	    FileInputStream fis1 = new FileInputStream(path1);
	    Bitmap bitmap1  = BitmapFactory.decodeStream(fis1);
	    
	    FileInputStream fis2 = new FileInputStream(path2);
	    Bitmap bitmap2  = BitmapFactory.decodeStream(fis2);
	    
	    return sameAs(bitmap1,bitmap2,percent);
	      
	  }
	  
	  public static boolean sameAs (Bitmap bitmap1, Bitmap bitmap2, double percent) {
	    if(bitmap1.getHeight() != bitmap2.getHeight())
	      return false;
	    
	    if(bitmap1.getWidth() != bitmap2.getWidth())
	      return false;
	    
	    if(bitmap1.getConfig() != bitmap2.getConfig())
	      return false;

	    int width = bitmap1.getWidth();
	    int height = bitmap2.getHeight();

	    int numDiffPixels = 0;
	       
	       for (int y = 0; y < height; y++) {
	         for (int x = 0; x < width; x++) {
	           if (bitmap1.getPixel(x, y) != bitmap2.getPixel(x, y)) {
	             numDiffPixels++;
	           }
	         }
	       }
	       double numberPixels = height * width;
	       double diffPercent = numDiffPixels / numberPixels;
	       return percent <= 1.0D - diffPercent;
	  }
	  
	  public static boolean sameAs (Bitmap bmp1, Bitmap bmp2) throws FileNotFoundException {
	    boolean res = false;
	    
	    res = bmp1.sameAs(bmp2);
	    
	    return res;		
	  }
	  
	  public static Bitmap getSubImage(String path,int x,int y,int width,int height) throws FileNotFoundException {
	    
	    FileInputStream fis = new FileInputStream(path);
	    Bitmap bitmap  = BitmapFactory.decodeStream(fis);
	        
	    Bitmap res = Bitmap.createBitmap(bitmap, x, y, width, height);
	    
	    return res;
	    
	  }
	
	}
