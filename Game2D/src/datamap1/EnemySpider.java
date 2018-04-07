package datamap1;

import images.ImageLibrary;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import data.*;

/**
 *
 * @author nhatt_000
 */
public class EnemySpider extends Enemy {

    private int health, speed, centerX, centerY;
    private long a;
    private Image image, imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private boolean back = true, left, right, top, down;
    private Rectangle r;
    private Image spiderleft1, spiderleft2, spiderleft3, spiderleft4, spiderleft5, spiderleft6, spiderright1, spiderright2, spiderright3, spiderright4, spiderright5, spiderright6;
    private Image spiderattackleft1, spiderattackleft2, spiderattackleft3, spiderattackleft4, spiderattackright1, spiderattackright2, spiderattackright3, spiderattackright4;
    private Animation spiderleft, spiderright, spiderattackleft, spiderattackright;
    private ArrayList<Tile> array;
//    private ArrayList<Tile> array =InterfaceGame.getArraytile();
    private Robot robot;

    private enum GameState {

        Right, Left
    }
    GameState state;
    private int vitri;

    private enum GameState1 {

        FightLeft, FightRight, NotFight
    }
    GameState1 st;

    public EnemySpider(int centerX, int centerY, int speed, int health, ArrayList<Tile> array, Robot robot, int dem) throws IOException {
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
        spiderleft1 = ImageLibrary.loadImage("spiderleft1");
        spiderleft2 = ImageLibrary.loadImage("spiderleft2");
        spiderleft3 = ImageLibrary.loadImage("spiderleft3");
        spiderleft4 = ImageLibrary.loadImage("spiderleft4");
        spiderleft5 = ImageLibrary.loadImage("spiderleft5");

        spiderright1 = ImageLibrary.loadImage("spiderright1");
        spiderright2 = ImageLibrary.loadImage("spiderright2");
        spiderright3 = ImageLibrary.loadImage("spiderright3");
        spiderright4 = ImageLibrary.loadImage("spiderright4");
        spiderright5 = ImageLibrary.loadImage("spiderright5");

        spiderattackleft1 = ImageLibrary.loadImage("spiderattackleft1");
        spiderattackleft2 = ImageLibrary.loadImage("spiderattackleft2");
        spiderattackleft3 = ImageLibrary.loadImage("spiderattackleft3");
        spiderattackleft4 = ImageLibrary.loadImage("spiderattackleft4");

        spiderattackright1 = ImageLibrary.loadImage("spiderattackright1");
        spiderattackright2 = ImageLibrary.loadImage("spiderattackright2");
        spiderattackright3 = ImageLibrary.loadImage("spiderattackright3");
        spiderattackright4 = ImageLibrary.loadImage("spiderattackright4");

        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");
        spiderleft = new Animation();
        spiderleft.addFrane(spiderleft1, 50);
        spiderleft.addFrane(spiderleft2, 50);
        spiderleft.addFrane(spiderleft3, 50);
        spiderleft.addFrane(spiderleft4, 50);
        spiderleft.addFrane(spiderleft5, 50);

        spiderright = new Animation();
        spiderright.addFrane(spiderright1, 50);
        spiderright.addFrane(spiderright2, 50);
        spiderright.addFrane(spiderright3, 50);
        spiderright.addFrane(spiderright4, 50);
        spiderright.addFrane(spiderright5, 50);

        spiderattackleft = new Animation();
        spiderattackleft.addFrane(spiderattackleft1, 50);
        spiderattackleft.addFrane(spiderattackleft2, 50);
        spiderattackleft.addFrane(spiderattackleft3, 50);
        spiderattackleft.addFrane(spiderattackleft4, 50);

        spiderattackright = new Animation();
        spiderattackright.addFrane(spiderattackright1, 50);
        spiderattackright.addFrane(spiderattackright2, 50);
        spiderattackright.addFrane(spiderattackright3, 50);
        spiderattackright.addFrane(spiderattackright4, 50);
        imhealth = imhealth2;

    }

    @Override
    public void update() {
        centerX += speed;
        spiderleft.update(3);
        spiderright.update(3);
        spiderattackleft.update(3);
        spiderattackright.update(3);
        r.setBounds(centerX, centerY, 50, 50);
        checkCollision();

    }

    private synchronized void checkCollision() {
        if (r.intersects(robot.bound)) {
            if (robot.getFight() == true) {
                checkHealth();
            }
            if (centerX > robot.getcenterX()) {
                st = GameState1.FightLeft;
            } else {
                st = GameState1.FightRight;
            }
            if (System.currentTimeMillis() - a > 100) {
                int health = robot.getHealth();
                health -= 1;
                robot.setHealth(health);
                a = System.currentTimeMillis();
            }
        } else {
            st = GameState1.NotFight;
        }
        for (int i = 0; i < robot.getArrayBullet().size(); i++) {
            Bullet b = robot.getArrayBullet().get(i);
            if (r.intersects(b.getRect())) {
                health -= 1;
                robot.getArrayBullet().remove(i);
                checkHealth();
            }

        }

        for (int i = 0; i < array.size(); i++) {
            Tile t = array.get(i);
            if (r.intersects(t.getRectangle()) && t.getType() != 1) {
                if (state == GameState.Right) {

                    state = GameState.Left;

                } else {

                    state = GameState.Right;

                }

            }
        }

        if (state == GameState.Right && st == GameState1.NotFight) {
            image = spiderleft.getImage();
            speed = -1;
        } else if (state == GameState.Left && st == GameState1.NotFight) {
            image = spiderright.getImage();
            speed = 1;
        }
        if (st == GameState1.FightLeft) {
            image = spiderattackleft.getImage();
            speed = 0;
        } else if (st == GameState1.FightRight) {
            image = spiderattackright.getImage();
            speed = 0;
        }

    }

    public void checkHealth() {
        if (health > 0) {
            health -= 1;
            robot.setFight(false);
            System.out.println(health);
        }
        if (health == 0) {
            centerX = -100;
            int mana = robot.getMana();
            mana += 1;
            robot.setMana(mana);
            checkScore();

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

    public void checkScore() {
        int score = robot.getScore();
        score += 1;
        robot.setScore(score);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawImage(image, centerX, centerY, null);
        g.drawImage(imhealth, centerX, centerY - 20, null);
//        g.drawRect(speed, speed, health, health);
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
