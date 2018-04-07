/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamap2;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import data.*;
public class EnemyFishSmall extends Enemy {

    private int health, speed, centerX, centerY;
    private Image image;
    private boolean back = true, left, right, top, down;
    private Rectangle r;
    private Image fishsmalleft1, fishsmalleft2, fishsmalleft3, fishsmallright1, fishsmallright2, fishsmallright3;
    private Image bulletfish;
    private Animation fishsmalleft, fishsmallright, fishsmallattackleft, fishsmallattackright;
    private Robot robot;
    private ArrayList<Tile> array;
//    private ArrayList<Tile2> array = InterfaceGamemap2.getArraytile();
    private ArrayList<Bullet> bulletfishsmall = new ArrayList<Bullet>();
    private int i;
    private int vitri;
    private enum GameState {
        Right, Left
    }
    GameState state;

    private enum GameState1 {
        Fight, NotFight
    }
    GameState1 st;

    public EnemyFishSmall(int centerX, int centerY, int speed, int health,ArrayList<Tile> array,Robot robot, int dem) throws IOException {
     switch (dem) {
            case 1:
                vitri = 466;
                break;
            case 2:
                vitri = 486;
                break;
            case 3:
                vitri = 506;
                break;
            case 4:
                vitri = 528;
                break;
        }
        this.robot = robot;
        this.array = array;
        this.centerX = centerX;
        this.centerY = centerY;
        this.speed = speed;
        this.health = health;
        state = GameState.Right;
        r = new Rectangle(0, 0, 0, 0);
        init();
    }

    public void init() throws IOException {
        fishsmalleft1 = ImageLibrary.loadImage("fishsmalleft1");
        fishsmalleft2 = ImageLibrary.loadImage("fishsmalleft2");
        fishsmalleft3 = ImageLibrary.loadImage("fishsmalleft3");

//        fishpink4left = ImageLibrary.loadImage("fishpink4left");
        fishsmallright1 = ImageLibrary.loadImage("fishsmallright1");
        fishsmallright2 = ImageLibrary.loadImage("fishsmallright2");
        fishsmallright3 = ImageLibrary.loadImage("fishsmallright3");
        bulletfish = ImageLibrary.loadImage("bulletfishsmall");

        fishsmalleft = new Animation();
        fishsmalleft.addFrane(fishsmalleft1, 50);
        fishsmalleft.addFrane(fishsmalleft2, 50);
        fishsmalleft.addFrane(fishsmalleft3, 50);
        fishsmallright = new Animation();
        fishsmallright.addFrane(fishsmallright1, 50);
        fishsmallright.addFrane(fishsmallright2, 50);
        fishsmallright.addFrane(fishsmallright3, 50);
//            spiderattackleft = new Animation();
//            spiderattackleft.addFrane(spiderattackleft1, 50);
//            spiderattackleft.addFrane(spiderattackleft2, 50);
//            spiderattackleft.addFrane(spiderattackleft3, 50);
//            spiderattackleft.addFrane(spiderattackleft4, 50);
//
//            spiderattackright = new Animation();
//            spiderattackright.addFrane(spiderattackright1, 50);
//            spiderattackright.addFrane(spiderattackright2, 50);
//            spiderattackright.addFrane(spiderattackright3, 50);
//            spiderattackright.addFrane(spiderattackright4, 50);
  
    }

    @Override
    public void update() {
        centerX += speed;
        fishsmalleft.update(3);
        fishsmallright.update(3);
        ApdateandRemoveBullet();
//            spiderattackleft.update(3);
//            spiderattackright.update(3);
        r.setBounds(centerX, centerY + 10, 10, 10);
        checkCollision();

    }

    private synchronized void checkCollision() {
        if (InterfaceGamemap2.getRobot().getcenterX() == centerX) {
            i++;
            if (i == 90) {
                Bullet b = new Bullet(centerX, centerY, 0, 3, 4, bulletfish,robot);
                bulletfishsmall.add(b);
                i = 0;
            }
            st = GameState1.Fight;

        } else {
            st = GameState1.NotFight;
        }
        for (int i = 0; i < array.size(); i++) {
            Tile t = array.get(i);
            if (r.intersects(t.getRectangle()) && t.getType() == 2) {
                if (state == GameState.Right) {

                    state = GameState.Left;

                } else {

                    state = GameState.Right;

                }

            }
        }

        if (state == GameState.Right && st == GameState1.NotFight) {
            image = fishsmalleft.getImage();
            speed = -1;
        } else if (state == GameState.Left && st == GameState1.NotFight) {
            image = fishsmallright.getImage();
            speed = 1;
        }
        if (st == GameState1.Fight) {
            image = fishsmalleft.getImage();
            speed = 0;
        }

    }

    public void ApdateandRemoveBullet() {
        if (bulletfishsmall.size() > 0) {
            for (int i = 0; i < bulletfishsmall.size(); i++) {
                Bullet m = bulletfishsmall.get(i);
                m.update();
                if (m.getCenterY() > 700 || m.getTime() == 30) {
                    bulletfishsmall.remove(i);
                }
            }
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawImage(image, centerX, centerY, null);
 
//        g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        g.drawString(Integer.toString(health), 1315, vitri);
//        g.drawString(Integer.toString(speed), 1276, 477);
        for (int i = 0; i < bulletfishsmall.size(); i++) {
            Bullet b = bulletfishsmall.get(i);
            g.drawImage(b.getImage(), b.getCenterX(), b.getCenterY(), null);
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void attack() {

    }

    public int getHealth() {
        return health;
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

    @Override
    public boolean getback() {
        return back;
    }

    @Override
    public Image getImage() {

        return image;
    }


    public Rectangle getRect() {
        return r;
    }

//    public static  void setback(boolean back){
//        
//    }
    public void sethealth(int health) {
        this.health = health;
    }

    public void setspeed(int speed) {
        this.speed = speed;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}
