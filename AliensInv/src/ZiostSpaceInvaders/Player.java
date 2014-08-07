package ZiostSpaceInvaders;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player implements Runnable {

	private static final int MAX_BULLETS = 5;
	int x, y, xDirection, id;
	int bx, by;
	boolean readyToFire, shot = false;
	ArrayList<Rectangle> bullets;
	private int lives;
	private int level;
	private int score;
	private int toPassLevel = 240;
	private int levelRequirement = 70;
	
	public Player() {
		readyToFire = true;
		x = 175;
		y = 575;
		bullets = new ArrayList<Rectangle>();
	}

	public void draw(Graphics g) {
		if (Game.inGame) {
			g.drawImage(Game.imageLoad("../player.png"), x, y - 10, null);
			for (int i = 0; i < bullets.size(); i++) {
				g.drawImage(Game.imageLoad("../r.png"), bullets.get(i).x,
						bullets.get(i).y, null);
			}
		}
	}
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLevelRequirement(){
		return this.toPassLevel + this.level*this.levelRequirement;
	}
	
	public void reset(){
		this.score = 0;
		this.bullets = new ArrayList<Rectangle>();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x+5, y + 35, 70, 5);
	}

	public Rectangle getBaseBounds() {
		return new Rectangle(x + 45, y, 10, 40);
	}

	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).y--;
		}
	}

	public void move() {
		x += xDirection;
		if (x <= 10)
			x = 10;
		if (x >= 400)
			x = 400;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == e.VK_LEFT) {
			setXDirection(-1);
		}
		if (keyCode == e.VK_RIGHT) {
			setXDirection(1);
		}
		if (keyCode == e.VK_SPACE) {
			if (bullets.size() < MAX_BULLETS) {
			}
			if (readyToFire) {
				by = y - 5;
				bx = x;
				Rectangle bullet = new Rectangle(bx, by, 2, 20);
				bullets.add(bullet);
				Rectangle bullet2 = new Rectangle(bx + 85, by, 2, 20);
				bullets.add(bullet2);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == e.VK_LEFT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_RIGHT) {
			setXDirection(0);
		}
		if (keyCode == e.VK_SPACE) {
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).y < -5) {
					bullets.remove(i);
				}
			}
		}
	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setXDirection(int xdir) {
		this.xDirection = xdir;
	}

	public void run() {
		try {
			while (true) {
				shoot();
				move();

				Thread.sleep(7);
			}
		} catch (Exception e) {
			System.err.println("Player = " + e.getMessage());
		}
	}

}