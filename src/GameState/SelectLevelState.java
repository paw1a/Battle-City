package GameState;

import Main.GamePanel;
import Util.Progress;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class SelectLevelState extends GameState {

    private GameStateManager gsm;
    private Progress pr;

    private Font font;
    private BufferedImage backButtonImage;

    private int levelLineWidth;
    private Rectangle [] rectangles;
    private Rectangle backButton;

    private Point mouse;

    public SelectLevelState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        pr = Progress.getInstance();
        mouse = new Point(0, 0);

        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
            backButtonImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/backButton.png"));
        } catch (Exception e) {}

        backButton = new Rectangle(230, 70, 70, 70);
        rectangles = new Rectangle[18];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                rectangles[k] = new Rectangle(230+j*170, 200+i*170, 150, 150);
                k++;
            }
        }
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

        g.setColor(Color.GRAY.darker());
        g.fillRect(1, 2, GamePanel.WIDTH-2, 20);
        g.setColor(Color.GREEN.darker());
        g.fillRect(1, 2, levelLineWidth, 20);
        g.setColor(Color.WHITE);
        g.setFont(font.deriveFont(17f));
        g.drawString(pr.get("xp")+"/" +pr.get("xpLevelMaxScore") +" Level "+pr.get("xpLevel"), 280*2, 9*2);

        g.drawImage(backButtonImage, 230, 70, 70, 70, null);

        g.setFont(font.deriveFont(60f));
        for (int i = 0; i < rectangles.length; i++) {
            g.setColor(Color.WHITE);
            g.fillRoundRect(rectangles[i].x-3, rectangles[i].y-3,
                    rectangles[i].width+6, rectangles[i].height+6, 20, 20);
            if(rectangles[i].contains(mouse)) g.setColor(Color.decode("#838383"));
            else g.setColor(Color.decode("#737373"));
            g.fillRoundRect(rectangles[i].x, rectangles[i].y,
                    rectangles[i].width, rectangles[i].height, 20, 20);
            g.setColor(Color.BLACK);
            if(i < 9) g.drawString((i+1)+"", rectangles[i].x+50, rectangles[i].y+95);
            else g.drawString((i+1)+"", rectangles[i].x+23, rectangles[i].y+95);
        }

        g.setColor(Color.WHITE);
        g.fillOval(690, 750, 30, 30);
        g.fillOval(740, 750, 30, 30);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(backButton.contains(e.getPoint())) gsm.setState(GameStateManager.MENUSTATE);
        for (int i = 0; i < rectangles.length; i++) {
            if(rectangles[i].contains(e.getPoint())) {
                pr.set("levelToPlay", (i+1)+"");
                pr.store();
                gsm.setState(GameStateManager.STAGESTATE);
            }
        }
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
        mouse = e.getPoint();
    }
}
