package TileMap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Player;

public class TileMap {

	private int x = 155;
	private int y;
	
	private int tileSize;
	private int [][] map;
	private int mapWidth;
	private int mapHeight;

	private boolean armor;
	private long armorTime;
	
	private BufferedImage tileset;
	private BufferedImage image;
	private BufferedImage base;
	
	private BufferedImage[] tilesBlocks;
	public Tile[][] tiles;
	private Animation animation;

	private String [] enemyTypes;
	
	private BufferedImage[] water;
	
	public TileMap(String s, int tileSize) {
		this.tileSize = tileSize;

		String delimiters = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(s)));

			enemyTypes = new String[20];
			enemyTypes = br.readLine().split(delimiters);

			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];

			animation = new Animation();

			for(int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String [] tokens = line.split(delimiters);
				for(int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			BufferedImage imageWater = ImageIO.read(getClass().getResourceAsStream("/Images/water.png"));
			water = new BufferedImage[3];
			for(int i = 0; i < 3; i++) {
				water[i] = imageWater.getSubimage(i * 8 + i, 0, 8, 8);
			}
			
		}
		catch(Exception e) {}
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Images/image.png"));
		} catch (IOException e) {}
		base = image.getSubimage(304, 32, 16, 16);
	}
	
	public void loadTiles(String s) {
		try{
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			int numTilesAcross = (tileset.getWidth() + 1) / (tileSize + 1);
			tilesBlocks = new BufferedImage[6];
			
			BufferedImage subimage;
			
			for(int i = 0; i < numTilesAcross; i++) {
				subimage = tileset.getSubimage(
					i * (tileSize + 1),
					0,
					tileSize,
					tileSize
				);
				tilesBlocks[i] = subimage;
				
			}
			
			tiles = new Tile[mapHeight][mapWidth];
			for(int row = 0; row < mapHeight; row++) {
				for(int col = 0; col < mapWidth; col++) {
					int rc = map[row][col];
					tiles[row][col] = new Tile(tilesBlocks[rc], rc, row, col + 10, 16);
				}
			}
		} catch(Exception e) {}
		
	}
	
	public void update() {
		animation.setDelay(250);
		animation.setFrames(water);
		animation.update();
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				tiles[i][j].update();
			}
		}

		if(armor && (System.currentTimeMillis() - armorTime) > 10000) {
			armor = false;
			transform(2);
		}
	}
	
	public void draw(Graphics2D g) {
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				int rc = map[row][col];
				if(rc == 1 && !tiles[row][col].getRemove()) {
					g.drawImage(tilesBlocks[rc], (x + col * tileSize)*2, (row * tileSize)*2, 32, 32, null);
				} else 
				if(rc == 4) {
					g.drawImage(tilesBlocks[rc], (x + col * tileSize)*2, (row * tileSize)*2, 32, 32, null);
				} else 
				if(rc == 5) {
					g.drawImage(animation.getImage(), (x + col * tileSize) * 2, (row * tileSize) * 2, 16 * 2, 16 * 2, null);
				} else if(rc == 2) {
					if(!tiles[row][col].getRemove()) {
						g.drawImage(tiles[row][col].getImage(), (x + col * tileSize)*2, (row * tileSize)*2, 32, 32, null);
					} else {
						g.drawImage(tilesBlocks[0], (x + col * tileSize)*2, (row * tileSize)*2, 32, 32, null);
					}
				} else {
					g.setColor(new Color(255, 255, 255, 0));
					g.fillRect((x + col * tileSize)*2, (row * tileSize)*2, 32, 32);
				}
			}
		}
		g.setColor(Color.BLUE);
		g.drawImage(base, (12 * tileSize + 160)*2 - 10, (24 * tileSize)*2, 64, 64, null);
	}
	public void drawGrass(Graphics2D g) {
		
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				int rc = map[row][col];
				if(rc == 3) {
					g.drawImage(tilesBlocks[rc], (x + col * tileSize)*2, (row * tileSize)*2, 32, 32, null);
				} else {
					g.setColor(new Color(255, 255, 255, 0));
					g.fillRect((x + col * tileSize)*2, (row * tileSize)*2, 32, 32);
				}
			}
		}
		
	}

	public void armor() {
		armor = true;
		armorTime = System.currentTimeMillis();
		transform(1);
	}

	public int getx() {return x;}
	public int gety() {return y;}

	public String[] getEnemyTypes() {
		return enemyTypes;
	}

	public int getColTile(int x) {
		return x / tileSize;
	}
	public int getRowTile(int y) {
		return y / tileSize;
	}
	public int getTile(int row, int col) {
		return map[row][col];
	}
	public int getTileSize() {
		return tileSize;
	}
	public Tile getTiles(int row, int col) {
		return tiles[row][col];
	}
	public BufferedImage getTilesBlocks(int row) {
		return tilesBlocks[row];
	}
	public int getMap(int row, int col) {
		return map[row][col];
	}
	public int getWidth() {return mapWidth; }
	public int getHeight() {return mapHeight; }
	public Rectangle getRectBase() {
		return new Rectangle((12 * tileSize)*2 + 310, (24 * tileSize)*2, 32, 32);
	}

	public void transform(int type) {
		tiles[23][11] = new Tile(tilesBlocks[type], type, 23,11+10, 16);
		tiles[23][12] = new Tile(tilesBlocks[type], type, 23,12+10,16);
		tiles[23][13] = new Tile(tilesBlocks[type], type, 23,13+10,16);
		tiles[23][14] = new Tile(tilesBlocks[type], type, 23,14+10,16);
		tiles[24][11] = new Tile(tilesBlocks[type], type, 24,11+10,16);
		tiles[25][11] = new Tile(tilesBlocks[type], type, 25,11+10,16);
		tiles[24][14] = new Tile(tilesBlocks[type], type, 24,14+10,16);
		tiles[25][14] = new Tile(tilesBlocks[type], type, 25,14+10,16);
	}
}
