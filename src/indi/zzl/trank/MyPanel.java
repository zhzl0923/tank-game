package indi.zzl.trank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    public static boolean isGameOver = false;
    Hero hero;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    Image image1;
    Image image2;
    Image image3;

    int enemyTankSize = 10;

    public MyPanel() throws IOException {
        Random random = new Random();
        int x = random.nextInt(960);
        int y = random.nextInt(710);
        hero = new Hero(x, y);
        hero.setSpeed(5);
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTank.shot();
            enemyTanks.add(enemyTank);
            enemyTank.setHero(hero);
            enemyTank.setEnemyTanks(enemyTanks);
            new Thread(enemyTank).start();
        }
        hero.setEnemyTanks(enemyTanks);
        image1 = ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/boom1.png")));
        image2 = ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/boom2.png")));
        image3 = ImageIO.read(Objects.requireNonNull(MyPanel.class.getResource("/boom3.png")));
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        if (hero.isLive()) {
            drawTank(g, hero.getX(), hero.getY(), hero.getDirect(), 1);
        }
        Vector<Bullet> heroBullets = hero.getBullets();
        for (int i = 0; i < heroBullets.size(); i++) {
            Bullet bullet = heroBullets.get(i);
            if (bullet.isLive()) {
                drawBullet(g, bullet.getX(), bullet.getY(), 1);
            } else {
                heroBullets.remove(bullet);
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive()) {
                drawTank(g, enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), 0);
            }
            Vector<Bullet> enemyTankBullets = enemyTank.getBullets();
            for (int j = 0; j < enemyTankBullets.size(); j++) {
                if (enemyTankBullets.get(j).isLive()) {
                    drawBullet(g, enemyTankBullets.get(j).getX(), enemyTankBullets.get(j).getY(), 0);
                } else {
                    enemyTankBullets.remove(enemyTankBullets.get(j));
                }
            }
            if (!enemyTank.isLive() && enemyTank.getBullets().size() == 0) {
                enemyTanks.remove(enemyTank);
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 6) {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 40, 40, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 40, 40, this);
            } else {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 40, 40, this);
                if (!hero.isLive()) {
                    isGameOver = true;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            }
            bomb.lifeDown();
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }
    }

    public void drawTank(Graphics g, int x, int y, int direct, int type) {
        switch (type) {
            case 0 -> g.setColor(Color.cyan);
            case 1 -> g.setColor(Color.yellow);
        }
        switch (direct) {
            case 0 -> {
                g.fill3DRect(x, y, 10, 40, false);
                g.fill3DRect(x + 30, y, 10, 40, false);
                g.fill3DRect(x + 10, y + 10, 20, 20, false);
                g.fillOval(x + 15, y + 15, 10, 10);
                g.drawLine(x + 20, y + 20, x + 20, y);
            }
            case 1 -> {
                g.fill3DRect(x, y, 40, 10, false);
                g.fill3DRect(x, y + 30, 40, 10, false);
                g.fill3DRect(x + 10, y + 10, 20, 20, false);
                g.fillOval(x + 15, y + 15, 10, 10);
                g.drawLine(x + 20, y + 20, x + 40, y + 20);
            }
            case 2 -> {
                g.fill3DRect(x, y, 10, 40, false);
                g.fill3DRect(x + 30, y, 10, 40, false);
                g.fill3DRect(x + 10, y + 10, 20, 20, false);
                g.fillOval(x + 15, y + 15, 10, 10);
                g.drawLine(x + 20, y + 20, x + 20, y + 40);
            }
            case 3 -> {
                g.fill3DRect(x, y, 40, 10, false);
                g.fill3DRect(x, y + 30, 40, 10, false);
                g.fill3DRect(x + 10, y + 10, 20, 20, false);
                g.fillOval(x + 15, y + 15, 10, 10);
                g.drawLine(x, y + 20, x + 20, y + 20);
            }
            default -> System.out.println("暂未处理");
        }
    }

    public void drawBullet(Graphics g, int x, int y, int type) {
        switch (type) {
            case 0 -> g.setColor(Color.cyan);
            case 1 -> g.setColor(Color.yellow);
        }
        g.fill3DRect(x, y, 6, 6, false);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (hero.isLive()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                hero.setDirect(0);
                if (!hero.isTouchEnemyTank()) {
                    hero.moveUp();
                }

            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                hero.setDirect(1);
                if (!hero.isTouchEnemyTank()) {
                    hero.moveRight();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                hero.setDirect(2);
                if (!hero.isTouchEnemyTank()) {
                    hero.moveDown();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                hero.setDirect(3);
                if (!hero.isTouchEnemyTank()) {
                    hero.moveLeft();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.shot();
            }
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitEnemyTank();
            hitHeroTank();
            this.repaint();
            if (isGameOver) {
                break;
            }
        }
    }

    public void hitTank(Bullet bullet, Tank tank) {
        if (bullet.getX() >= tank.getX() && bullet.getX() <= tank.getX() + 40
                && bullet.getY() >= tank.getY() && bullet.getY() <= tank.getY() + 40) {
            Bomb bomb = new Bomb(tank.getX(), tank.getY());
            bombs.add(bomb);
            bullet.setLive(false);
            tank.setLive(false);
        }
    }

    public void hitEnemyTank() {
        Vector<Bullet> heroBullets = hero.getBullets();
        for (int i = 0; i < heroBullets.size(); i++) {
            Bullet bullet = heroBullets.get(i);
            if (bullet != null && bullet.isLive()) {
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    if (enemyTank.isLive()) {
                        hitTank(bullet, enemyTank);
                    }
                }
            }
        }
    }


    public void hitHeroTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.getBullets().size(); j++) {
                Bullet bullet = enemyTank.getBullets().get(j);
                hitTank(bullet, hero);
            }
        }
    }
}
