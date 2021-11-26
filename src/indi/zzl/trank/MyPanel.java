package indi.zzl.trank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    Hero hero;
    Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyTankSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);
        hero.setSpeed(5);
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            enemyTank.shoot();
            enemyTanks.add(enemyTank);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(g, hero.getX(), hero.getY(), hero.getDirect(), 1);
        Vector<Bullet> heroBullets = hero.getBullets();
        for (int i = 0; i < heroBullets.size(); i++) {
            if (heroBullets.get(i).isLive()) {
                drawBullet(g, heroBullets.get(i).getX(), heroBullets.get(i).getY(), 1);
            } else {
                heroBullets.remove(heroBullets.get(i));
            }
        }
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(g, enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect(), 0);
            Vector<Bullet> enemyTankBullets = enemyTank.getBullets();
            for (int i = 0; i < enemyTankBullets.size(); i++) {
                if (enemyTankBullets.get(i).isLive()) {
                    drawBullet(g, enemyTankBullets.get(i).getX(), enemyTankBullets.get(i).getY(), 0);
                } else {
                    enemyTankBullets.remove(enemyTankBullets.get(i));
                }
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDirect(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.shoot();
        }
        this.repaint();
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
            this.repaint();
        }
    }
}
