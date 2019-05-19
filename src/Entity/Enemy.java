package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import Main.GamePanel;
import TileMap.TileMap;
import Util.Sprite;

public class Enemy extends Entity implements ActionListener {

	public static Random rand = new Random();
	public Random randTime = new Random();
	Timer timer = new Timer(1, this);

	public boolean dead;
	private boolean bonus;

	private int time = 0;
	private int delay;
	
	public ArrayList<Bullet> enemyBullets;
	private Sprite bonusSprite;

	private Player player;

	public Enemy(int level, int position, TileMap tm, Player pl, boolean bonus) {
		this.level = level;
		enemyBullets = new ArrayList<Bullet>();
		tileMap = tm;
		player = pl;
		this.bonus = bonus;

		timer.start();

		if(level == 4) lives = 3;
		else lives = 1;
		if(level == 2) speed = 8;
		else speed = 4;
		if(level == 3) fastBullet = true;
		else fastBullet = false;

		y = 10;
		dx = 0;
		dy = speed;

		setDirection(3);

		loadAnimations();

		if(position == 1) x = 315;
		if(position == 2) x = 700;
		if(position == 3) x = 1085;
		
		firing = false;
		fireDelay = 200;
		fireTimer = System.nanoTime();
		delay = randTime.nextInt(2000);
	}
	
	public void update() {
		if(direction == 1) {
			dy = -speed;
			dx = 0;
		} else
		if(direction == 2) {
			dx = -speed;
			dy = 0;
		} else
		if(direction == 3) {
			dy = speed;
			dx = 0;
		} else
		if(direction == 4) {
			dx = speed;
			dy = 0;
		}

		x += dx;
		y += dy;

		if(level == 4) currentAnimation = animations[2 - (lives-1)][direction-1];
		else currentAnimation = animations[level - 1][direction - 1];
		currentAnimation.update();

		if(firing) {
			long elapsed = (System.nanoTime() - fireTimer) / 1000000;
			if(elapsed > fireDelay && enemyBullets.size() == 0) {
				if(direction == 4) {
					enemyBullets.add(new Bullet(x + 40, y + 24, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				}else
				if(direction == 3) {
				enemyBullets.add(new Bullet(x + 23, y + 40, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				} else
				if(direction == 2) {
					enemyBullets.add(new Bullet(x + 3, y + 24, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				} else 
				if(direction == 1) {
					enemyBullets.add(new Bullet(x + 23, y + 3, direction, fastBullet, tileMap));
					fireTimer = System.nanoTime();
				}
			}
			firing = false;
		}
		
	}

	public void checkCollisions() {
		if(lives == 1) dead = true;

		if(x < 310) {x = 310;setDirection(rand.nextInt(4)+1);}
		if(x > GamePanel.WIDTH - 310 - width) {x = GamePanel.WIDTH - 310 - width;setDirection(rand.nextInt(4)+1);}
		if(y < 0) {y = 0;setDirection(rand.nextInt(4)+1);}
		if(y > GamePanel.HEIGHT - height) {y = GamePanel.HEIGHT - height;setDirection(rand.nextInt(4)+1);}

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
			setDirection(rand.nextInt(4) + 1);
			stop = false;
		}
	}

	public void loadAnimations() {
		sprite = new Sprite("/Images/image.png");
		animations = new Animation[3][4];
		bonusSprite = new Sprite("/Images/image.png");
		if(bonus) bonusSprite.loadImages(8, 12, 8, 4, 16, 0, 0);

		if(level == 4) {
			sprite.loadImages(8, 7, 8, 1, 16, 0, 0);
			for (int j = 0, k = 0; j < 8; j += 2, k++) {
				if(bonus) {
					animations[0][k] = new Animation(
							new BufferedImage[]{sprite.getImage(j, 0), bonusSprite.getImage(j, 3),
									sprite.getImage(j + 1, 0), bonusSprite.getImage(j + 1, 3)}, 150);
				} else {
					animations[0][k] = new Animation(
							new BufferedImage[]{sprite.getImage(j, 0), sprite.getImage(j + 1, 0)}, 150);
				}
			}
			sprite.loadImages(0, 7, 8, 1, 16, 0, 0);
			for (int j = 0, k = 0; j < 8; j += 2, k++) {
				animations[1][k] = new Animation(
						new BufferedImage[]{sprite.getImage(j, 0), sprite.getImage(j+1, 0)}, 150);
			}
			sprite.loadImages(0, 15, 8, 1, 16, 0, 0);
			for (int j = 0, k = 0; j < 8; j += 2, k++) {
				animations[2][k] = new Animation(
						new BufferedImage[]{sprite.getImage(j, 0), sprite.getImage(j+1, 0)}, 150);
			}
			currentAnimation = animations[2 - (lives-1)][direction-1];
		} else {
			sprite.loadImages(8, 4, 8, 3, 16, 0, 0);
			for (int i = 0; i < 3; i++) {
				for (int j = 0, k = 0; j < 8; j += 2, k++) {
					if(bonus) {
						animations[i][k] = new Animation(
								new BufferedImage[]{sprite.getImage(j, i), bonusSprite.getImage(j, i),
										sprite.getImage(j + 1, i), bonusSprite.getImage(j + 1, i)}, 150);
					} else {
						animations[i][k] = new Animation(
								new BufferedImage[]{sprite.getImage(j, i), sprite.getImage(j + 1, i)}, 150);
					}
				}
			}
			currentAnimation = animations[level - 1][direction - 1];
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(currentAnimation.getImage(), x, y, width, height, null);
		for(int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).draw(g);
		}
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	public boolean isBonus() { return bonus; }

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time++;
		if(time > delay) {
			firing = true;
			delay = randTime.nextInt(2000);
			time = 0;
		}
	}
	
	
}
