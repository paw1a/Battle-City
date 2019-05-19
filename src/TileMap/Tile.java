package TileMap;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	
	private BufferedImage image;
	private BufferedImage ts;
	
	private int type;
	private int x;
	private int y;
	private int tileSize;
	private boolean blocked;
	private boolean removeBlock = false;
	
	public boolean topLeft;
	public boolean topRight;
	public boolean downRight;
	public boolean downLeft;
	
	//block constructor
	public Tile(BufferedImage image, int type, int row, int col, int tileSize) {
		this.image = image;
		this.type = type;
		this.tileSize = tileSize;
		try {
			ts = ImageIO.read(getClass().getResourceAsStream("/Images/tilesetbrick.png"));
		} catch (IOException e) {}
		
		topLeft = true;
		topRight = true;
		downLeft = true;
		downRight = true;
		
		x = (col * tileSize)*2;
		y = (row * tileSize)*2;
	}
	
	public void update() {
		if(removeBlock) {
			type = 0;
		}
		
		if(topLeft == true && topRight == true && downRight == true && downLeft == true) image = image;
		if(topLeft == false && topRight == false && downRight == false && downLeft == false) removeBlock = true;
		
		if(topLeft == true && topRight == false && downRight == false && downLeft == false) image = ts.getSubimage(0, 16, 16, 16);
		if(topLeft == false && topRight == true && downRight == false && downLeft == false) image = ts.getSubimage(16, 16, 16, 16);
		if(topLeft == false && topRight == false && downRight == true && downLeft == false) image = ts.getSubimage(32, 16, 16, 16);
		if(topLeft == false && topRight == false && downRight == false && downLeft == true) image = ts.getSubimage(48, 16, 16, 16);
		
		if(topLeft == true && topRight == true && downRight == false && downLeft == false) image = ts.getSubimage(0, 32, 16, 16);
		if(topLeft == false && topRight == true && downRight == true && downLeft == false) image = ts.getSubimage(16, 32, 16, 16);
		if(topLeft == false && topRight == false && downRight == true && downLeft == true) image = ts.getSubimage(32, 32, 16, 16);
		if(topLeft == true && topRight == false && downRight == false && downLeft == true) image = ts.getSubimage(48, 32, 16, 16);
		
		if(topLeft == true && topRight == false && downRight == true && downLeft == false) image = ts.getSubimage(0, 48, 16, 16);
		if(topLeft == false && topRight == true && downRight == false && downLeft == true) image = ts.getSubimage(16, 48, 16, 16);
		
		if(topLeft == true && topRight == true && downRight == false && downLeft == true) image = ts.getSubimage(0, 64, 16, 16);
		if(topLeft == true && topRight == true && downRight == true && downLeft == false) image = ts.getSubimage(16, 64, 16, 16);
		if(topLeft == false && topRight == true && downRight == true && downLeft == true) image = ts.getSubimage(32, 64, 16, 16);
		if(topLeft == true && topRight == false && downRight == true && downLeft == true) image = ts.getSubimage(48, 64, 16, 16);
	}
	
	public int getType() {return type;}
	public int getX() {return x;}
	public int getY() {return y;}
	public BufferedImage getImage() {return image;}
	public boolean isBlock() {
		if(type == 1 || type == 2 || type == 5) {
			blocked = true;
		} else if(type == 0) {
			blocked = false;
		} else {
			blocked = false;
		}
		return blocked;
	}
	public Rectangle getRect() {
		return new Rectangle((x-10)+2, (y)+2, (tileSize)*2-2, (tileSize)*2-2);
	}
	public Rectangle getRectBul() {
		return new Rectangle((x-10) - 3, (y+2) - 3, tileSize*2 + 6, tileSize*2 + 6);
	}
	public void setRemove(boolean b) { removeBlock = b; }
	public boolean getRemove() { return removeBlock; }
}
