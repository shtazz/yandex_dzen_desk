package com.shtazz.rykosyj.desk.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DeskCreator {

    public int[][] createDesk(int width, int hight) {
        int[][] desk = new int[hight][width];
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                desk[i][j] = getRandomState();
            }
        }
        return desk;
    }

    private int getRandomState() {
        int state = 0;
        if (Math.random() >= 0.5) {
            state = 1;
        }
        return state;
    }

    public int[][] getDeskFromFile(int width, int hight, String fileName) {
        int[][] desk = new int[hight][width];
        try (Scanner fileScanner = new Scanner(new File(fileName))){
            for (int i = 0; i < desk.length; i++) {
                for (int j = 0; j < desk[i].length; j++) {
                    if (fileScanner.hasNextInt()) {
                        int cell = fileScanner.nextInt();
                        if (cell != 0 && cell != 1) {
                            throw new IllegalArgumentException(
                                    "Cell state can be only 0 or 1. Please check and update your file.");
                        }
                        desk[i][j] = cell;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return desk;
    }
}
