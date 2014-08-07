package ZiostSpaceInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame  {
	Image dbImage;
	Graphics dbg;
	public static boolean inGame = true;
	static Image image = null;
	public static int Width = 500;
	public static int Height = 650;
	public static Board board;
	private static Image bg;
	JPanel gamePanel = new JPanel();
	
	Font font = new Font("Arial", Font.BOLD, 34);
	Font fontDigital = loadFont("../DS-DIGIB.TTF"); //new Font("../DS-DIGIB.TTF", Font.PLAIN, 25);
	static Font newFont;
	
	public Game()  {		
		setTitle("Crusher");
		setSize(Width,Height);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new AL());
		
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("E:\\Downloads\\Skype\\ds_digital\\DS-DIGIB.TTF"));
			newFont = newFont.deriveFont(25.0f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Font loadFont(final String path) {
	    Font font = null;

	    InputStream fontFile = null;
	    fontFile = Game.class.getResourceAsStream(path);

	    if (fontFile != null) {
	        try {
	            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
	        } catch (Exception e) {	            
	            System.err.println("Error with font");
	        }
	    }
	    return font;
	}
	
	  public static Image imageLoad(String i){
	    	try {
				image = ImageIO.read(Game.class.getResource(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in imageLoad");
			}
	    	return image;
	    }
	  public static Image bgImageLoad(String s){
		  try {
			bg = ImageIO.read(Game.class.getResource(s));
		} catch (IOException e) {
			System.out.println("Image exception");// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return bg;
	  }
	public class AL extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e){
			board.player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e){
			board.player.keyReleased(e);
		}
	}

	@Override
	public void paint(Graphics g){
		if(inGame){
		dbImage = bgImageLoad("../1.png");
		}
		else {
			dbImage = bgImageLoad("../gameOver.png");			
		}
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void draw(Graphics g){
		board.player.draw(g);
		Game.board.draw(g);
		
		g.setColor(Color.RED);
		g.setFont(newFont);
		g.drawString("Score: "+board.player.getScore(), 350, 50);
		g.drawString("Lives: "+board.player.getLives(), 180, 50);
		g.drawString("Level: "+(board.player.getLevel() + 1), 15, 50);
		repaint();
	}

	public static void main(String[] args) {
		
		Game game = new Game();
		
		board = new Board();
		Thread b1 = new Thread(board);
		Thread p1 = new Thread(board.player);

		b1.start();
		p1.start();
	}
}