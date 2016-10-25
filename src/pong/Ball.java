package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private Game game;
	private static final int DIAMETER = 15;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	
	public Ball(Game game) {
		this.game = game;
	}
	
	/**
	 * makes the ball move around in the canvas and makes it bounce off walls
	 * 
	 */
	void move() {
		boolean changeDirection = true;
		if (x + xa < 0) {
			xa = game.speed;
			if (!(collision())) {
				game.setScore2(++game.score2);
				Sound.SCORE.play();
			}
		} else if (x + xa > game.getWidth() - DIAMETER) {
			xa = -game.speed;
			if (!(collision2())) {
				game.setScore1(++game.score1);
				Sound.SCORE.play();
			}
		} else if (y + ya < 0) {
			ya = game.speed;
		} else if (y + ya > game.getHeight() - DIAMETER) {
			ya = -game.speed;
		} else if (collision()) {
			xa = game.speed;
			x = game.racquet.getRightX() + DIAMETER;
			game.speed++;   // makes the ball go faster
		} else if (collision2()) {
			xa = -game.speed;
			x = game.racquet2.getRightX() - DIAMETER;
		} else if (game.score2 == 3 || game.score1 == 3) {
			game.gameOver();
			game.setScore1(0);
			game.setScore2(0);
			game.setSpeed(1);
		} else {
			changeDirection = false;
		}
		if (changeDirection) {
			Sound.BALL.play();
		}
		x = x + xa;
		y = y + ya;
	}
	
	private boolean collision() {
		// TODO Auto-generated method stub
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	private boolean collision2() {
		// TODO Auto-generated method stub
		return game.racquet2.getBounds().intersects(getBounds());
	}
	
	private Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
}
