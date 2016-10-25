package pong;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	static boolean flag = true;
	int x = 0;
	int y = 0;

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this, 0);
	Racquet racquet2 = new Racquet(this, 475);
	int speed = 1;
	int score1;
	int score2;
	
	private int getScore1() {
		return score1;
	}
	
	private int getScore2() {
		return score2;
	}
	
	void setScore1(int score1) {
		this.score1 = score1;
	}
	
	void setScore2(int score2) {
		this.score2 = score2;
	}
	
	void setSpeed(int speed) {
		this.speed = speed;
	}
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				
			}
		});
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				racquet2.keyPressed2(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				racquet2.keyReleased2(e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racquet.move();
		racquet2.move();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		ball.paint(g2d);
		racquet.paint(g2d);
		
		Graphics2D g3d = (Graphics2D) g;
		g3d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g3d.setColor(Color.ORANGE);
		racquet2.paint(g3d);
		
		// paint the punctuation for p1
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore1()), 10, 30);
		
		// paint the punctuation for p2
		g2d.setColor(Color.ORANGE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore2()), 460, 30);
	}

	public void gameOver() {
		Sound.GAMEOVER.play();
		int n = JOptionPane.showConfirmDialog(this, "Player 1 score is: " + getScore1() + "\n" +
				"Player 2 score is: " + getScore2() + "\n" + "Do you want to play again?\n",
				"Game Over", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			flag = true;
		} else {
			flag = false;
			System.exit(ABORT);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		while (flag) {
			JFrame frame = new JFrame("2-Player Pong Game");
			Game game = new Game();
			frame.add(game);
			frame.setSize(500, 300);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			while (true) {
				game.move();
				game.repaint();   // tells JPanel to paint again so that we could see the new position of the ball
				Thread.sleep(10);   // higher value makes the animation slower
			}
		}
	}
}

