package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Main.GamePanel;


public class SelectLevel extends GameState {
	
	
	
	private int currentChoice = 0;
	private String[] options = {
			"Уровень 1",
			"Уровень 2",
			"Уровень 3",
			"Уровень 4",
			"Уровень 5"
	};
	
	Color opColor = new Color(0, 120, 120);
	Color fonColor = new Color(50, 30, 50);
	Font font;

	public SelectLevel(GameStateManager gsm) {
		this.gsm = gsm;
		
		font = new Font("Arial", Font.PLAIN, 30);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(fonColor);
		g.fillRect(0, 0, 400, 400);
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(opColor);
			}
			g.drawString(options[i], 130, 120 + i * 40);
			g.setColor(Color.WHITE);
			if(currentChoice == i) {
				g.drawRect(125, 90 + i * 40, 150, 40);
			} 
		}
	}

	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int k) {
		
	}

	public void select() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
	
	
}
