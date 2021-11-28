package indi.zzl.trank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class TankGame extends JFrame {

    MyPanel mp;

    public static void main(String[] args) throws IOException {
        new TankGame();
    }

    public TankGame() throws IOException {
        int response = JOptionPane.showConfirmDialog(
                null,
                "是否继续上局游戏",
                "",
                JOptionPane.YES_NO_OPTION
        );
        mp = new MyPanel(response == 0);
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1400, 750 + 31 + 8);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.storeRecord();
            }
        });

        new Thread(mp).start();
    }
}
