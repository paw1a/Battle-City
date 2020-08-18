package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import tilemaps.TileMap;

public class Bullet {

	private int x;
	private int y;
	private int dx;
	private int dy;
	
	private int speed;
	
	private int direction;
	private boolean remove;
	private TileMap tileMap;
	
	private BufferedImage bulletSet;
	private BufferedImage bulletUp;
	private BufferedImage bulletRight;
	private BufferedImage bulletDown;
	private BufferedImage bulletLeft;

	private boolean powerBullet;
	
	public Bullet(int x, int y, int direction, boolean fastBullet, TileMap tileMap) {
		this.x = x;
		this.y = y;
		this.tileMap = tileMap;
		this.direction = direction;

		if(fastBullet) speed = 27;
		else speed = 18;
		powerBullet = false;
		
		try {
			bulletSet = ImageIO.read(getClass().getResourceAsStream("/Images/bullet.png"));
			bulletUp = bulletSet.getSubimage(0, 0, 3, 4);
			bulletRight = bulletSet.getSubimage(4, 0, 4, 3);
			bulletDown = bulletSet.getSubimage(9, 0, 3, 4);
			bulletLeft = bulletSet.getSubimage(13, 0, 4, 3);
		} catch (IOException e) {System.out.println("Bullet NO");}
		
	}
	
	public boolean update() {
		if(direction == 1) {
			moveUp();
		}
		if(direction == 2) {
			moveLeft();
		}
		if(direction == 3) {
			moveDown();
		}
		if(direction == 4) {
			moveRight();
		}
		
		if(x < 310) remove  = true;
		if(x > GamePanel.WIDTH - 310 - 4) remove = true;
		if(y < 0) remove = true;
		if(y > GamePanel.HEIGHT - 4) remove = true;
		
		for(int i = 0; i < tileMap.getWidth(); i++) {
			for(int j = 0; j < tileMap.getHeight(); j++) {
				
				if(tileMap.tiles[i][j].getType() == 1) {
					if(getRectUp().intersects(tileMap.tiles[i][j].getRectBul()) 
						|| getRectRight().intersects(tileMap.tiles[i][j].getRectBul()) 
						|| getRectDown().intersects(tileMap.tiles[i][j].getRectBul())
						|| getRectLeft().intersects(tileMap.tiles[i][j].getRectBul())) {
						if(powerBullet) tileMap.tiles[i][j].setRemove(true);
						remove = true;
					}
				} else if(tileMap.tiles[i][j].getType() == 2) {
					if(direction == 1 && getRectUp().intersects(tileMap.tiles[i][j].getRectBul())) {
						if(tileMap.tiles[i][j].downLeft == true || tileMap.tiles[i][j].downRight == true) {
							tileMap.tiles[i][j].downLeft = false;
							tileMap.tiles[i][j].downRight = false;
						} else {
							tileMap.tiles[i][j].topLeft = false;
							tileMap.tiles[i][j].topRight = false;
						}
						remove = true;
					} else if(direction == 2 && getRectLeft().intersects(tileMap.tiles[i][j].getRectBul())) {
						if(tileMap.tiles[i][j].topRight == true || tileMap.tiles[i][j].downRight == true) {
							tileMap.tiles[i][j].topRight = false;
							tileMap.tiles[i][j].downRight = false;
						} else {
							tileMap.tiles[i][j].topLeft = false;
							tileMap.tiles[i][j].downLeft = false;
						}
						remove = true;
					} else if(direction == 3 && getRectDown().intersects(tileMap.tiles[i][j].getRectBul())) {
						if(tileMap.tiles[i][j].topLeft == true || tileMap.tiles[i][j].topRight == true) {
							tileMap.tiles[i][j].topLeft = false;
							tileMap.tiles[i][j].topRight = false;
						} else {
							tileMap.tiles[i][j].downLeft = false;
							tileMap.tiles[i][j].downRight = false;
						}
						remove = true;
					} else if(direction == 4 && getRectRight().intersects(tileMap.tiles[i][j].getRectBul())) {
						if(tileMap.tiles[i][j].topLeft == true || tileMap.tiles[i][j].downLeft == true) {
							tileMap.tiles[i][j].topLeft = false;
							tileMap.tiles[i][j].downLeft = false;
						} else {
							tileMap.tiles[i][j].topRight = false;
							tileMap.tiles[i][j].downRight = false;
						}
						remove = true;
					}
					if(powerBullet && (getRectUp().intersects(tileMap.tiles[i][j].getRectBul())
							|| getRectRight().intersects(tileMap.tiles[i][j].getRectBul())
							|| getRectDown().intersects(tileMap.tiles[i][j].getRectBul())
							|| getRectLeft().intersects(tileMap.tiles[i][j].getRectBul()))) {
						tileMap.tiles[i][j].topRight = false;
						tileMap.tiles[i][j].downRight = false;
						tileMap.tiles[i][j].topLeft = false;
						tileMap.tiles[i][j].downLeft = false;
					}
				}
			}
		}
		return remove;
	}
	public void moveUp() {
		dy = -speed;
		y += dy;
	}
	public void moveRight() {
		dx = speed;
		x += dx;
	}
	public void moveDown() {
		dy = speed;
		y += dy;
	}
	public void moveLeft() {
		dx = -speed;
		x += dx;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		
		if(direction == 1) {
			g.drawImage(bulletUp, x, y, 6, 8, null);
		}
		if(direction == 2) {
			g.drawImage(bulletLeft, x, y, 8, 6, null);
		}
		if(direction == 3) {
			g.drawImage(bulletDown, x, y, 6, 8, null);
		}
		if(direction == 4) {
			g.drawImage(bulletRight, x, y, 8, 6, null);
		}
	}
	
	public Rectangle getRectUp() {
		return new Rectangle(x - 9, y, 24, 8);
	}
	public Rectangle getRectRight() {
		return new Rectangle(x, y - 9, 8, 24);
	}
	public Rectangle getRectDown() {
		return new Rectangle(x - 9, y, 24, 8);
	}
	public Rectangle getRectLeft() {
		return new Rectangle(x, y - 9, 8, 24);
	}
	public void setRemove(boolean b) {remove = b;}
	
	public int getX() { return x; }
	public int getY() { return y; }

	public void setPowerBullet(boolean powerBullet) {
		this.powerBullet = powerBullet;
	}
}
