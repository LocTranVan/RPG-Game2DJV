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

public class EnemyFishBig extends Enemy {

    private int health, speed, centerX, centerY;
    private Image image, imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private boolean back = true, left, right, top, down;
    private Rectangle r;
    private Image fishbigleft1, fishbigleft2, fishbigleft3, fishbigright1, fishbigright2, fishbigright3;
    private Image fishbigfightleft1, fishbigfightleft2, fishbigfightright1, fishbigfightright2;
    private Animation fishbigleft, fishbigright, fishbigattackleft, fishbigattackright;
    private ArrayList<Tile> array;
//    private ArrayList<Tile2> array = InterfaceGamemap2.getArraytile();
    private int vitri;
    private enum GameState {

        Right, Left
    }
    GameState state;

    private enum GameState1 {

        FightLeft, FightRight, NotFight
    }
    GameState1 st;

    public EnemyFishBig(int centerX, int centerY, int speed, int health, ArrayList<Tile> array, int dem) throws IOException {
        switch (dem) {
            case 1:
                vitri = 365;
                break;
            case 2:
                vitri = 385;
                break;
            case 3:
                vitri = 407;
                break;
            case 4:
                vitri = 427;
                break;
        }
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
        fishbigleft1 = ImageLibrary.loadImage("fishbigleft1");
        fishbigleft2 = ImageLibrary.loadImage("fishbigleft2");
        fishbigleft3 = ImageLibrary.loadImage("fishbigleft3");
//        fishpink4left = ImageLibrary.loadImage("fishpink4left");
        fishbigright1 = ImageLibrary.loadImage("fishbigright1");
        fishbigright2 = ImageLibrary.loadImage("fishbigright2");
        fishbigright3 = ImageLibrary.loadImage("fishbigright3");

        fishbigfightright1 = ImageLibrary.loadImage("fishbigfightright1");
        fishbigfightright2 = ImageLibrary.loadImage("fishbigfightright2");

        fishbigfightleft1 = ImageLibrary.loadImage("fishbigfightleft1");
        fishbigfightleft2 = ImageLibrary.loadImage("fishbigfightleft2");
        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");

        fishbigleft = new Animation();
        fishbigleft.addFrane(fishbigleft1, 50);
        fishbigleft.addFrane(fishbigleft2, 50);
        fishbigleft.addFrane(fishbigleft3, 50);

        fishbigright = new Animation();
        fishbigright.addFrane(fishbigright1, 50);
        fishbigright.addFrane(fishbigright2, 50);
        fishbigright.addFrane(fishbigright3, 50);

        fishbigattackleft = new Animation();
        fishbigattackleft.addFrane(fishbigfightleft1, 50);
        fishbigattackleft.addFrane(fishbigfightleft2, 50);

        fishbigattackright = new Animation();
        fishbigattackright.addFrane(fishbigfightright1, 50);
        fishbigattackright.addFrane(fishbigfightright2, 50);
        imhealth = imhealth2;

    }

    @Override
    public void update() {
        centerX += speed;
        fishbigleft.update(3);
        fishbigright.update(3);
        fishbigattackleft.update(3);
        fishbigattackright.update(3);
//            spiderattackleft.update(3);
//            spiderattackright.update(3);
        r.setBounds(centerX + 40, centerY + 40, 30, 30);
        checkCollision();

    }

    private synchronized void checkCollision() {
        if (r.intersects(InterfaceGamemap2.getRobot().bound)) {
            if (InterfaceGamemap2.getRobot().getFight() == true) {
                if (health > 0) {
                    health -= 1;
                    InterfaceGamemap2.getRobot().setFight(false);
                    System.out.println(health);
                }
                if (health == 0) {
                    centerX = -300;
                    int score = InterfaceGamemap2.getRobot().getScore();
                    score += 1;
                    InterfaceGamemap2.getRobot().setScore(score);
                }
            }
            if (centerX > InterfaceGamemap2.getRobot().getcenterX()) {
                st = GameState1.FightLeft;
            } else {
                st = GameState1.FightRight;
            }
            int health = InterfaceGamemap2.getRobot().getHealth();
            health -= 1;
            InterfaceGamemap2.getRobot().setHealth(health);
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
            image = fishbigleft.getImage();
            speed = -1;
        } else if (state == GameState.Left && st == GameState1.NotFight) {
            image = fishbigright.getImage();
            speed = 1;
        }
        if (st == GameState1.FightLeft) {
            image = fishbigattackleft.getImage();
            speed = 0;
        } else if (st == GameState1.FightRight) {
            image = fishbigattackright.getImage();
            speed = 0;
        }
        switch (health) {
            case 6:
                imhealth = imhealth2;
                break;
            case 5:
                imhealth = imhealth3;
                break;
            case 4:
                imhealth = imhealth4;
                break;
            case 3:
                imhealth = imhealth5;
                break;
            case 2:
                imhealth = imhealth6;
                break;
            case 1:
                imhealth = imhealth7;
                break;
        }

    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawImage(image, centerX, centerY, null);
        g.drawImage(imhealth, centerX, centerY - 20, null);
//        g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        g.drawString(Integer.toString(health), 1315, vitri);
//        g.drawString(Integer.toString(speed), 1276, 477);
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

    public Image getImagehealth() {
        return imhealth;
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
