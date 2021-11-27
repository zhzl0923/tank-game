package indi.zzl.trank;

public class EnemyTank extends Tank implements Runnable {

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            if (getBullets() == null || getBullets().size() == 0) {
                shot();
            }
            switch (getDirect()) {
                case 0 -> {
                    for (int i = 0; i < 30; i++) {
                        if (getY() - getSpeed() < 0) {
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
                        if (getX() + 40 + getSpeed() > 1000) {
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
                        if (getY() + 40 + getSpeed() > 750) {
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
                        if (getX() - getSpeed() < 0) {
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

            if (MyPanel.isGameOver){
                break;
            }
        }

    }
}
