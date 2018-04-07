/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import manager.GameStateManager;
/**
 *
 * @author dellvostroo 2420
 */
public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
//	public abstract void handleInput();
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
        public abstract void keyType(KeyEvent e);
}
