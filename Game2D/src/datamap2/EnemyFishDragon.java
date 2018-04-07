package datamap2;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import data.*;

public class EnemyFishDragon extends Enemy {

    private int health, speed, centerX, centerY;
    private Image image, imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private boolean back = true, left, right, top, down;
    private Rectangle r;
    private Image fishdragonleft1, fishdragonleft2, fishdragonleft3, fishdragonleft4, fishdragonleft5, fishdragonright1, fishdragonright2, fishdragonright3, fishdragonright4, fishdragonright5;
    private Image fishdragonfightleft1, fishdragonfightleft2, fishdragonfightright1, fishdragonfightright2;
    private Animation fishdragonleft, fishdragonright, fishdragonattackleft, fishdragonattackright;
    private ArrayList<Tile> array;
//    private ArrayList<Tile2> array = InterfaceGamemap2.getArraytile();

    private enum GameState {

        Right, Left
    }
    GameState state;
    private int vitri;
    private enum GameState1 {

        FightLeft, FightRight, NotFight
    }
    GameState1 st;

    public EnemyFishDragon(int centerX, int centerY, int speed, int health, ArrayList<Tile> array, int dem) throws IOException {
       switch (dem) {
            case 1:
                vitri = 258;
                break;
            case 2:
                vitri = 278;
                break;
            case 3:
                vitri = 301;
                break;
            case 4:
                vitri = 323;
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
        fishdragonleft1 = ImageLibrary.loadImage("fishdragonleft1");
        fishdragonleft2 = ImageLibrary.loadImage("fishdragonleft2");
        fishdragonleft3 = ImageLibrary.loadImage("fishdragonleft3");
        fishdragonleft4 = ImageLibrary.loadImage("fishdragonleft4");
        fishdragonleft5 = ImageLibrary.loadImage("fishdragonleft5");

        fishdragonright1 = ImageLibrary.loadImage("fishdragonright1");
        fishdragonright2 = ImageLibrary.loadImage("fishdragonright2");
        fishdragonright3 = ImageLibrary.loadImage("fishdragonright3");
        fishdragonright4 = ImageLibrary.loadImage("fishdragonright4");
        fishdragonright5 = ImageLibrary.loadImage("fishdragonright5");

        fishdragonfightleft1 = ImageLibrary.loadImage("fishdragonfightleft1");
        fishdragonfightleft2 = ImageLibrary.loadImage("fishdragonfightleft2");

        fishdragonfightright1 = ImageLibrary.loadImage("fishdragonfightright1");
        fishdragonfightright2 = ImageLibrary.loadImage("fishdragonfightright2");

        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");
        fishdragonleft = new Animation();

        fishdragonleft.addFrane(fishdragonleft1, 50);
        fishdragonleft.addFrane(fishdragonleft2, 50);
        fishdragonleft.addFrane(fishdragonleft3, 50);
        fishdragonleft.addFrane(fishdragonleft4, 50);
        fishdragonleft.addFrane(fishdragonleft5, 50);

        fishdragonright = new Animation();
        fishdragonright.addFrane(fishdragonright1, 50);
        fishdragonright.addFrane(fishdragonright2, 50);
        fishdragonright.addFrane(fishdragonright3, 50);
        fishdragonright.addFrane(fishdragonright4, 50);
        fishdragonright.addFrane(fishdragonright5, 50);

        fishdragonattackright = new Animation();
        fishdragonattackright.addFrane(fishdragonfightright1, 50);
        fishdragonattackright.addFrane(fishdragonfightright2, 50);

        fishdragonattackleft = new Animation();
        fishdragonattackleft.addFrane(fishdragonfightleft1, 50);
        fishdragonattackleft.addFrane(fishdragonfightleft2, 50);

        imhealth = imhealth2;

    }

    @Override
    public void update() {
        centerX += speed;
        fishdragonleft.update(3);
        fishdragonright.update(3);
        fishdragonattackleft.update(3);
        fishdragonattackright.update(3);
        r.setBounds(centerX + 30, centerY + 50, 50, 50);
        checkCollision();

    }

    private synchronized void checkCollision() {
        if (r.intersects(InterfaceGamemap2.getRobot().bound)) {
            if (InterfaceGamemap2.getRobot().getFight() == true) {
                if (health > 0) {
                    health -= 1;
                    InterfaceGamemap2.getRobot().setFight(false);
//                    System.out.println(health);
                }
                if (health == 0) {
                    centerX = -300;

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
            if (t.getRectangle().intersects(r) && t.getType() == 2) {
                if (state == GameState.Right) {

                    state = GameState.Left;

                } else {

                    state = GameState.Right;

                }

            }
        }

//        for (int i = 0; i < array.size(); i++) {
//            Tile t = array.get(i);
//            if (r.intersects(t.getRectangle()) && t.getType() == 2) {
//                if (state == GameState.Right) {
//
//                    state = GameState.Left;
//
//                } else {
//
//                    state = GameState.Right;
//
//                }
//
//            }
//        }
        if (state == GameState.Right && st == GameState1.NotFight) {
            image = fishdragonleft.getImage();
            speed = -1;
        } else if (state == GameState.Left && st == GameState1.NotFight) {
            image = fishdragonright.getImage();
            speed = 1;
        }
        if (st == GameState1.FightLeft) {
            image = fishdragonattackleft.getImage();
            speed = 0;
        } else if (st == GameState1.FightRight) {
            image = fishdragonattackright.getImage();
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
//    public static  void setback(boolean back){
//        
//    }

    public Rectangle getRect() {
        return r;
    }

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
