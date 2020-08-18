package states;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import util.Progress;

public class MenuState extends GameState {

	private int currentChoice = 0;

	private Font font;

	private int levelLineWidth;
	
	private BufferedImage titleImage;
	private BufferedImage settingsImage;
	private BufferedImage tileset;
	private BufferedImage patron;
	private BufferedImage money;
	private BufferedImage plus;
	private BufferedImage reward;
	
	private Rectangle[] opRect;

	private Progress pr;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		pr = Progress.getInstance();

		try {
			titleImage = ImageIO.read(getClass().getResourceAsStream("/Images/title.png"));
			settingsImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/settings.png"));
			tileset = ImageIO.read(getClass().getResourceAsStream("/Images/image.png"));
			patron = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/patron.png"));
			money = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/money.png"));
			plus = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/plus.png"));
			reward = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/reward.png"));
			opRect = new Rectangle[10];
			InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
		} catch (IOException | FontFormatException e) {System.out.println("NO FILE");}

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
		opRect[6] = new Rectangle(370, 255*2, 56*3, 56*3); // oval
		opRect[7] = new Rectangle(500, 60*2, 15*2, 16*2); // plus
		opRect[8] = new Rectangle(800, 60*2, 15*2, 16*2);
		opRect[9] = new Rectangle(1130, 370*2, 100*2, 25*2); // help
	}
	@Override
	public void update() {
		levelLineWidth = (int)((GamePanel.WIDTH / (double)Integer.parseInt(pr.get("xpLevelMaxScore")))
				*(double) Integer.parseInt(pr.get("xp")));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(font.deriveFont(36f));
		g.drawImage(titleImage, 150*2, 100*2, 450*2, 65*2, null);
		g.drawImage(money, 155*2, 60*2, 15*2, 16*2, null);
		//g.drawImage(reward, 380, 350, 150, 150, null);
		g.drawString(pr.get("coins"), 180*2, 75*2);
		g.drawImage(plus, 250*2, 60*2, 15*2, 16*2, null);
		g.drawImage(patron, 320*2, 58*2, 15*2, 20*2, null);
		g.drawString(pr.get("patrons"), 345*2, 75*2);
		g.drawImage(plus, 400*2, 60*2, 15*2, 16*2, null);
		g.drawString("I-"+pr.get("lastLevelScore"), 155*2, 40*2);
		g.drawString("HI-"+pr.get("highScore"), 285*2, 40*2);
		g.drawString("EXIT", 580*2, 390*2);
		g.setColor(Color.GRAY.brighter());
		/*g.fillOval(185*2, 258*2, 82*2, 82*2);
		g.setColor(Color.GREEN.darker());
		g.fillOval(187*2, 260*2, 78*2, 78*2);*/
		g.drawImage(settingsImage, 500*2, 320*2, 40*2, 40*2, null);

		//draw xp line
		g.setColor(Color.GRAY.darker());
		g.fillRect(1, 2, GamePanel.WIDTH-2, 20);
		g.setColor(Color.GREEN.darker());
		g.fillRect(1, 2, levelLineWidth, 20);
		g.setColor(Color.WHITE);
		g.setFont(font.deriveFont(17f));
		g.drawString(pr.get("xp")+"/" +pr.get("xpLevelMaxScore") +" Level "+pr.get("xpLevel"), 280*2, 9*2);

		//draw options
		g.setFont(font.deriveFont(40f));
		g.drawString("1 PLAYER", 610, 415);
		g.drawString("2 PLAYERS", 610, 480);
		g.drawString("CAREER", 610, 540);
		g.drawString("CONSTRUCTION", 610, 610);
		g.drawString("ARMORY", 610, 670);

		/*for(int i = 0; i < 10; i++) {
			g.draw(opRect[i]);
		}*/
	}

	private void select() {
		if(currentChoice == 0) {
			gsm.setState(gsm.SELECTLEVELSTATE);
		} else if(currentChoice == 2 || currentChoice == 3) {
			gsm.setState(gsm.CAREERSTATE);
		} else if(currentChoice == 4) {
			gsm.setState(gsm.CONSTRUCTIONSTATE);
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
				currentChoice = 4;
				select();
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[4])) {
				System.out.println("armory");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[5])) {
				System.out.println("settings");
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[6])) {
				currentChoice = 3;
				select();
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[7])) {
				System.out.println("plus1");
				String coins = String.valueOf(Integer.parseInt(pr.get("coins"))+1);
				pr.set("coins", coins);
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[8])) {
				System.out.println("plus2");
				String patrons = String.valueOf(Integer.parseInt(pr.get("patrons"))+1);
				pr.set("patrons", patrons);
			}
			if(getRect(e.getX(), e.getY()).intersects(opRect[9])) {
				pr.store();
				System.exit(1);
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
	@Override
	public void mouseDragged(MouseEvent e) {

	}
	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public Rectangle getRect(int x, int y) {
		return new Rectangle(x, y, 1, 1);
	}
	
}
