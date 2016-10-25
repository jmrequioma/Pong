package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private int X;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	int y = 0;
	int ya = 0;
	private Game game;

	public Racquet(Game game, int X) {
		this.game= game;
		this.X = X;
	}

	public void move() {
		if (y + ya > 0 && y + ya < game.getHeight() - 60)
			y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillRect(X, y, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}
	
	public void keyReleased2(KeyEvent e) {
		ya = 0;
	}
	
	/**
	 * makes the racquet move up or down
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			ya = -game.speed;   // value is the speed
		if (e.getKeyCode() == KeyEvent.VK_S)
			ya = game.speed;   // value is the speed
		if (e.getKeyCode() == KeyEvent.VK_D)
			game.speed++;   // power-up
	}
	
	public void keyPressed2(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			ya = -game.speed;   // value is the speed
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = game.speed;   // value is the speed
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			game.speed++;   // power-up
	}
	
	/**
	 * returns the position of the racquet
	 * 
	 */
	public Rectangle getBounds() {
		return new Rectangle(X, y, WIDTH, HEIGHT);
	}
	
	public int getRightX() {
		return X;
	}
}
