package ZiostSpaceInvaders;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bonus {

	private String bonus = "../bonus.png";
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	private boolean visibale; 
	public  Bonus(int x, int y){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bonus));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visibale = true;
		this.x = x;
		this.y = y;
	}
		
	public void move(){
		y++;		
		if(y >= 600){
			setVisible(false);
		}
	}
	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y = y;
	}
	public Image getImage(){
		return image;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y-25, width-10, height);
	}
	public void setVisible(Boolean v) {
		this.visibale = v;
	}
	public boolean isVisible(){
		return this.visibale;
	}
}