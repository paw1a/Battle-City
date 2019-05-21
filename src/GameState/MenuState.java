package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class MenuState extends GameState {

	private int currentChoice = 0;
	
	private int tanky;
	private Color titleColor;
	private Color fonColor;
	private Font titleFont;
	private Font font;
	
	private BufferedImage titleImage;
	private BufferedImage settingsImage;
	private BufferedImage tileset;
	private BufferedImage optionImage;
	private BufferedImage patron;
	private BufferedImage money;
	private BufferedImage plus;
	
	private Rectangle[] opRect;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		tanky = 183;
		titleColor = new Color(170, 0, 70);
		fonColor = new Color(50, 30, 50);
		titleFont = new Font("Century Gothic",Font.PLAIN,40*2);
		font = new Font("Arial", Font.BOLD, 20*2);
		try {
			titleImage = ImageIO.read(getClass().getResourceAsStream("/Images/title.png"));
			settingsImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/settings.png"));
			tileset = ImageIO.read(getClass().getResourceAsStream("/Images/image.png"));
			optionImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/options.png"));
			patron = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/patron.png"));
			money = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/money.png"));
			plus = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/plus.png"));
			opRect = new Rectangle[10];
		} catch (IOException e) {System.out.println("NO FILE");}
		init();
	}
	
	@Override
	public void init() {
		opRect[0] = new Rectangle(600, 375, 300, 50); // options
		opRect[1] = new Rectangle(600, 440, 140*2, 23*2);
		opRect[2] = new Rectangle(600, 506, 105*2, 23*2);
		opRect[3] = new Rectangle(600, 286*2, 180*2, 23*2);
		opRect[4] = new Rectangle(600, 319*2, 100*2, 23*2);
		opRect[5] = new Rectangle(1000, 320*2, 40*2, 40*2); // settings
		opRect[6] = new Rectangle(370, 260*2, 56*3, 56*3); // oval
		opRect[7] = new Rectangle(500, 60*2, 15*2, 16*2); // plus
		opRect[8] = new Rectangle(800, 60*2, 15*2, 16*2);
		opRect[9] = new Rectangle(1160, 370*2, 100*2, 25*2); // help
	}
	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawImage(titleImage, 150*2, 100*2, 450*2, 65*2, null);
		g.drawImage(optionImage, 300*2, 190*2, 180*2, 150*2, null);
		g.drawImage(money, 155*2, 60*2, 15*2, 16*2, null);
		g.drawString("5000", 180*2, 75*2);
		g.drawImage(plus, 250*2, 60*2, 15*2, 16*2, null);
		g.drawImage(patron, 320*2, 58*2, 15*2, 20*2, null);
		g.drawString("200", 345*2, 75*2);
		g.drawImage(plus, 400*2, 60*2, 15*2, 16*2, null);
		g.drawString("I - 30000", 155*2, 40*2);
		g.drawString("HI - 300000", 270*2, 40*2);
		g.drawString("Settings", 580*2, 390*2);
		g.setColor(Color.GRAY.brighter());
		g.fillOval(185*2, 258*2, 82*2, 82*2);
		g.setColor(Color.GREEN.darker());
		g.fillOval(187*2, 260*2, 78*2, 78*2);
		g.drawImage(settingsImage, 500*2, 320*2, 40*2, 40*2, null);
		g.fillRect(1, 1, (GamePanel.WIDTH - 100)*2, 10*2);
		g.setColor(Color.GRAY);
		g.fillRect((GamePanel.WIDTH - 100)*2, 1*2, 99*2, 10*2);
		g.setFont(new Font("Arial", Font.BOLD, 10*2));
		g.setColor(Color.WHITE);
		g.drawString("149043/150000 Level 15", 280*2, 9*2);
		
//		for(int i = 0; i < 10; i++) {
//			g.draw(opRect[i]);
//		}
	}

	private void select() {
		if(currentChoice == 0) {
			gsm.setState(gsm.LEVEL1STATE);
		}
		if(currentChoice == 2) {
			gsm.setState(gsm.CAREERSTATE);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int k) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(getRect(e.getX(), e.getY()).intersects(opRect[0])) {
				currentChoice = 0;
				select();
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[1])) {
				System.out.println("player 2");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[2])) {
				currentChoice = 2;
				select();
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[3])) {
				System.out.println("construction");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[4])) {
				System.out.println("armory");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[5])) {
				System.out.println("settings");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[6])) {
				System.out.println("oval");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[7])) {
				System.out.println("plus1");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[8])) {
				System.out.println("plus2");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[9])) {
				System.out.println("help");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public Rectangle getRect(int x, int y) {
		return new Rectangle(x, y, 1, 1);
	}
	
}
