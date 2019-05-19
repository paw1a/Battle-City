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

public class CareerState extends GameState {

	GameStateManager gsm;
	
	private BufferedImage titleCareer;
	private BufferedImage[] careerImage;
	private BufferedImage yes;
	private Rectangle back = new Rectangle(40*2, 25*2, 30*2, 30*2);
	private Rectangle next = new Rectangle(530*2, 360*2, 150*2, 40*2);
	private Rectangle backPage = new Rectangle(530*2, 300*2, 150*2, 40*2);
	int page = 0;
	
	public CareerState(GameStateManager gsm) {
		this.gsm = gsm;
		
		careerImage = new BufferedImage[9];
		
		try {
			titleCareer = ImageIO.read(getClass().getResourceAsStream("/Career/titlecareer.png"));
			careerImage[0] = ImageIO.read(getClass().getResourceAsStream("/Career/рядовой.png"));
			careerImage[1] = ImageIO.read(getClass().getResourceAsStream("/Career/ефрейтор.png"));
			careerImage[2] = ImageIO.read(getClass().getResourceAsStream("/Career/млсержант.png"));
			careerImage[3] = ImageIO.read(getClass().getResourceAsStream("/Career/сержант.png"));
			careerImage[4] = ImageIO.read(getClass().getResourceAsStream("/Career/стсержант.png"));
			careerImage[5] = ImageIO.read(getClass().getResourceAsStream("/Career/старшина.png"));
			careerImage[6] = ImageIO.read(getClass().getResourceAsStream("/Career/прапорщик.png"));
			careerImage[7] = ImageIO.read(getClass().getResourceAsStream("/Career/стпрапорщик.png"));
			careerImage[8] = ImageIO.read(getClass().getResourceAsStream("/Career/мллейтенант.png"));
			
			yes = ImageIO.read(getClass().getResourceAsStream("/Career/yes.png"));
		} catch (IOException e) {System.out.println("NO FILE Career");}
		
		init();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, GamePanel.WIDTH - 2, 10);
		g.setColor(Color.GREEN.darker());
		g.fillRect(1, 1, GamePanel.WIDTH - 200*2, 10*2);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("149043/150000  УРОВЕНЬ 15", 280*2, 9*2);
		g.drawImage(titleCareer, 30*2, 11*2, 400*2, 60*2, null);
		if(page == 0) {
			g.drawImage(careerImage[0], 120*2, 72*2, 300*2, 85*2, null);
			g.drawImage(yes, 430*2, 90*2, 45*2, 40*2, null);
			g.drawImage(careerImage[1], 120*2, 160*2, 300*2, 83*2, null);
			g.drawImage(yes, 430*2, 173*2, 45*2, 40*2, null);
			g.drawImage(careerImage[2], 120*2, 252*2, 300*2, 83*2, null);
			g.drawImage(yes, 430*2, 265*2, 45*2, 40*2, null);
			g.drawImage(careerImage[3], 117*2, 333*2, 300*2, 82*2, null);
			g.drawImage(yes, 428*2, 350*2, 45*2, 40*2, null);
			g.setColor(Color.GRAY);
			g.fillRoundRect(530*2, 360*2, 150*2, 40*2, 20*2, 20*2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Century Gothic", Font.BOLD, 25*2));
			g.drawString("ДАЛЕЕ", 560*2, 390*2);
		}
		if(page == 1) {
			g.drawImage(careerImage[4], 120*2, 72*2, 320*2, 85*2, null);
			g.drawImage(yes, 450*2, 90*2, 45*2, 40*2, null);
			g.drawImage(careerImage[5], 120*2, 160*2, 320*2, 83*2, null);
			g.drawImage(yes, 450*2, 173*2, 45*2, 40*2, null);
			g.drawImage(careerImage[6], 116*2, 252*2, 325*2, 83*2, null);
			g.drawImage(yes, 450*2, 265*2, 45*2, 40*2, null);
			g.drawImage(careerImage[7], 120*2, 333*2, 320*2, 82*2, null);
			g.drawImage(yes, 448*2, 350*2, 45*2, 40*2, null);
			g.setColor(Color.GRAY);
			g.fillRoundRect(530*2, 360*2, 150*2, 40*2, 20*2, 20*2);
			g.fillRoundRect(530*2, 300*2, 150*2, 40*2, 20*2, 20*2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Century Gothic", Font.BOLD, 25*2));
			g.drawString("ДАЛЕЕ", 560*2, 390*2);
			g.drawString("НАЗАД", 560*2, 330*2);
		}
		if(page == 2) {
			page = 0;
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
			if(getRect(e.getX(), e.getY()).intersects(back)) {
				gsm.setState(gsm.MENUSTATE);
			}
			if(getRect(e.getX(), e.getY()).intersects(next)) {
				page++;
			}
			if(getRect(e.getX(), e.getY()).intersects(backPage)) {
				page--;
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
