package Item;

import Entity.Enemy;
import Entity.Player;
import Util.Progress;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HUD {

    private BufferedImage pauseImage;
    private BufferedImage angar;
    private BufferedImage playerLives;
    private BufferedImage flag;
    private BufferedImage heart;
    private BufferedImage gun;
    private BufferedImage armor;
    private BufferedImage backButtonImage;

    private ArrayList<Enemy> enemies;
    private Progress pr;
    private Player player;
    private Font font;

    private int enemyID;
    private int level;

    public HUD(ArrayList<Enemy> enemies, Player player, int level) {
        this.enemies = enemies;
        this.player = player;
        this.level = level;
        pr = Progress.getInstance();

        try {
            pauseImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/pause2.png"));
            angar = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/angar.png"));
            playerLives = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/playerLives.png"));
            flag = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/flag.png"));
            heart = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/heart.png"));
            gun = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/gun.png"));
            armor = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/armor.png"));
            backButtonImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/backButton.png"));

            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
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
        g.drawImage(flag, 1175, 630, 64, 64, null);
        g.drawString(level+"", 1195, 725);
        g.drawString("IP", 1175, 530);
        g.drawImage(playerLives, 1168, 540, 40, 40, null);
        g.drawString(player.getLives()+"", 1213, 570);

        g.setColor(Color.decode("#737373"));
        g.fillRoundRect(70, 95, 190, 105, 10, 10);
        g.drawImage(heart, 80, 100, 90, 90, null);
        g.fillRoundRect(70, 210, 190, 105, 10, 10);
        g.drawImage(gun, 80, 215, 90, 90, null);
        g.fillRoundRect(70, 325, 190, 105, 10, 10);
        g.drawImage(armor, 80, 330, 90, 90, null);

        g.setColor(Color.WHITE);
        g.drawString(pr.get("hearts"), 190, 150);
        g.drawString(pr.get("guns"), 190, 260);
        g.drawString(pr.get("armors"), 190, 380);

        g.drawImage(backButtonImage, 90, 720, 70, 70, null);
    }

}
