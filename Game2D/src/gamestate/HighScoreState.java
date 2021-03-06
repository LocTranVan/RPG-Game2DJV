
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
public class HighScoreState extends GameState implements KeyListener {

    private Image highscorestate, diamond;
    private final int currentOption = 0;
    private int about = 0;

    public HighScoreState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        try {
            highscorestate = ImageLibrary.loadImage("highscore");

        } catch (IOException ex) {
            Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(highscorestate, 0, 0, null);

    }

    private void selectOption() {
        if (currentOption == 0) {
            gsm.setState(GameStateManager.MENU);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            MusicLibrary.music("collect");
            selectOption();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
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
