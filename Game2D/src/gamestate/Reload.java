
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import images.ImageLibrary;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.GameStateManager;
import utils.MusicLibrary;

/**
 *
 * @author dellvostroo 2420
 */
public class Reload extends GameState implements KeyListener {

    private Image theend, diamond, menuabout;
    private int currentOption = 0;

    public Reload(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        try {
            theend = ImageLibrary.loadImage("theend");
            diamond = ImageLibrary.loadImage("diamond");
//            menuabout = ImageLibrary.loadImage("MenuAbout");
        } catch (IOException ex) {
            Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(theend, 0, 0, null);
        if (currentOption == 0) {
            g.drawImage(diamond, 545, 415, null);
        } else if (currentOption == 1) {
            g.drawImage(diamond, 705, 415, null);
        }

    }

    private void selectOption() {
        if (currentOption == 0) {
            gsm.setState(GameStateManager.PLAY);
        }

        if (currentOption == 1) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                MusicLibrary.music("menuoption");
                if (currentOption == 0) {
                    currentOption++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (currentOption == 1) {
                    currentOption--;
                }
                MusicLibrary.music("menuoption");
                break;
            case KeyEvent.VK_ENTER:
                selectOption();
                MusicLibrary.music("collect");
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:

                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_ENTER:

                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyType(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
