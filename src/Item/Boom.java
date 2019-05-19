package Item;

import Entity.Animation;
import Util.Sprite;

import java.awt.*;

public class Boom {

    private int x;
    private int y;

    private Animation animation;
    private Sprite sprite;

    private boolean bigBoom;

    public Boom(int x, int y, boolean bigBoom) {
        this.x = x;
        this.y = y;
        this.bigBoom = bigBoom;

        sprite = new Sprite("/Images/boom.png");
        if(bigBoom) sprite.loadImages(0, 0, 5, 1, 32, 0, 0);
        else sprite.loadImages(0, 0, 3, 1, 32, 0, 0);
        animation = new Animation(sprite.getSpriteArray(0), 70);
    }

    public boolean update() {
        animation.update();
        if(animation.hasPlayed(1)) return true;

        return false;
    }

    public void draw(Graphics2D g) {
        g.drawImage(animation.getImage(), x, y, 100, 100, null);
    }

}
