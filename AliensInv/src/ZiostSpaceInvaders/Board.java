package ZiostSpaceInvaders;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Aliens> aliensList = new ArrayList<Aliens>();
	private Aliens al;
	Player player;
	Bonus bonus;
	Random rand = new Random();

	public Board() {
		player = new Player();
		bonus = new Bonus(200,300);
	}

	public void born() {
		al = new Aliens(rand.nextInt(440) + 2, 0);
		aliensList.add(al);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < aliensList.size(); i++) {
			Aliens a = aliensList.get(i);
			boolean draw = true;
			if (Game.inGame) {
				if (a.isVisible()) {
					if (player.bullets.size() > 0) {
						for (int j = 0; j < player.bullets.size(); j++) {
							if(bonus.getBounds().intersects(player.bullets.get(j))){
								System.out.println("Collide");
								bonus.setVisible(false);
							}
							if (a.getBounds().intersects(player.bullets.get(j))) {
								a.setVisible(false);
								player.bullets.remove(j);
								g.drawImage(Game.imageLoad("../explosion1.png"),a.getX(), a.getY(), null);
								draw = false;
								player.setScore(player.getScore() + 10);
								System.out.println("Get level requirements "+player.getLevelRequirement());
								if (player.getScore() == player.getLevelRequirement()) {
									player.setLevel(player.getLevel() + 1);;
									resetGameScreen();
								}
							}
						}
					}
					if (player.getBounds().intersects(a.getBounds())
							|| player.getBaseBounds().intersects(a.getBounds())) {
						player.setLives(player.getLives() - 1);
						a.setVisible(false);						
						
						if(player.getLives() < 0){
							Game.inGame = false;
						}
					}
					if (draw) {
						g.drawImage(a.getImage(), a.getX(), a.getY(), this);
					}
				} else {
					aliensList.remove(i);
				}
			}
		}
		if(bonus.isVisible()){
			g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), this);
			if(player.getBaseBounds().intersects(bonus.getBounds()) 
					|| player.getBounds().intersects(bonus.getBounds())){
				System.out.println("GET LIFE");
				player.setLives(player.getLives() + 1);
				bonus.setVisible(false);
			}
		}
	}

	private void resetGameScreen() { 
		aliensList = new ArrayList<Aliens>();		
		player.reset();
		born();
	}
	
	public void run() {
		try {
			born();
			while (true) {
//				Aliens last = aliensList.get(aliensList.size() - 1);
//				if (last.getY() > 50) {
//					born();
//				}				
				if (rand.nextInt(100) < (player.getLevel() + 1)) {
					born();
				}
				if(bonus.isVisible()){
					bonus.move();
				}
				else if (rand.nextInt(100) < 10 && !bonus.isVisible()) {	
					bonus.setX(rand.nextInt(300));
					bonus.setY(rand.nextInt(440) + 2);
					bonus.setVisible(true);
				}
				for (int i = 0; i < aliensList.size(); i++) {
					Aliens a = aliensList.get(i);
					if (a.isVisible()) {
						a.move();
					} else {
						aliensList.remove(i);
					}
				}
				Thread.sleep(7);
			}
		} catch (Exception e) {
			System.err.println("Exception in board " + e.getMessage());
			System.out.println(e.getStackTrace().toString());
		}
	}
}