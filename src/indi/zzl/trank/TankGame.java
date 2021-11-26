package indi.zzl.trank;

import javax.swing.*;

public class TankGame extends JFrame {

    MyPanel mp;

    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        new Thread(mp).start();
    }
}
