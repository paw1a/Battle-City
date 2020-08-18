package states;

import main.GamePanel;
import util.Progress;
import util.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CountState extends GameState {

    private GameStateManager gsm;
    private Progress pr;
    private Sprite sprite;
    private BufferedImage coin;

    private int levelID;
    private int levelLineWidth;

    private Font font;

    public CountState(GameStateManager gsm) {
        this.gsm = gsm;
        pr = Progress.getInstance();

        levelID = Integer.parseInt(pr.get("levelToPlay"));
        sprite = new Sprite("/Images/image.png");
        sprite.loadImages(8,4, 1, 4, 16, 0, 0);
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
            coin = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/money.png"));
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        levelLineWidth = (int)((GamePanel.WIDTH / (double)Integer.parseInt(pr.get("xpLevelMaxScore")))
                *(double) Integer.parseInt(pr.get("xp")));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.setFont(font.deriveFont(45f));
        g.setColor(Color.WHITE);
        g.drawString("STAGE "+levelID, 600, 150);

        g.setColor(Color.GRAY.darker());
        g.fillRect(1, 2, GamePanel.WIDTH-2, 20);
        g.setColor(Color.GREEN.darker());
        g.fillRect(1, 2, levelLineWidth, 20);
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(17f));
        g.drawString(pr.get("xp")+"/" +pr.get("xpLevelMaxScore") +" Level "+pr.get("xpLevel"), 280*2, 9*2);

        g.setFont(font.deriveFont(40f));
        g.setColor(Color.RED);
        g.drawString("I-PLAYER", 350, 230);
        g.setColor(Color.YELLOW.darker());
        g.drawString(pr.get("currentScore"), 410, 290);

        g.setColor(Color.WHITE);
        int killed = 0;
        for (int i = 0; i < 4; i++) {
            g.drawString(Integer.parseInt(pr.get("killed"+(i+1)))*(i+1)+(Integer.parseInt(pr.get("killed"+(i+1))) == 0 ? "" : "00"), 350, 350+i*70);
            g.drawString("PTS", 520, 350+i*70);
            g.drawString(pr.get("killed"+(i+1)), 700, 350+i*70);
            g.drawImage(sprite.getImage(0, i), 780, 310+i*70, 50, 50, null);
            killed += Integer.parseInt(pr.get("killed"+(i+1)));
        }
        g.fillRect(340, 600, 510, 6);
        g.drawString("TOTAL   " + killed, 430, 650);
        g.drawString("COINS   10", 430, 700);

        g.setColor(Color.decode("#636363"));
        g.setFont(font.deriveFont(15f));
        g.drawString("Click to continue", 500, 800);

        g.drawImage(coin, 790, 670, 30, 30, null);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pr.set("levelToPlay", (Integer.parseInt(pr.get("levelToPlay"))+1)+"");
        pr.store();
        gsm.setState(gsm.STAGESTATE);
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
