package datamap2;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import data.*;

public class EnemyFishPink extends Enemy {

    private int health, speed, centerX, centerY;
    private Image image, imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private boolean back = true, left, right, top, down;
    private Rectangle r;
    private Image fishpink1left, fishpink2left, fishpink3left, fishpink4left, fishpink5left, fishpink1right, fishpink2right, fishpink3right, fishpink4right, fishpink5right;
    private Image fishpinkfightleft, fishpinkfightleft1, fishpinkfightright, fishpinkfightright1;
    private Animation fishpinkleft, fishpinkright, fishpinkattackleft, fishpinkattackright;
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

    public EnemyFishPink(int centerX, int centerY, int speed, int health, ArrayList<Tile> array, int dem) throws IOException {
          switch (dem) {
            case 1:
                vitri = 150;
                break;
            case 2:
                vitri = 173;
                break;
            case 3:
                vitri = 192;
                break;
            case 4:
                vitri = 215;
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
        fishpink1left = ImageLibrary.loadImage("fishpink1left");
        fishpink2left = ImageLibrary.loadImage("fishpink2left");
        fishpink3left = ImageLibrary.loadImage("fishpink3left");
//        fishpink4left = ImageLibrary.loadImage("fishpink4left");
        fishpink5left = ImageLibrary.loadImage("fishpink5left");
        fishpink1right = ImageLibrary.loadImage("fishpink1right");
        fishpink2right = ImageLibrary.loadImage("fishpink2right");
        fishpink3right = ImageLibrary.loadImage("fishpink3right");
        fishpink4right = ImageLibrary.loadImage("fishpink4right");
        fishpink5right = ImageLibrary.loadImage("fishpink5right");

        fishpinkfightright = ImageLibrary.loadImage("fishpinkfightright");
        fishpinkfightright1 = ImageLibrary.loadImage("fishpinkfightright1");

        fishpinkfightleft = ImageLibrary.loadImage("fishpinkfightleft");
        fishpinkfightleft1 = ImageLibrary.loadImage("fishpinkfightleft1");

        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");
        fishpinkleft = new Animation();
        fishpinkleft.addFrane(fishpink1left, 50);
        fishpinkleft.addFrane(fishpink2left, 50);
        fishpinkleft.addFrane(fishpink3left, 50);
//        spiderleft.addFrane(fishpink4left, 50);
        fishpinkleft.addFrane(fishpink5left, 50);

        fishpinkright = new Animation();
        fishpinkright.addFrane(fishpink1right, 50);
        fishpinkright.addFrane(fishpink2right, 50);
        fishpinkright.addFrane(fishpink3right, 50);
        fishpinkright.addFrane(fishpink4right, 50);
        fishpinkright.addFrane(fishpink5right, 50);

        fishpinkattackleft = new Animation();
        fishpinkattackleft.addFrane(fishpinkfightleft, 50);
        fishpinkattackleft.addFrane(fishpinkfightleft1, 50);

        fishpinkattackright = new Animation();
        fishpinkattackright.addFrane(fishpinkfightright, 50);
        fishpinkattackright.addFrane(fishpinkfightright1, 50);

        imhealth = imhealth2;

    }

    @Override
    public void update() {
        centerX += speed;
        fishpinkleft.update(3);
        fishpinkright.update(3);
        fishpinkattackleft.update(3);
        fishpinkattackright.update(3);
//            spiderattackleft.update(3);
//            spiderattackright.update(3);
        r.setBounds(centerX + 30, centerY + 10, 40, 40);
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
                    centerX = -100;

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
            image = fishpinkleft.getImage();
            speed = -1;
        } else if (state == GameState.Left && st == GameState1.NotFight) {
            image = fishpinkright.getImage();
            speed = 1;
        }
        if (st == GameState1.FightLeft) {
            image = fishpinkattackleft.getImage();
            speed = 0;
        } else if (st == GameState1.FightRight) {
            image = fishpinkattackright.getImage();
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
