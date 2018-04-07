package manager;

import datamap1.InterfaceGame;
import datamap1.InterfaceGame2;
import datamap2.InterfaceGamemap2;
import gamestate.AboutState;
import gamestate.GameState;
import gamestate.HighScoreState;
import gamestate.Introduction;
import gamestate.MenuState;
import gamestate.Reload;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author dellvostroo 2420
 */
public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;
    private int previousState;

    public static final int NUM_STATES = 8;
//    public static final int INTRO = 0;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int PLAY2 = 2;
    public static final int PLAYMAP2 = 3;
    public static final int HIGHSCORE = 4;
    public static final int ABOUTSTATE = 5;
    public static final int RELOAD = 6;
    public static final int Introduction = 7;
    public GameStateManager() {
        gameStates = new GameState[NUM_STATES];
        setState(MENU);
    }

    public void setState(int i) {
        previousState = currentState;
        unloadState(previousState);
        currentState = i;
        switch(i){
        case MENU:
        	 gameStates[i] = new MenuState(this);
             gameStates[i].init();
             break;
        case PLAY:
        	 gameStates[i] = new InterfaceGame(this);
        	 break;
        case PLAY2:
        	gameStates[i] = new InterfaceGame2(this);
        
        	 break;
        case PLAYMAP2:
       	 gameStates[i] = new InterfaceGamemap2(this);
        	 break;
        case ABOUTSTATE:
        	 gameStates[i] = new AboutState(this);
             gameStates[i].init();
             break;
        case HIGHSCORE:
        	 gameStates[i] = new HighScoreState(this);
             gameStates[i].init();
             break;
        case RELOAD:
        	gameStates[i] = new Reload(this);
        	gameStates[i].init();
        	break;
        case Introduction:
          	gameStates[i] = new Introduction(this);
        	gameStates[i].init();
        	break;
        }
    }

    public void unloadState(int i) {
        gameStates[i] = null;
    }

    public void update() {
        if (gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }

    public void draw(Graphics2D g) {
        if (gameStates[currentState] != null) {
            gameStates[currentState].draw(g);
        }
    }

    public int getcurrent() {
        return currentState;
    }

    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyPressed(KeyEvent e) {
        if (gameStates[currentState] != null) {
            gameStates[currentState].keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (gameStates[currentState] != null) {
            gameStates[currentState].keyReleased(e);
        }
    }
}
