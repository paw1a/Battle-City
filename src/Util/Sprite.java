package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    private String file;

    private BufferedImage image;
    private BufferedImage[][] images;

    public Sprite(String file) {
        this.file = file;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(file));
        } catch (IOException e) {
            System.out.println("Error in loading " + file);
        }
    }

    public void loadImages(int x, int y, int width, int height, int size, int xOffset, int yOffset) {
        images = new BufferedImage[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                images[i][j] = image.getSubimage(x*size + j*size + j*xOffset, y*size + i*size + i*yOffset, size, size);
            }
        }
    }

    public BufferedImage getImage(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    public BufferedImage[][] getImages() {return images;}

    public BufferedImage getImage(int x, int y) {return images[y][x];}
    public BufferedImage[] getSpriteArray(int i) {return images[i];}

}
