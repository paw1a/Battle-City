package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import tilemaps.TileMap;
import util.Sprite;

public class Player extends Entity {
	
	public ArrayList<Bullet> bullets;
	private long respawnTime;
	private Animation respawnAnimation;
	private boolean isRespawn;
	private long beforeRespawn;

	private boolean powerBullet;

	public Player(TileMap tilemap) {
		x = 593;
		y = 766;
		
		dx = 0;
		dy = 0;
		speed = 6;
		lives = 2;
		level = 1;
		if(level == 1) fastBullet = false;
		else fastBullet = true;

		setDirection(1);

		sprite = new Sprite("/Images/image.png");
		sprite.loadImages(0, 0, 8, 4, 16, 0, 0);

		animations = new Animation[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0, k = 0; j < 8; j+=2, k++) {
				animations[i][k] = new Animation(
						new BufferedImage[]{sprite.getImage(j, i), sprite.getImage(j+1, i)}, 150);
			}
		}

		currentAnimation = animations[level-1][direction-1];
		
		firing = false;
		fireTimer = System.nanoTime();
		
		tileMap = tilemap;	
		bullets = new ArrayList<Bullet>();

		sprite.loadImages(16, 9, 2, 1, 16, 0, 0);
		respawnAnimation = new Animation(new BufferedImage[]{sprite.getImage(0, 0),
				sprite.getImage(1, 0)}, -1);
		respawn(3000);
	}
	
	public void update() {
		x += dx;
		y += dy;

		currentAnimation = animations[level-1][direction-1];
		currentAnimation.update();
		respawnAnimation.update();

		powerBullet = false;
		if(level == 1) {
			fireDelay = 230;
			fastBullet = false;
		} else if(level == 2) {
			fireDelay = 230;
			fastBullet = true;
		} else if(level == 3) {
			fireDelay = 230;
			fastBullet = true;
		} else {
			fireDelay = 300;
			fastBullet = false;
			powerBullet = true;
		}

		if(firing) {
			long elapsed = (System.nanoTime() - fireTimer) / 1000000;

			if((elapsed > fireDelay && bullets.size() == 0) || (level == 3 && elapsed > fireDelay)) {
				if(right) {
					bullets.add(new Bullet(x + 40, y + 24, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				}else
				if(down) {
					bullets.add(new Bullet(x + 23, y + 40, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				} else
				if(left) {
					bullets.add(new Bullet(x + 3, y + 24, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				} else 
				if(up) {
					bullets.add(new Bullet(x + 23, y + 3, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				}
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).setPowerBullet(powerBullet);
		}

		if(System.currentTimeMillis() - beforeRespawn > respawnTime) {
			respawnAnimation.setDelay(-1);
			isRespawn = false;
		}

	}

	public void respawn(int time) {
		respawnTime = time;
		beforeRespawn = System.currentTimeMillis();
		isRespawn = true;
		respawnAnimation.setDelay(50);
	}

	public void upgrade() {
		if(level < 4) level++;
	}

	public void checkCollisions() {
		if(x < 310) x = 310;
		if(x > GamePanel.WIDTH - 310 - width) x = GamePanel.WIDTH - 310 - width;
		if(y < 0) y = 0;
		if(y > GamePanel.HEIGHT - height) y = GamePanel.HEIGHT - height;

		//check tilemap collisions

		for(int i = 0; i < tileMap.getWidth(); i++) {
			for(int j = 0; j < tileMap.getHeight(); j++) {
				if(getRect().intersects(tileMap.tiles[i][j].getRect()) && tileMap.tiles[i][j].isBlock()) {
					stop = true;
				}
			}
		}

		if(stop) {
			x -= dx;
			y -= dy;
			stop = false;
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(currentAnimation.getImage(), x, y, width, height, null);
		g.drawImage(respawnAnimation.getImage(), x-3, y+2, width, height, null);
		g.setColor(Color.BLUE);
	}
	
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			dy = -speed;
			dx = 0;
			setDirection(1);
		}
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			dx = -speed;
			dy = 0;
			setDirection(2);
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			dy = speed;
			dx = 0;
			setDirection(3);
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			dx = speed;
			dy = 0;
			setDirection(4);
		}
		if(key == KeyEvent.VK_SPACE) {
			setFiring(true);
		}
	}
	
	public void keyReleased(int key) {
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			dx = 0;
		}
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			dx = 0;
		}
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			dy = 0;
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			dy = 0;
		}
		if(key == KeyEvent.VK_SPACE) {
			setFiring(false);
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width-4, height-4);
	}
	public void setLevel(int level) {level++;}
	public boolean isRespawn() {return isRespawn;}
}
