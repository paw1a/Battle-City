package items;

import entity.Animation;
import entity.Player;
import tilemaps.TileMap;
import util.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bonus {

    private int x;
    private int y;

    private int type; //1, 2, 3 ... 6

    private Sprite sprite;
    private Animation animation;
    private boolean catched;
    private boolean visible;
    private Random random = new Random();

    private Player player;
    private TileMap tileMap;

    public Bonus(Player player, TileMap tileMap) {
        this.player = player;
        this.tileMap = tileMap;

        sprite = new Sprite("/Images/bonuses.png");
        sprite.loadImages(0, 0, 6, 1, 16, 0, 0);
        create();
    }

    public void create() {
        int n = random.nextInt(6) + 1;
        type = n;
        //type = 4;

        while(true) {
            x = random.nextInt(25);
            y = random.nextInt(25);
            if(tileMap.getTile(y, x) == 0) break;
        }
        x = x * 32 + 310;
        y *= 32;

        animation = new Animation(new BufferedImage[]{sprite.getImage(type, 0),
                sprite.getImage(0, 0)}, 200);
        visible = true;
    }

    public void update() {
        animation.update();
        if(catched) {
            visible = false;
            catched = false;
        }
    }

    public void draw(Graphics2D g) {
        if(visible) g.drawImage(animation.getImage(), x, y, 56, 56, null);
    }

    public int getType() {return type;}
    public boolean isCatched() {
        return catched;
    }
    public void setCatched(boolean b) { catched = b; }

    public Rectangle getRect() {
        return new Rectangle(x, y, 56, 56);
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
