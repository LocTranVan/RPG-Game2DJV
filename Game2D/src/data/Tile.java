
package data;

import images.ImageLibrary;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import datamap2.*;
import datamap1.*;
import manager.GameStateManager;
/**
 *
 * @author nhatt_000
 */
public class Tile {

    private int tlx, tly, type;
    private Image image;
    public Rectangle r ;
//    private Robot robot;
//    private GameStateManager gsm = new GameStateManager();
    private Robot robot;
//    private Robot robot = InterfaceGame2.getRobot();
    public static Image f21, f22, nam, tree, house, f23;
    public Tile(int tlx, int tly, int type,Robot robot) throws IOException {
        this.robot = robot;
//        r.setBounds(tlx, tly, 60, 60);
        init();
        r = new Rectangle();

        this.tlx = tlx * 70;
        this.tly = tly * 70;
        this.type = type;
        if (type == 3) {
            image = tree;
        } else if (type == 4) {
            image = f21;
        } else if (type == 5) {
            image = f22;

        } else if (type == 2) {
            image = f23;
        } else if (type == 1) {
            image = null;
        }

    }

    public void init() throws IOException {
    	f21 = ImageLibrary.loadImage("f21");
        f22 = ImageLibrary.loadImage("f22");
        nam = ImageLibrary.loadImage("nam");
        tree = ImageLibrary.loadImage("tree");
        f23 = ImageLibrary.loadImage("f23");


    }

    public void update() {

        r.setBounds(tlx - 15, tly, 60, 58);
        if (r.intersects(Robot.rect2) && type != 1) {
            robot.setcenterY(tly + 43);
        }
        if (r.intersects(Robot.rect) && type != 1) {
            robot.setcenterY(tly - 63);
        }

        if (r.intersects(Robot.leftfoot) && type != 1) {
//            robot.setcenterX(tlx - 62);
        	robot.setcenterX(tlx - 70);
        }
        if (r.intersects(Robot.rightfoot) && type != 1) {
            robot.setcenterX(tlx + 50);
        }
        for(int i = 0; i < robot.getArrayBullet().size(); i++){
        	Bullet b = robot.getArrayBullet().get(i);
        	if(type != 1 && r.intersects(b.getRect())){
        		robot.getArrayBullet().remove(i);
        	}
        }
     


    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
        if (rbot.intersects(r)) {

        }
        if (rtop.intersects(r) && type != 1) {
//           robot.setcenterX(tlx - 62);
//           robot.setcenterY(tly + 10);
        }
    }
 
    public void setTlx(int tlx) {
        this.tlx = tlx;
    }

    public void setTly(int tly) {
        this.tly = tly;

    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTly() {
        return tly;
    }

    public int getTlx() {
        return tlx;
    }

    public int getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRectangle() {
        return r;
    }
}
