package GameState;

import Main.GamePanel;
import Util.Progress;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.InputStream;

public class StageState extends GameState {

    private GameStateManager gsm;
    private Font font;
    private Progress pr;
    private boolean initializing;

    public StageState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(50f);
        } catch (Exception e){}
        pr = Progress.getInstance();
        initializing = true;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#535353"));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("STAGE " + pr.get("levelToPlay"), 600, 410);

        if(!initializing) gsm.setState(gsm.LEVELSTATE);
        initializing = false;
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
