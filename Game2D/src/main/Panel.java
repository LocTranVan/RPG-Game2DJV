package main;

import datamap1.InterfaceGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import datamap2.*;
import manager.GameStateManager;

/**
 *
 * @author nhatt_000
 */
public class Panel extends JPanel implements Runnable, KeyListener{

    private Thread thread;
    private boolean mRunning;
    private Graphics2D g2;
    private BufferedImage bufferedImage;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 700;
    private InterfaceGamemap2 interfacegame;
    private GameStateManager gsm;

    private enum GameState {
        map1, map2
    }

    GameState state = GameState.map1;

    public Panel() {
        System.out.println("1");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        setBackground(Color.BLACK);
        addKeyListener(this);
        init();
    }

   
    public void init() {
        System.out.println("2");
        bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gsm = new GameStateManager();
//        interfacegame = new InterfaceGamemap2();
        mRunning = true;

        thread = new Thread(this);
        thread.start();
    }

    public void update() {
//		 interfacegame.update();
        gsm.update();
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("3");
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
    }

  
    public void draw(Graphics2D g2) {
//		interfacegame.draw(g2);
        gsm.draw(g2);
//		interfaceGame2.update();
    }

    @Override
    public void run() {
        System.out.println("4");
        while (mRunning) {
            long start = System.currentTimeMillis();
            update();
            draw(g2);
            repaint();
            long sleep = 1000 / 60 - (System.currentTimeMillis() - start);
            if (sleep <= 0) {
                sleep = 5;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out.printf("Error " + e.toString()));
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//		interfacegame.keyTyped(e);
            gsm.keyTyped(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
//		interfacegame.keyPressed(e);
//                Keys.keySet(e.getKeyCode(), true);
        gsm.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
//		interfacegame.keyReleased(e);
//                Keys.keySet(e.getKeyCode(), false);
        gsm.keyReleased(e);
    }

}
