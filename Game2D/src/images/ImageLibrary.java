package images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author nhatt_000
 */
public class ImageLibrary {

    public static Image loadImage(String name) throws IOException {
//        String path = name;
//        String path = "C:\\Users\\dellvostroo 2420\\Documents\\NetBeansProjects\\JavaApplication5\\src\\images\\"+name+".png";
    	 String path = "/images/"+name+".png";
    	BufferedImage spritesheet = ImageIO.read(ImageLibrary.class.getResourceAsStream(path));
//        File file = new File(name + ".png");
//        BufferedImage image;
//        image = ImageIO.read(file);
//        return image;
        return spritesheet;
    }
}
