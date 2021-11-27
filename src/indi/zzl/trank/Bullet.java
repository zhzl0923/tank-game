package indi.zzl.trank;

public class Bullet implements Runnable {
    private int x;//子弹x坐标
    private int y;//子弹y坐标
    private int direct;//子弹方向，0上 1右 2下 3左
    private int speed = 3;//子弹速度
    private boolean isLive = true;

    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true) {
            move();
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                isLive = false;
                break;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (MyPanel.isGameOver) {
                break;
            }
        }
    }

    public void move() {
        if (direct == 0) {
            y -= speed;
        }
        if (direct == 1) {
            x += speed;
        }
        if (direct == 2) {
            y += speed;
        }
        if (direct == 3) {
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
