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
public class EnemyGirl extends Enemy {

    private int centerX, centerY, speed, health;
    private long stick;
    private int i;
//    private StarttingClass st;
    private Image image, girlleft1, girlleft2, girlleft3, girlleft4, girlleft5, girlleft6;
    private Image girlright1, girlright2, girlright3, girlright4, girlright5, girlright6;
    private Image girlstand, girlstand2;
    private Image girlbulletleft, girlbulletright, imagebullet;
    private Image imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private Animation robotAnimGirl;
    private Robot robot;
    private Animation girlleft, girlright, angirlstand;
    private static ArrayList<Bullet> girlbulles = new ArrayList<Bullet>();
    private boolean fight;
    private int cnt;
    private Rectangle r;
    private int vitri;

    private enum enemygirl {

        dead, live
    }
    enemygirl state;

    public EnemyGirl(int centerX, int centerY, int speed, int health, Robot robot, int dem) throws IOException {
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
        this.robot = robot;
        this.centerX = centerX;
        this.centerY = centerY;
        this.speed = speed;
        this.health = health;
        state = enemygirl.live;
        r = new Rectangle(0, 0, 0, 0);
        init();

    }

    public void init() throws IOException {
        girlleft1 = ImageLibrary.loadImage("girlleft1");
        girlleft2 = ImageLibrary.loadImage("girlleft2");
//         girlleft3 = ImageLibrary.loadImage("girlleft3");
        girlleft4 = ImageLibrary.loadImage("girlleft4");
        girlleft5 = ImageLibrary.loadImage("girlleft5");
        girlleft6 = ImageLibrary.loadImage("girlleft6");

        girlright1 = ImageLibrary.loadImage("girlright1");
        girlright2 = ImageLibrary.loadImage("girlright2");
        girlright3 = ImageLibrary.loadImage("girlright3");
        girlright4 = ImageLibrary.loadImage("girlright4");
        girlright5 = ImageLibrary.loadImage("girlright5");
        girlright6 = ImageLibrary.loadImage("girlright6");

        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");

        girlstand = ImageLibrary.loadImage("girlstand");
        girlstand2 = ImageLibrary.loadImage("girlstand2");

        girlbulletleft = ImageLibrary.loadImage("girlbulletleft");
        girlbulletright = ImageLibrary.loadImage("girlbulletright");

        girlleft = new Animation();
        girlleft.addFrane(girlleft1, 50);
        girlleft.addFrane(girlleft2, 50);
//        girlleft.addFrane(girlleft3, 50);
        girlleft.addFrane(girlleft4, 50);
        girlleft.addFrane(girlleft5, 50);
        girlleft.addFrane(girlleft6, 50);

        girlright = new Animation();
        girlright.addFrane(girlright1, 50);
        girlright.addFrane(girlright2, 50);
        girlright.addFrane(girlright3, 50);
        girlright.addFrane(girlright4, 50);
        girlright.addFrane(girlright5, 50);
        girlright.addFrane(girlright6, 50);

        angirlstand = new Animation();
        angirlstand.addFrane(girlstand, 50);
        angirlstand.addFrane(girlstand2, 50);

    }

    public void update() {
        if (state == enemygirl.live) {
            girlleft.update(3);
            girlright.update(3);
            angirlstand.update(3);
            r.setBounds(centerX, centerY, 50, 50);
            checkCollision();
            checkImagegirlandaddBullet();

            ImageHealth();
        }
        ApdateandRemoveBullet();
    }

    private void checkCollision() {
        if (r.intersects(robot.bound)) {
            if (robot.getFight() == true) {
                checkHealth();
            }
        }
        for (int i = 0; i < robot.getArrayBullet().size(); i++) {
            Bullet b = robot.getArrayBullet().get(i);
            if (r.intersects(b.getRect())) {
                health -= 1;
                robot.getArrayBullet().remove(i);
                checkHealth();
            }

        }

    }

    public void checkImagegirlandaddBullet() {
        if (robot.getcenterY() > centerY - 40 && robot.getcenterY() < centerY + 60) {

            if (robot.getcenterX() > centerX) {
                i++;
                image = girlleft.getImage();
                if (i == 90) {
                    Bullet a = new Bullet(centerX + 20, centerY + 20, 4, 0, 1, girlbulletright, robot);
                    girlbulles.add(a);
                    i = 0;
                }
            } else {

                image = girlright.getImage();
                i++;
                if (i == 90) {
                    Bullet b = new Bullet(centerX - 10, centerY + 20, -4, 0, 1, girlbulletleft, robot);
                    girlbulles.add(b);
                    i = 0;
                }

            }
        } else {
            image = angirlstand.getImage();
        }
    }

    public void ApdateandRemoveBullet() {
        if (girlbulles.size() > 0) {
            for (int i = 0; i < girlbulles.size(); i++) {
                Bullet m = girlbulles.get(i);
                m.update();
                if (m.getCenterX() < 50 || m.getCenterX() > 950 || m.getTime() == 30) {
                    girlbulles.remove(i);
                }
            }
        }
    }

    public void checkScore() {
        int score = robot.getScore();
        score += 1;
        robot.setScore(score);
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
            state = enemygirl.dead;
            checkScore();

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

    public Animation getAinmGirl() {
        return robotAnimGirl;
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, centerX, centerY, null);
        g.setColor(Color.DARK_GRAY);
        g.drawString(Integer.toString(health), 1315, vitri);
//        g.drawString(Integer.toString(speed), 1276, 190);
        g.drawImage(imhealth, centerX, centerY - 20, null);
        for (int i = 0; i < girlbulles.size(); i++) {
            Bullet b = girlbulles.get(i);
            g.drawImage(b.getImage(), b.getCenterX(), b.getCenterY(), null);
//            g.drawImage(b.getImage(),b.getCenterX() , b.getCenterY(), this);
        }
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getSpeed() {
        return speed;

    }

    public int getHealth() {
        return health;
    }

    public Image getImagehealth() {
        return imhealth;
    }

    public Image getImage() {
        return image;
    }

    public boolean getFight() {
        return fight;
    }

    public static ArrayList getBulles() {
        return girlbulles;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
