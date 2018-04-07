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
public class MenuState extends GameState implements KeyListener {

    private Image menustate, diamond, menuabout;
    private int currentOption = 0;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        try {
            menustate = ImageLibrary.loadImage("MenuState1");
            diamond = ImageLibrary.loadImage("diamond");
            menuabout = ImageLibrary.loadImage("MenuAbout");
        } catch (IOException ex) {
            Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(menustate, 0, 0, null);
        if (currentOption == 0) {
            g.drawImage(diamond, 500, 110, null);
        } else if (currentOption == 1) {
            g.drawImage(diamond, 500, 220, null);
        } else if (currentOption == 2) {
            g.drawImage(diamond, 500, 330, null);
        } else if (currentOption == 3) {
            g.drawImage(diamond, 500, 440, null);
        }

    }

    private void selectOption() {
        if (currentOption == 0) {
            gsm.setState(GameStateManager.Introduction);
        }
        if (currentOption == 1) {
            gsm.setState(GameStateManager.ABOUTSTATE);

        }
        if (currentOption == 2) {
            gsm.setState(GameStateManager.HIGHSCORE);
        }
        if (currentOption == 3) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                MusicLibrary.music("menuoption");
                if (currentOption > 0) {
                    currentOption--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (currentOption < 3) {
                    currentOption++;
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
