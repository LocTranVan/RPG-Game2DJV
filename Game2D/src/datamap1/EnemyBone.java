package datamap1;

import images.ImageLibrary;
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
public class EnemyBone extends Enemy {

    private long a;
    private int centerX, centerY, speed, health;
    private boolean left = true, right = false;
    private Animation robotXuongleft, robotXuongright, robotXuongattackleft, robotXuongattackright;
    private Image image, enboneleft1, enboneleft2, enboneleft3, enboneleft4, enboneleft5, enboneRight1, enboneRight2,
            enboneRight3, enboneRight4, enboneRight5;
    private Image imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
    private Image xuongattackleft, xuongattackleft1, xuongattackright, xuongattackright1;
    private Robot robot;
    private Rectangle r;
    private ArrayList<Tile> array;
    // private ArrayList<Tile> array = InterfaceGame.getArraytile();
    private int vitri;

    private enum enemybone {

        dead, live
    }

    enemybone enehealth;

    private enum GameState {

        Right, left
    }

    GameState state;

    private enum GameState1 {

        FightLeft, FightRight, NotFight
    }

    GameState1 st;

    public EnemyBone(int centerX, int centerY, int speed, int health, ArrayList<Tile> array, Robot robot, int dem)
            throws IOException {
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
        this.robot = robot;
        this.array = array;
        this.centerX = centerX;
        this.centerY = centerY;
        this.speed = speed;
        this.health = health;
        state = GameState.Right;
        enehealth = enemybone.live;
        r = new Rectangle(0, 0, 0, 0);
        init();
    }

    public void init() throws IOException {
        enboneleft1 = ImageLibrary.loadImage("xuongleft1");

        enboneleft2 = ImageLibrary.loadImage("xuongleft2");
        enboneleft3 = ImageLibrary.loadImage("xuongleft3");
        enboneleft4 = ImageLibrary.loadImage("xuongleft4");
        enboneleft5 = ImageLibrary.loadImage("xuongleft5");

        enboneRight1 = ImageLibrary.loadImage("xuongright1");
        enboneRight2 = ImageLibrary.loadImage("xuongright2");
        enboneRight3 = ImageLibrary.loadImage("xuongright3");
        enboneRight4 = ImageLibrary.loadImage("xuongright4");
        enboneRight5 = ImageLibrary.loadImage("xuongright5");

        xuongattackleft = ImageLibrary.loadImage("xuongattackleft");
        xuongattackleft1 = ImageLibrary.loadImage("xuongattackleft1");
        xuongattackright = ImageLibrary.loadImage("xuongattackright");
        xuongattackright1 = ImageLibrary.loadImage("xuongattackright1");
        imhealth2 = ImageLibrary.loadImage("health2");
        imhealth3 = ImageLibrary.loadImage("health3");
        imhealth4 = ImageLibrary.loadImage("health4");
        imhealth5 = ImageLibrary.loadImage("health5");
        imhealth6 = ImageLibrary.loadImage("health6");
        imhealth7 = ImageLibrary.loadImage("health7");
        robotXuongleft = new Animation();
        robotXuongleft.addFrane(enboneleft1, 50);
        robotXuongleft.addFrane(enboneleft2, 50);
        robotXuongleft.addFrane(enboneleft3, 50);
        robotXuongleft.addFrane(enboneleft4, 50);

        robotXuongright = new Animation();
        robotXuongright.addFrane(enboneRight1, 50);
        robotXuongright.addFrane(enboneRight2, 50);
        robotXuongright.addFrane(enboneRight3, 50);
        robotXuongright.addFrane(enboneRight4, 50);

        robotXuongattackleft = new Animation();
        robotXuongattackleft.addFrane(xuongattackleft, 50);
        robotXuongattackleft.addFrane(xuongattackleft1, 50);

        robotXuongattackright = new Animation();
        robotXuongattackright.addFrane(xuongattackright, 50);
        robotXuongattackright.addFrane(xuongattackright1, 50);
        // image = robotXuongleft.getImage();
    }

    @Override
    public void update() {
        if (enehealth == enemybone.live) {
            centerX += speed;
            r.setBounds(centerX, centerY + 20, 60, 20);
            robotXuongleft.update(3);
            robotXuongright.update(3);
            robotXuongattackleft.update(3);
            robotXuongattackright.update(3);
            checkStatus();
            checkCollision();
            ImageHealth();
            checkImageandSpeed();
        }
    }

    private void checkCollision() {
        if (r.intersects(robot.bound)) {
            checkHealth();
            if (centerX > robot.getcenterX()) {
                setSP(GameState1.FightLeft);

            } else {
                setSP(GameState1.FightRight);
            }
            if (System.currentTimeMillis() - a > 100) {
                int health = robot.getHealth();
                health -= 1;
                robot.setHealth(health);
                a = System.currentTimeMillis();
            }
        } else {
            setSP(GameState1.NotFight);
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

    public void checkStatus() {
        for (int i = 0; i < array.size(); i++) {
            Tile t = array.get(i);
            if (r.intersects(t.getRectangle()) && t.getType() != 1) {
                if (state == GameState.Right) {

                    state = GameState.left;

                } else {

                    state = GameState.Right;

                }

            }
        }
    }

    public void paint(Graphics2D g) {

        g.drawImage(image, centerX, centerY, null);
        g.drawImage(imhealth, centerX, centerY - 20, null);
        g.drawString(Integer.toString(health), 1315, vitri);
//        g.drawString(Integer.toString(speed), 1276, 383);
		// g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(),
        // (int)r.getHeight());
        // g.drawImage(enemybone2.getImage(), enemybone2.getCenterX(),
        // enemybone2.getCenterY(), this);
        // g.drawImage(enemybone2.getImagehealth(), enemybone2.getCenterX(),
        // enemybone2.getCenterY() - 20, this);
    }

    public void checkImageandSpeed() {
        if (st == GameState1.FightLeft) {
            image = robotXuongattackleft.getImage();
            speed = 0;
        } else if (st == GameState1.FightRight) {
            image = robotXuongattackright.getImage();
            speed = 0;
        }
        if (state == GameState.Right && st == GameState1.NotFight) {
            image = robotXuongleft.getImage();
            speed = -1;
        } else if (state == GameState.left && st == GameState1.NotFight) {
            image = robotXuongright.getImage();
            speed = 1;
        }
    }

    public void checkHealth() {
        if (robot.getFight() == true) {
            if (health > 0) {
                health -= 1;
                robot.setFight(false);
                System.out.println(health);
            }

        }
        if (health == 0) {
            centerX = -100;
            checkScore();
            int mana = robot.getMana();
            mana += 1;
            robot.setMana(mana);
            enehealth = enemybone.dead;

        }

    }

    public void checkScore() {
        int score = robot.getScore();
        score += 1;
        robot.setScore(score);
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

    public Image getImagehealth() {
        return imhealth;
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

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public Rectangle getRect() {
        return r;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setSP(GameState1 st) {
        this.st = st;
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

    public Image getImage() {

        return image;
    }
}
