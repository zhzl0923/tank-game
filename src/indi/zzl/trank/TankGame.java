package indi.zzl.trank;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TankGame extends JFrame {

    MyPanel mp;

    public static void main(String[] args) throws IOException {
        new TankGame();
    }

    public TankGame() throws IOException {
        mp = new MyPanel();
        this.setSize(1000 + 8 + 8, 750 + 31 + 8);
        this.add(mp);
        this.addKeyListener(mp);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        new Thread(mp).start();
    }
}
