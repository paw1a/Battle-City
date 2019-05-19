package Item;

import Entity.Enemy;
import Entity.Player;
import Util.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HUD {

    private BufferedImage pauseImage;
    private BufferedImage angar;
    private BufferedImage playerLives;

    private ArrayList<Enemy> enemies;
    private Player player;
    private Font font;

    private int enemyID;


    public HUD(ArrayList<Enemy> enemies, Player player) {
        this.enemies = enemies;
        this.player = player;

        try {
            pauseImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/pause2.png"));
            angar = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/angar.png"));
            playerLives = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/playerLives.png"));

            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(24f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

    }

    public void update(int id) {
        enemyID = id;
    }

    public void draw(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawImage(pauseImage, 1170, 30, 80, 80, null);
        for (int i = 0; i < 20 - enemyID; i++) {
            g.drawImage(angar, 1175+(i % 2)*32, 130+(i / 2)*32, 32, 32, null);
        }

    }

}
