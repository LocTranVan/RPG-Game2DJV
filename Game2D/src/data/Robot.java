package data;

import images.ImageLibrary;
import utils.MusicLibrary;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author nhatt_000
 */
@SuppressWarnings("unused")
public class Robot {

	private final int GROUND = 450;
	private int centerX = 50, centerY = 450, speedX, speedY, ahealth, mana = 7;
	private int health = 100, score;
	private long a, recuperate, apower,b;
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rightfoot = new Rectangle(0, 0, 0, 0);
	public static Rectangle leftfoot = new Rectangle(0, 0, 0, 0);
	public static Rectangle bound = new Rectangle(0, 0, 0, 0);
	private Image s1down, s1top, s1right, s1left, imagebullet;
	private Image power0;
	private int bspeedX, bspeedY;
	private Image image, charactermoveright1, charactermoveright, charactermoveleft4, charactermovetop1,
			charactermovetop2, charactermovetop3, charactermovetop4, charactermovetop5, charactermoveright2,
			charactermoveright3, charactermoveright4, charactermoveleft, charactermoveleft1, charactermoveleft2,
			charactermoveleft3, fightleft, fightright;
	private Image characterfightright1, characterfightright2, characterfightright3, characterfighleft1,
			characterfighleft2, characterfighleft3, characterfightop1, characterfightop2, characterfightop3;
	private Image imhealth2, imhealth3, imhealth4, imhealth5, imhealth6, imhealth7, imhealth;
	private Image mana1, mana2, mana3, mana4, mana5, mana6, mana7, immana;
	private Animation robotAnimleft, robotAnimTop, robotAnimfightleft, robotAnimfightright, robotAnimfighttop,
			robotAnimright;
	private boolean right, left, top, down, move, fight, fk, fight1, bpower;
	private ArrayList<Bullet> arraybullet = new ArrayList<Bullet>();

	public Robot() throws IOException {
		init();
	}

	public void init() throws IOException {

		charactermoveright = ImageLibrary.loadImage("right1");
		charactermoveright1 = ImageLibrary.loadImage("right2");
		charactermoveright2 = ImageLibrary.loadImage("right3");
		charactermoveright3 = ImageLibrary.loadImage("right4");
		charactermoveright4 = ImageLibrary.loadImage("right5");

		charactermoveleft = ImageLibrary.loadImage("left1");
		charactermoveleft1 = ImageLibrary.loadImage("left2");
		charactermoveleft2 = ImageLibrary.loadImage("left3");
		charactermoveleft3 = ImageLibrary.loadImage("left4");
		charactermoveleft4 = ImageLibrary.loadImage("left5");

		charactermovetop1 = ImageLibrary.loadImage("lungright1");
		charactermovetop2 = ImageLibrary.loadImage("lungright2");
		charactermovetop3 = ImageLibrary.loadImage("lungright3");
		charactermovetop4 = ImageLibrary.loadImage("lungright4");
		charactermovetop5 = ImageLibrary.loadImage("lungright5");

		characterfighleft1 = ImageLibrary.loadImage("fightleft1");
		characterfighleft2 = ImageLibrary.loadImage("fightleft2");
		characterfighleft3 = ImageLibrary.loadImage("fightleft3");

		characterfightright1 = ImageLibrary.loadImage("fightright1");
		characterfightright2 = ImageLibrary.loadImage("fightright2");
		characterfightright3 = ImageLibrary.loadImage("fightright3");

		characterfightop1 = ImageLibrary.loadImage("fighttop1");
		characterfightop2 = ImageLibrary.loadImage("fighttop2");
		power0 = ImageLibrary.loadImage("power0");

		s1down = ImageLibrary.loadImage("s1down");
		s1left = ImageLibrary.loadImage("s1left");
		s1right = ImageLibrary.loadImage("s1right");
		s1top = ImageLibrary.loadImage("s1top");

		imhealth2 = ImageLibrary.loadImage("health2");
		imhealth3 = ImageLibrary.loadImage("health3");
		imhealth4 = ImageLibrary.loadImage("health4");
		imhealth5 = ImageLibrary.loadImage("health5");
		imhealth6 = ImageLibrary.loadImage("health6");
		imhealth7 = ImageLibrary.loadImage("health7");

		mana1 = ImageLibrary.loadImage("mana1");
		mana2 = ImageLibrary.loadImage("mana2");
		mana3 = ImageLibrary.loadImage("mana3");
		mana4 = ImageLibrary.loadImage("mana4");
		mana5 = ImageLibrary.loadImage("mana5");
		mana6 = ImageLibrary.loadImage("mana6");
		mana7 = ImageLibrary.loadImage("mana7");

		// fightleft = ImageLibrary.loadImage("fightleft");
		// fightright = ImageLibrary.loadImage("fightright");
		robotAnimleft = new Animation();
		robotAnimleft.addFrane(charactermoveleft1, 50);
		robotAnimleft.addFrane(charactermoveleft2, 50);
		robotAnimleft.addFrane(charactermoveleft3, 50);
		robotAnimleft.addFrane(charactermoveleft4, 50);
		robotAnimleft.addFrane(charactermoveleft, 50);

		robotAnimTop = new Animation();
		robotAnimTop.addFrane(charactermovetop1, 50);
		robotAnimTop.addFrane(charactermovetop2, 50);
		robotAnimTop.addFrane(charactermovetop3, 50);
		robotAnimTop.addFrane(charactermovetop4, 50);
		robotAnimTop.addFrane(charactermovetop5, 50);

		robotAnimfightleft = new Animation();
		robotAnimfightleft.addFrane(characterfighleft1, 50);
		robotAnimfightleft.addFrane(characterfighleft3, 50);

		robotAnimfightright = new Animation();
		robotAnimfightright.addFrane(characterfightright1, 50);
		robotAnimfightright.addFrane(characterfightright3, 50);

		robotAnimfighttop = new Animation();
		robotAnimfighttop.addFrane(characterfightop1, 50);
		robotAnimfighttop.addFrane(characterfightop2, 50);
		robotAnimright = new Animation();
		robotAnimright.addFrane(charactermoveright1, 50);
		robotAnimright.addFrane(charactermoveright2, 50);
		robotAnimright.addFrane(charactermoveright3, 50);
		robotAnimright.addFrane(charactermoveright4, 50);
		robotAnimright.addFrane(charactermoveright, 50);
		image = charactermoveright;
	}

