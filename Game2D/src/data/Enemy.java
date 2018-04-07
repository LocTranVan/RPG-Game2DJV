package data;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author nhatt_000
 */
public class Enemy {

    private int power, speed, centerX, centerY;
    private Image image;
    private static boolean back = true;
    public Rectangle r = new Rectangle(0, 0, 0, 0);
    public int health = 5;

    public Enemy() {
//        this.centerX = centerX;
//        this.centerY = centerY;
//        this.speed = speed;
//        this.power = power;
    }


    public void update() {

    }

    public void die() {

    }

    public void attack() {

    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean getback() {
        return back;
    }
    public Image getImage(){
        return image;
    }
//    public static  void setback(boolean back){
//        
//    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}