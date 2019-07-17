package GameState;

import Main.GamePanel;
import Util.Progress;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class ConstructionState extends GameState {

    private GameStateManager gsm;
    private int mouseX;
    private int mouseY;

    private static final int WIDTH = 26;
    private static final int HEIGHT = 26;

    private int [][] map;
    private Rectangle [][] rectangles;
    private Rectangle [] tileButtons;
    private Rectangle saveButton;
    private Rectangle playButton;
    private Rectangle backButton;

    private BufferedImage [] tiles;
    private BufferedImage backButtonImage;
    private int currentTile;

    private Font font;
    private Progress pr;

    public ConstructionState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        BufferedImage image = null;
        tiles = new BufferedImage[6];
        pr = Progress.getInstance();

        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("joystix.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(45f);
            image = ImageIO.read(getClass().getResourceAsStream("/Images/tileset2.png"));
            backButtonImage = ImageIO.read(getClass().getResourceAsStream("/Images/HUD/backButton.png"));
        } catch (Exception e) {}

        for (int i = 0; i < 6; i++) {
            tiles[i] = image.getSubimage(i*16+i, 0, 16, 16);
        }
        
        rectangles = new Rectangle[HEIGHT][WIDTH];
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j] = new Rectangle(j*32, i*32, 32, 32);
            }
        }
        map = new int[HEIGHT][WIDTH];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 0;
            }
        }

        currentTile = 0;

        tileButtons = new Rectangle[6];
        for (int i = 0; i < tileButtons.length; i++) {
            tileButtons[i] = new Rectangle(32*26+100, 150+80*i, 70, 70);
        }

        saveButton = new Rectangle(32*26+60, 650, 150, 70);
        playButton = new Rectangle(32*26+30, 740, 210, 70);
        backButton = new Rectangle(32*26+100, 50, 70, 70);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.decode("#636363"));
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                g.draw(rectangles[i][j]);
            }
        }
        g.drawLine(0, 26*32-1, 26*32, 26*32-1);
        if(mouseX < 32*26) g.fill(rectangles[mouseY/32][mouseX/32]);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                g.drawImage(tiles[map[i][j]], rectangles[i][j].x, rectangles[i][j].y, 32, 32, null);
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            g.drawImage(tiles[i], 32*26+100, 150+80*i, 70, 70, null);
        }

        for (int i = 0; i < tileButtons.length; i++) {
            g.draw(tileButtons[i]);
        }

        if(saveButton.contains(mouseX, mouseY)) g.setColor(Color.decode("#434343"));
        g.fillRect(32*26+60, 650, 150, 70);
        g.setColor(Color.decode("#636363"));
        if(playButton.contains(mouseX, mouseY)) g.setColor(Color.decode("#434343"));
        g.fillRect(32*26+30, 740, 210, 70);
        g.setColor(Color.WHITE);
        g.drawString("SAVE", 32*26+60, 700);
        g.drawString("<PLAY>", 32*26+25, 790);
        g.draw(saveButton);
        g.draw(playButton);

        g.setColor(Color.RED);
        g.draw(tileButtons[currentTile]);

        g.drawImage(backButtonImage, 32*26+100, 50, 70, 70, null);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                if(rectangles[i][j].contains(e.getX(), e.getY())) {
                    map[i][j] = currentTile;
                }
            }
        }
        for (int i = 0; i < tileButtons.length; i++) {
            if(tileButtons[i].contains(e.getX(), e.getY())) {
                currentTile = i;
            }
        }
        if(saveButton.contains(e.getX(), e.getY())) {
            saveMap();
        }
        if(playButton.contains(e.getPoint())) {
            saveMap();
            pr.set("levelToPlay", "100");
            pr.store();
            gsm.setState(gsm.LEVELSTATE);
        }
        if(backButton.contains(e.getPoint())) {
            gsm.setState(GameStateManager.MENUSTATE);
        }

    }

    private void saveMap() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter("res/Levels/Level_100")));
            writer.println("11223344111231423143");
            writer.println(26);
            writer.println(26);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    writer.print(map[i][j]);
                }
                writer.println();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("SAVED");
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
        mouseX = e.getX();
        mouseY = e.getY();
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                if(rectangles[i][j].contains(e.getX(), e.getY())) {
                    map[i][j] = currentTile;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
