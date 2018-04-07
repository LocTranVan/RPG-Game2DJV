package datamap1;

import data.Bullet;
import data.MapData;
import data.Robot;
import data.Tile;
import gamestate.GameState;
import images.ImageLibrary;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.GameStateManager;
import utils.MusicLibrary;

/**
 *
 * @author nhatt_000
 */
public class InterfaceGame2 extends GameState implements KeyListener {

    private Image background, image, huyhieu, port0;
    private Graphics second;
    private static ArrayList<Tile> arraytile;
//    private static ArrayList<Tile> arraytile = MapData.getArraymap();
    private static Robot robot;
//    private static ArrayList<Bullet> girlbulles = EnemyGirl.getBulles();
//    private static ArrayList<Bullet> egalbulles = EnemyEagle.getBulles();
    private MapData map;
    private EnemyBone enemybone1, enemybone2,enemybone3;
    private EnemyGirl enemygirl,enemygirl2,enemygirl3;
    private EnemySpider enemyspider1, enemyspider2;
    private EnemyEagle enemyeagle, enemyeagle2;

    public InterfaceGame2(GameStateManager gsm) {
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
//            robot = new Robot();
            map = new MapData(2);
            robot = map.getRobot2();
            arraytile = map.getArraytile2();
            enemybone1 = new EnemyBone(200, 330, -1, 6,arraytile,robot,1); //xương
            enemybone2 = new EnemyBone(400, 410, -1, 6,arraytile,robot,2);
            enemybone3 = new EnemyBone(850, 480, -1, 6,arraytile,robot,3);
            enemygirl = new EnemyGirl(800, 40, 0, 6,robot,1);
            enemygirl2 = new EnemyGirl(870, 180, 0, 6,robot,2);
            enemygirl3= new EnemyGirl(800, 535, 3, 6,robot,3);
            enemyspider1 = new EnemySpider(700, 568, -1, 6,arraytile,robot,1);
            enemyspider2 = new EnemySpider(800, 215, -2, 6,arraytile,robot,2); // nhện
            enemyeagle = new EnemyEagle(460, 550, -1, 6,arraytile,robot,1); //chim ưng
            enemyeagle2 = new EnemyEagle(50, 550, -4, 6,arraytile,robot,2);
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
        enemybone3.update();

    }

    public void updateEnemyGirl() {
        enemygirl.update();
        enemygirl2.update();
        enemygirl3.update();

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
        if (robot.getScore() >=  10 && robot.getcenterX() < 100 && robot.getcenterY() > 400) {
            gsm.setState(3);
        }
    }

    public void updateSomething() {
        for (int i = 0; i < arraytile.size(); i++) {
            Tile t = arraytile.get(i);
            t.update();
        }
    }
	public void checkRunning(){
		if(robot.getHealth() <= 0){
			gsm.setState(6);
		}
	}
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(background, 0, 0, null);
        g2.drawImage(huyhieu, 1180, 0,200,700, null);
        for (int i = 0; i < arraytile.size(); i++) {
            Tile t = arraytile.get(i);
            g2.drawImage(t.getImage(), t.getTlx(), t.getTly(), null);
        }
        if (robot.getScore() == 5) {
            g2.drawImage(port0, 100, 500, null);
        }
        robot.paint(g2);
        enemygirl.paint(g2);
        enemygirl2.paint(g2);
        enemygirl3.paint(g2);
        enemybone1.paint(g2);
        enemybone2.paint(g2);
        enemybone3.paint(g2);
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
//    			robot.stop();
    			break;
        	case KeyEvent.VK_C:
//    			robot.stop();
    			break;
        }
        robot.setmove(false);
    }

    public static Robot getRobot() {
        return robot;
    }

    public static ArrayList<Tile> getArraytile() {
        return arraytile;
    }

    @Override
    public void keyType(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