	public void update() {

		centerX += speedX;
		centerY += speedY;

		if (right == true || down == true) {
			if (move == true && fight == false) {
				image = robotAnimright.getImage();
			} else if (fight == true) {
				image = robotAnimfightright.getImage();
			} else {
				image = charactermoveright;
				imagebullet = s1right;
				bspeedX = 4;
				bspeedY = 0;
			}
		}
		if (left == true) {
			if (move == true && fight == false) {
				image = robotAnimleft.getImage();

			} else if (fight == true) {
				image = robotAnimfightleft.getImage();
			} else {
				image = charactermoveleft;
				imagebullet = s1left;
				bspeedX = -6;
				bspeedY = 0;
			}
		}
		if (top == true) {
			if (move == true && fight == false) {
				image = robotAnimTop.getImage();

			} else if (fight == true) {
				image = robotAnimfighttop.getImage();
			} else {
				image = charactermovetop1;
				imagebullet = s1top;
			}
			bspeedX = 0;
			bspeedY = -6;
		}

		robotAnimleft.update(3);
		robotAnimright.update(3);
		robotAnimTop.update(3);
		robotAnimfightleft.update(3);
		robotAnimfightright.update(3);
		robotAnimfighttop.update(3);
		recuperateHealth();
		for (int i = 0; i < arraybullet.size(); i++) {
			Bullet b = arraybullet.get(i);
			b.update();
		}

		bound.setBounds(centerX, centerY, 70, 70);
		rect.setRect(centerX + 28, centerY + 55, 10, 10);
		rect2.setRect(centerX + 28, centerY + 20, 10, 10);
		leftfoot.setRect(centerX + 45, centerY + 30, 10, 10);
		rightfoot.setRect(centerX + 5, centerY + 30, 10, 10);
		ImageHealth();
		ImageMana();
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, centerX, centerY, null);
		g.drawImage(immana, centerX + 10, centerY - 3, null);
		g.drawImage(imhealth, centerX, centerY - 20, null);
		g.setColor(Color.DARK_GRAY);
                g.drawString(Integer.toString(health), 1315, 45);
		g.drawString(Integer.toString(mana), 1315, 73);
		g.drawString(Integer.toString(speedX), 1315, 99);
		for (int i = 0; i < arraybullet.size(); i++) {
			Bullet b = arraybullet.get(i);
			g.drawImage(b.getImage(), b.getCenterX(), b.getCenterY(), null);
//			g.drawRect((int)b.getRect().getX(), (int)b.getRect().getY(), (int)b.getRect().getWidth(), (int)b.getRect().getHeight());
		}
		if (bpower) {

			g.drawImage(power0, centerX - 18, centerY - 60, null);
			// g.drawImage(power0, centerX - 25, centerY - 40, null);
			health = ahealth;

			if (System.currentTimeMillis() - b > 3000) {

				bpower = false;
			}
		}
		// g.drawString(Integer.toString(speedX), 1276, 98);
		// g.drawImage(robotAnimleft.getImage(), robot.getcenterX(),
		// robot.getcenterY(), this);
		// g.drawRect((int) rect.getX(), (int) rect.getY(), (int)
		// rect.getHeight(), (int) rect.getWidth());
		// g.drawRect((int) rect2.getX(), (int) rect2.getY(), (int)
		// rect2.getHeight(), (int) rect2.getWidth());
		// g.drawRect((int) leftfoot.getX(), (int) leftfoot.getY(), (int)
		// leftfoot.getHeight(), (int) leftfoot.getWidth());
		// g.drawRect((int) rightfoot.getX(), (int) rightfoot.getY(), (int)
		// rightfoot.getHeight(), (int)rightfoot.getWidth());
		// g.drawRect((int) bound.getX(), (int)bound.getY(), (int)
		// bound.getHeight(), (int) bound.getWidth());
	}

	public void ImageHealth() {

		switch (health) {
		case 100:
			imhealth = imhealth2;
			break;
		case 80:
			imhealth = imhealth3;
			break;
		case 60:
			imhealth = imhealth4;
			break;
		case 20:
			imhealth = imhealth5;
			break;
		case 10:
			imhealth = imhealth6;
			break;
		case 0:
			imhealth = imhealth7;
			break;
		}
	}

	public void ImageMana() {

		switch (mana) {
		case 7:
			immana = mana1;
			break;
		case 6:
			immana = mana2;
			break;
		case 5:
			immana = mana3;
			break;
		case 4:
			immana = mana4;
			break;
		case 3:
			immana = mana5;
			break;
		case 2:
			immana = mana6;
			break;
		case 1:
			immana = mana7;
			break;
		case 0 :
			immana = mana7;
			break;
		}
	}

	public void moveLeft() {
		speedX = -3;
		// tam = speedX;
		// image = robotAnimleft.getImage();
		move = true;
		left = true;
		down = false;
		top = false;
		right = false;
	}

	public void moveRight() {
		speedX = 3;

		// image = robotAnimright.getImage();
		right = true;
		down = false;
		top = false;
		left = false;
	}

	public void movetop() {
		speedY = -3;

		top = true;
		// image = robotAnimTop.getImage();
		// move = true;
		down = false;
		left = false;
		right = false;
	}

	public void movebottom() {
		// image = robotAnimright.getImage();
		speedY = 3;
		// tam = speedY;
		down = true;
		// move = true;
		left = false;
		top = false;
		right = false;
	}

	public void stop() {
		speedX = 0;
		speedY = 0;
		fight = false;
		fight1 = false;
	}

	public void shoot() {
		fight = true;

		fight1 = true;

	}

	public void power() {
		if (mana > 0) {
			bpower = true;
			ahealth = health;
			mana -= 1;
			b = System.currentTimeMillis();
		}
	}

	public void setpower(boolean b) {

	}

	public void recuperateHealth() {
		if (health < 100) {
			if (fight == false && ((System.currentTimeMillis() - recuperate) > 1000))
				health += 4;
			recuperate = System.currentTimeMillis();
		}
	}

	public void fight() {
		if ((System.currentTimeMillis() - a) > 1000 && (mana > 0)) {
			Bullet b = new Bullet(centerX, centerY + 10, bspeedX, bspeedY, 0, imagebullet);
			arraybullet.add(b);
			MusicLibrary.music("tilechange");
			a = System.currentTimeMillis();
			mana -= 1;
		}

	}

	public ArrayList<Bullet> getArrayBullet() {
		return arraybullet;
	}

	public int getcenterX() {
		return centerX;
	}

	public int getcenterY() {
		return centerY;
	}

	public int getspeedX() {
		return speedX;
	}

	public int getspeedY() {
		return speedY;
	}

	public boolean getFight() {
		return fight1;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setFight(boolean fight1) {
		this.fight1 = fight1;
	}

	public int getHealth() {
		return health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setcenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setcenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setspeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setspeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setmove(boolean move) {
		this.move = move;
	}

	public Image getImage() {
		return image;
	}

}
