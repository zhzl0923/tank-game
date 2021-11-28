package indi.zzl.trank;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int allEnemyThankNum = 0;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\my_record.txt";
    private static Vector<EnemyTank> enemyTanks = new Vector<>();
    private static Hero hero;

    public static int getAllEnemyThankNum() {
        return allEnemyThankNum;
    }

    public static void setAllEnemyThankNum(int allEnemyThankNum) {
        Recorder.allEnemyThankNum = allEnemyThankNum;
    }

    public static void addAlEnemyThankNum() {
        allEnemyThankNum += 1;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static void setHero(Hero hero) {
        Recorder.hero = hero;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void storeRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyThankNum + "\r\n");
            bw.write(hero.getX() + " " + hero.getY() + " " + hero.getDirect() + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                bw.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readRecord() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyThankNum = Integer.parseInt(br.readLine());
            String content = br.readLine();
            String[] info = content.split(" ");
            hero = new Hero(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
            while ((content = br.readLine()) != null) {
                info = content.split(" ");
                EnemyTank enemyTank = new EnemyTank(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                enemyTanks.add(enemyTank);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
