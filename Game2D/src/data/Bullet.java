package data;

import images.ImageLibrary;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import datamap1.InterfaceGame;

/**
 *
 * @author nhatt_000
 */
public class Bullet {

    private int centerX, centerY, speedX, speedY, power;
    private int i;
    private long a;
    private Image image;
    private Rectangle r;
    private Image bum;
    private boolean delete;
    private Robot robot;
    public Bullet(int centerX, int centerY, int speedX, int speedY, int power, Image image,Robot robot) {
//      
        this.robot = robot;
        try {
            this.centerX = centerX;
            this.centerY = centerY;
            this.speedX = speedX;
            this.speedY = speedY;
            this.power = power;
            this.image = image;
            r = new Rectangle(0, 0, 0, 0);
            bum = ImageLibrary.loadImage("bum");
        } catch (IOException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public Bullet(int centerX, int centerY, int speedX, int speedY, int power, Image image) {
//      
   
        try {
            this.centerX = centerX;
            this.centerY = centerY;
            this.speedX = speedX;
            this.speedY = speedY;
            this.power = power;
            this.image = image;
            r = new Rectangle(0, 0, 0, 0);
            bum = ImageLibrary.loadImage("bum");
        } catch (IOException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() {
        r.setBounds(centerX, centerY, 40, 45);
        centerX += speedX;
        centerY += speedY;
        checkCollision();
//        for(int i = 0; i< InterfaceGame.getArraytile().size(); i++){
//        	Tile t = InterfaceGame.getArraytile().get(i);
//        	if(r.intersects(t.r)){
//        		
//        	}
//        }
    }

    private void checkCollision() {
        if (r.intersects(robot.bound)&& power != 0 ) {
//        	if(System.currentTimeMillis() - a < 500){
            image = bum;
//        	}else {
//        		image = null;
//        		a = System.currentTimeMillis();
//        	}
            int health = robot.getHealth();
            health -= 1;
            robot.setHealth(health);

        }
        if (image == bum) {
            i++;
        }
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getPower() {
        return power;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return r;
    }

    public boolean getdelet() {
        return delete;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedX = speedY;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTime() {
        return i;
    }
}
