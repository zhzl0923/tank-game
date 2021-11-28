package indi.zzl.trank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    private Hero hero;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (getBullets() == null || getBullets().size() == 0) {
//                shot();
            }
            switch (getDirect()) {
                case 0 -> {
                    for (int i = 0; i < 30; i++) {
                        if (getY() - getSpeed() < 0 || isTouchHeroTank() || isTouchEnemyTank()) {
                            break;
                        }
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 1 -> {
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 40 + getSpeed() > 1000 || isTouchHeroTank() || isTouchEnemyTank()) {
                            break;
                        }
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 2 -> {
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 40 + getSpeed() > 750 || isTouchHeroTank() || isTouchEnemyTank()) {
                            break;
                        }
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 3 -> {
                    for (int i = 0; i < 30; i++) {
                        if (getX() - getSpeed() < 0 || isTouchHeroTank() || isTouchEnemyTank()) {
                            break;
                        }
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDirect((int) (Math.random() * 4));
            if (!isLive()) {
                break;
            }

            if (MyPanel.isGameOver) {
                break;
            }
        }

    }

    public boolean isTouchHeroTank() {
        return isTouchObstacle(hero.getX(), hero.getY(), 40, 40);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }


}
