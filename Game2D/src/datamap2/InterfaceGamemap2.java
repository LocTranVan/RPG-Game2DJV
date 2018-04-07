package datamap2;

import images.ImageLibrary;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.*;
import gamestate.GameState;
import manager.GameStateManager;

/**
 *
 * @author nhatt_000
 */
public class InterfaceGamemap2 extends GameState implements MouseListener {

    private Image background, image, huyhieu, title, fish1left;
    private Graphics second;
    private static ArrayList<Tile> arraytile;
//    private static ArrayList<Tile> arraytile = MapData.getArraymap();
    private static Robot robot;
//    private static ArrayList<Bullet> girlbulles = EnemyGirl.getBulles();
//    private static ArrayList<Bullet> egalbulles = EnemyEagle.getBulles();
    private MapData map;
    private EnemyFishPink enemyfishpink, enemyfishpink2;
    private EnemyFishDragon enemyfishdragon, enemyfishdragon2;
    private EnemyFishBig enemyfishbig;
    private EnemyFishSmall enemyfishsmall, enemyfishsmall1, enemyfishsmall2, enemyfishsmall3, enemyfishsmall4;

    public InterfaceGamemap2(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        try {
            background = ImageLibrary.loadImage("background1");
            title = ImageLibrary.loadImage("background1");
            huyhieu = ImageLibrary.loadImage("huyhieu2");
//            robot = new Robot();
            map = new MapData(3);
            System.out.println("ne day");
            robot = map.getRobot3();
            arraytile = map.getArraytile3();
            enemyfishpink = new EnemyFishPink(900, 480, -1, 6, arraytile, 1);
            enemyfishpink2 = new EnemyFishPink(100, 480, -1, 6, arraytile, 2);
            enemyfishdragon = new EnemyFishDragon(800, 300, -1, 6, arraytile, 1);
            enemyfishdragon2 = new EnemyFishDragon(100, 300, -1, 6, arraytile, 2);
            enemyfishbig = new EnemyFishBig(600, 600, -1, 6, arraytile, 1);

            enemyfishsmall1 = new EnemyFishSmall(285, 97, -1, 6, arraytile, robot, 1);
            enemyfishsmall2 = new EnemyFishSmall(475, 157, -1, 6, arraytile, robot, 2);
            enemyfishsmall3 = new EnemyFishSmall(700, 200, -1, 6, arraytile, robot, 3);

        } catch (IOException ex) {
            Logger.getLogger(InterfaceGamemap2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() {
        updateSomething();
        updateEnemyFishPink();
        updateEnemyFishDragon();
        updateEnemyFishBig();
        updateEnemyFishSmall();
        updateRobot();
        checkRunning();
    }

    public void updateEnemyFishPink() {
        enemyfishpink.update();
        enemyfishpink2.update();

    }

    public void updateEnemyFishSmall() {

        enemyfishsmall1.update();
        enemyfishsmall2.update();
        enemyfishsmall3.update();

    }

    public void updateEnemyFishDragon() {
        enemyfishdragon.update();
        enemyfishdragon2.update();

    }

    public void updateEnemyFishBig() {
        enemyfishbig.update();

    }

    public void updateRobot() {
        robot.update();
    }

    public void checkRunning() {
        if (robot.getHealth() <= 0) {
            gsm.setState(5);
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
//        g2.drawImage(background, 0, 0, 1024, 700, null);
//    for(int i = 0; i< 40; i++){
//        for(int j = 0; j< 40 ; j++){
        g2.drawImage(title, 0, 0, null);
        g2.drawImage(huyhieu, 1180, 0,200,700, null);
        robot.paint(g2);
//        enemygirl.paint(g2);
        enemyfishpink.paint(g2);
        enemyfishpink2.paint(g2);
        enemyfishbig.paint(g2);
        enemyfishdragon.paint(g2);
        enemyfishdragon2.paint(g2);
//        enemyfishsmall.paint(g2);
        enemyfishsmall1.paint(g2);
        enemyfishsmall2.paint(g2);
        enemyfishsmall3.paint(g2);

//        enemybone2.paint(g2);
//        enemyspider1.paint(g2);
//        enemyspider2.paint(g2);
//        enemyeagle.paint(g2);
//        enemyeagle2.paint(g2);
    }

    public void keyTyped(KeyEvent e) {
    }

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
//    			robot.fight();

                break;
            case KeyEvent.VK_C:

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
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int cx = e.getX();
        int cy = e.getY();
        System.out.println(cx + " sf" + cy);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyType(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
