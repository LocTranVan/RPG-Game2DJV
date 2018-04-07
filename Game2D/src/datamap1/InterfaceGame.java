package datamap1;

import images.ImageLibrary;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MusicLibrary;
import data.*;
import gamestate.GameState;
import manager.GameStateManager;

/**
 *
 * @author nhatt_000
 */
public class InterfaceGame extends GameState implements KeyListener {

	private Image background, image, huyhieu, port0;
	private Graphics second;
	private static ArrayList<Tile> arraytile;
	private static Robot robot;
	private MapData map;
	private EnemyBone enemybone1, enemybone2;
	private EnemyGirl enemygirl;
	private EnemySpider enemyspider1, enemyspider2;
	private EnemyEagle enemyeagle, enemyeagle2;

	public InterfaceGame(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		try {
			MusicLibrary.music("bgmusic");

			background = ImageLibrary.loadImage("background");
			huyhieu = ImageLibrary.loadImage("huyhieu");
			port0 = ImageLibrary.loadImage("port0");
			map = new MapData(1);
			robot = map.getRobot1();
			arraytile = map.getArraytile();
			enemybone1 = new EnemyBone(800, 50, -1, 6, arraytile, robot,1);
			enemybone2 = new EnemyBone(400, 200, -1, 6, arraytile, robot,2);
			enemygirl = new EnemyGirl(450, 340, 0, 6, robot,1);
			enemyspider1 = new EnemySpider(800, 550, -1, 6, arraytile, robot,1);
			enemyspider2 = new EnemySpider(800, 200, -2, 6, arraytile, robot,2);
			enemyeagle = new EnemyEagle(900, 550, -1, 6, arraytile, robot,1);
			enemyeagle2 = new EnemyEagle(50, 550, -4, 6, arraytile, robot,2);
		} catch (IOException ex) {
			Logger.getLogger(InterfaceGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void update() {

		updateSomething();
		updateEnemyBone();
		updateEnemyGirl();
		updateEnemySpider();
		updateEnemyEagle();
		updateRobot();
		checksocre();
		checkRunning();
	}

	public void updateEnemyBone() {
		enemybone1.update();
		enemybone2.update();

	}
	public void checkRunning(){
		if(robot.getHealth() <= 0){
			gsm.setState(6);
		}
	}
	public void updateEnemyGirl() {
		enemygirl.update();

	}

	public void updateEnemySpider() {
		enemyspider1.update();
		enemyspider2.update();
	}

	public void updateEnemyEagle() {
		enemyeagle.update();
		enemyeagle2.update();
	}

	public void updateRobot() {
		robot.update();

	}

	public void checksocre() {
		if (robot.getScore() == 5 && robot.getcenterX() < 100 && robot.getcenterY() > 400) {
			gsm.setState(2);

		}
	}

	public void updateSomething() {
		for (int i = 0; i < arraytile.size(); i++) {
			Tile t = arraytile.get(i);
			t.update();

		
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		// g2.drawImage(background, 0, 0, 1024, 700, null);
		g2.drawImage(background, 0, 0, null);
		 g2.drawImage(huyhieu, 1180, 0,200,700, null);
		for (int i = 0; i < arraytile.size(); i++) {
			Tile t = arraytile.get(i);
			g2.drawImage(t.getImage(), t.getTlx(), t.getTly(), null);
//			g2.drawRect((int) t.getRectangle().getX(), (int) t.getRectangle().getY(), (int) t.getRectangle().getWidth(),
//					(int) t.getRectangle().getHeight());
		}
		if (robot.getScore() == 5) {
			g2.drawImage(port0, 100, 500, null);
		}
		robot.paint(g2);
		enemygirl.paint(g2);
		enemybone1.paint(g2);
		enemybone2.paint(g2);
		enemyspider1.paint(g2);
		enemyspider2.paint(g2);
		enemyeagle.paint(g2);
		enemyeagle2.paint(g2);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			robot.moveRight();

			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			break;
		case KeyEvent.VK_UP:
			robot.movetop();

			break;
		case KeyEvent.VK_DOWN:
			robot.movebottom();

			break;
		case KeyEvent.VK_SPACE:
			robot.shoot();

			break;
		case KeyEvent.VK_Z:
			robot.fight();
			break;
		case KeyEvent.VK_C:
			robot.power();
			
			break;
		}
		robot.setmove(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			robot.stop();

			break;
		case KeyEvent.VK_LEFT:
			robot.stop();

			break;
		case KeyEvent.VK_UP:
			robot.stop();

			break;
		case KeyEvent.VK_DOWN:
			robot.stop();

			break;
		case KeyEvent.VK_SPACE:
			robot.stop();
			break;
		case KeyEvent.VK_Z:
//			robot.fight();
			
			break;
		case KeyEvent.VK_C:
		
			
			break;
		}
		robot.setmove(false);
	}

	// public static Robot getRobot() {
	// return robot;
	// }

	public static ArrayList<Tile> getArraytile() {
		return arraytile;
	}

	@Override
	public void keyType(KeyEvent e) {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

}
