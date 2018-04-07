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
public class EnemyEagle extends Enemy {

    private int health, speed, centerX, centerY;
    private int i;
    private Image image;
    private static boolean top, down, left, right;
    public Rectangle r;
    private Image egalup1, egalup2, egalup3, egaldown1, egaldown2, egaldown3;
    private Image imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private Image egalbullet, egalbulletdown, egalbullettop;
    private Animation egalup, egaldown;
    private Robot robot;
    private static ArrayList<Bullet> egalbulles = new ArrayList<Bullet>();
    private ArrayList<Tile> array;
//    private ArrayList<Tile> array = InterfaceGame.getArraytile();
    private int vitri;
    private enum GameState {

        Top, Down
    }
    GameState state;

    private enum GameState1 {

        FightTop, FightDown, NotFight
    }
    GameState1 st;

    public EnemyEagle(int centerX, int centerY, int speed, int health, ArrayList<Tile> array,Robot robot, int dem) throws IOException {
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
        this.robot = robot;
        this.array = array;
        this.centerX = centerX;
        this.centerY = centerY;
        this.speed = speed;
        state = GameState.Top;
//        st = GameState1.NotFight;
        this.health = health;
        r = new Rectangle(0, 0, 0, 0);

        init();
    }

    public void init() throws IOException {
        egaldown1 = ImageLibrary.loadImage("egaldown1");
        egaldown2 = ImageLibrary.loadImage("egaldown2");
        egaldown3 = ImageLibrary.loadImage("egaldown3");
        egalup1 = ImageLibrary.loadImage("egalup1");
        egalup2 = ImageLibrary.loadImage("egalup2");
        egalup3 = ImageLibrary.loadImage("egalup3");

        egalbullet = ImageLibrary.loadImage("egalbullet");
        egalbulletdown = ImageLibrary.loadImage("egalbulletdown");
        egalbullettop = ImageLibrary.loadImage("egalbullettop");

        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");

        egaldown = new Animation();
        egaldown.addFrane(egaldown1, 50);
        egaldown.addFrane(egaldown2, 50);
        egaldown.addFrane(egaldown3, 50);

        egalup = new Animation();
        egalup.addFrane(egalup1, 50);
        egalup.addFrane(egalup2, 50);
        egalup.addFrane(egalup3, 50);

    }

    public void update() {
        centerY += speed;
        r.setBounds(centerX + 10, centerY, 50, 50);

        for (int i = 0; i < egalbulles.size(); i++) {
            Bullet b1 = egalbulles.get(i);
            b1.update();
            if (b1.getCenterY() > 500 || b1.getCenterY() < 50 || b1.getTime() == 3) {
                egalbulles.remove(i);

            }
        }
        egaldown.update(3);
        egalup.update(3);
        checkCollision();
        checkBullet();
        ImageHealth();
        checkImageandSpeed();
    }

    public void checkCollision() {
        checkStatus();
        for (int i = 0; i < array.size(); i++) {
            Tile t = array.get(i);
            if (r.intersects(t.getRectangle()) && t.getType() != 1) {
                if (state == GameState.Top) {

                    state = GameState.Down;

                } else {

                    state = GameState.Top;

                }

            }
        }

    }

    public void paint(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.drawImage(image, centerX, centerY, null);
        g.drawImage(imhealth, centerX, centerY - 20, null);
        g.drawString(Integer.toString(health), 1315 , vitri);
//        g.drawString(Integer.toString(speed), 1276, 477);
        for (int i = 0; i < egalbulles.size(); i++) {
            Bullet b1 = egalbulles.get(i);
            g.drawImage(b1.getImage(), b1.getCenterX(), b1.getCenterY(), null);
        }
    }

    public void checkImageandSpeed() {
        if (state == GameState.Top && st == GameState1.NotFight) {
            image = egalup.getImage();
            speed = -1;
        } else if (state == GameState.Down && st == GameState1.NotFight) {
            image = egaldown.getImage();
            speed = 1;
        }
        if (st == GameState1.FightTop) {
            image = egalup.getImage();
            speed = 0;
        } else if (st == GameState1.FightDown) {
            image = egaldown.getImage();
            speed = 0;
        }
    }

    public void checkStatus() {
        if (r.intersects(robot.bound)) {
            if (centerY > robot.getcenterY()) {
                st = GameState1.FightTop;
            } else {
                st = GameState1.FightDown;
            }
        } else {
            st = GameState1.NotFight;
        }

    }

    public void checkHealth() {
        if (robot.getFight() == true) {
            if (health > 0) {
                health -= 1;
                robot.setFight(false);
                System.out.println(health);
            }
            if (health == 0) {
                centerX = -100;
//            state = e.dead;

            }
        }
    }

    public void ImageHealth() {
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

    public void checkBullet() {
        if ((Math.abs(robot.getcenterY() - centerY) < 500) && (Math.abs(robot.getcenterX() - centerX) < 100)) {
            i++;
            if (i == 90) {
                if (state == GameState.Top.Top) {
                    Bullet b = new Bullet(centerX, centerY, 0, -5, 1, egalbulletdown,robot);
                    egalbulles.add(b);
                    i = 0;
                } else {
                    Bullet b2 = new Bullet(centerX, centerY, 0, 5, 1, egalbullettop,robot);
                    egalbulles.add(b2);
                    i = 0;
                }
            }
        }
    }

    public void die() {

    }

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

    public Image getImage() {
        return image;
    }

    public static ArrayList getBulles() {
        return egalbulles;
    }

    public Rectangle getRect() {
        return r;
    }

    public Image getImagehealth() {
        return imhealth;
    }

    public void setHealth(int health) {
        this.health = health;
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
