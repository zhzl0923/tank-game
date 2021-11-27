package indi.zzl.trank;

import java.util.Vector;

public class Tank {
    private int x;//坦克x坐标
    private int y;//坦克y坐标
    private int direct;//坦克方向，0上 1右 2下 3左
    private int speed = 1;
    private boolean isLive = true;
    private final Vector<Bullet> bullets = new Vector<>();


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp() {
        if (y - speed >= 0) {
            y -= speed;
        }
    }

    public void moveRight() {
        if (x + 40 + speed <= 1000) {
            x += speed;
        }

    }

    public void moveDown() {
        if (y + 40 + speed <= 750) {
            y += speed;
        }
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            x -= speed;
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void shot() {
        int x = 0;
        int y = 0;
        switch (getDirect()) {
            case 0 -> {
                x = getX() + 17;
                y = getY() - 6;
            }
            case 1 -> {
                x = getX() + 40;
                y = getY() + 17;
            }
            case 2 -> {
                x = getX() + 17;
                y = getY() + 40;
            }
            case 3 -> {
                x = getX() - 6;
                y = getY() + 17;
            }
        }
        Bullet bullet = new Bullet(x, y, getDirect());
        this.bullets.add(bullet);
        new Thread(bullet).start();
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

}
