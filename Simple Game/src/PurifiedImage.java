

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PurifiedImage {

	public static Image emptyImage = (Image) new BufferedImage(1, 1, Image.SCALE_DEFAULT); 
	
	public Image TranseptBufferedImage(String path){
		BufferedImage image ;
		try {
			image = ImageIO.read(this.getClass().getResource(path));
			
			ImageFilter filter = new RGBImageFilter()
		    {
				int markerRGB = Color.WHITE.getRGB() | 0xFF000000;
				
				@Override
		      public final int filterRGB(int x, int y, int rgb){
				if ((rgb | 0xFF000000) == markerRGB) {
    				// Mark the alpha bits as zero - transparent
    				return 0x00FFFFFF & rgb;
    			} else {
    				// nothing to do
    				return rgb;
    			}
				 }
		    };
		    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		    return Toolkit.getDefaultToolkit().createImage(ip);
			
		    
		} 
		catch (IOException|IllegalArgumentException e ) {
			//System.out.println("Cannot find "+path);
			e.printStackTrace();
		}
	
		return null;
	}//end method
	
}

