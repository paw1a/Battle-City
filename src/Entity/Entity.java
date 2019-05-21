package Entity;

import TileMap.TileMap;
import Util.Sprite;

public class Entity {

    public int x;
    public int y;

    public int dx;
    public int dy;
    public int speed;

    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;

    public int direction; //1-up, 2-left, 3-down, 4-right

    public boolean stop;

    public int height = 56;
    public int width = 56;

    public int lives; //3
    public int level; //1, 2, 3, 4
    public boolean fastBullet; //false-slow, true-fast

    public Sprite sprite;

    public Animation currentAnimation;
    public Animation[][] animations;

    public TileMap tileMap;

    public boolean firing;
    public long fireTimer;
    public long fireDelay;

    public void setFiring(boolean b) { firing = b; }

    public void setDirection(int direction) {
        this.direction = direction;
        up = false;
        right = false;
        down = false;
        left = false;
        if(direction == 1) { up = true; }
        else if(direction == 2) {left = true;}
        else if(direction == 3) {down = true;}
        else if(direction == 4) {right = true;}
        else up = true;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getLives() { return lives; }

}
