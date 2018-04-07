package data;

import java.awt.Image;

/**
 *
 * @author nhatt_000
 */
public class AnimFrame {

    private Image image;
    private long endTime;

    public AnimFrame(Image image, long endTime) {
        this.image = image;
        this.endTime = endTime;
    }

    public Image getImage() {
        return image;
    }

    public long getendTime() {
        return endTime;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setendTime(long endTime) {
        this.endTime = endTime;
    }
}
