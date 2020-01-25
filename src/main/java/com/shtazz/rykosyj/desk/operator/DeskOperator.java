package com.shtazz.rykosyj.desk.operator;

public class DeskOperator implements Runnable{

    private int[][] desk;

    public int[][] getDesk() {
        return desk;
    }

    public void setDesk(int[][] desk) {
        this.desk = desk;
    }
    @Override
    public void run(){
        Thread current = Thread.currentThread();
        while(!current.isInterrupted()) {
            updateDeskState();
            printDesk();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                current.interrupt();
                System.out.println("Thread was interrupted");
            }
        }
    }

    public void updateDeskState() {
        int[][] oldDeskState = desk;
        int hight = desk.length;
        int width = desk[0].length;
        int[][] newDesk = new int[hight][width];
        for (int i = 0; i < oldDeskState.length; i++) {
            for (int j = 0; j < oldDeskState[i].length; j++) {
                switch (oldDeskState[i][j]) {
                case (0):
                    newDesk[i][j] = calculateFromDeadCell(oldDeskState, i, j);
                    break;
                case (1):
                    newDesk[i][j] = calculateFromLivingCell(oldDeskState, i, j);
                    break;
                default:
                    break;
                }
            }
        }
        desk = newDesk;
    }

    private int calculateFromLivingCell(int[][] oldDeskState, int i, int j) {
        int neighboursSum = calculateNeighboursSum(oldDeskState, i, j);
        if (neighboursSum == 2 || neighboursSum == 3) {
            return 1;
        }
        return 0;
    }

    private int calculateFromDeadCell(int[][] oldDeskState, int i, int j) {
        int neighboursSum = calculateNeighboursSum(oldDeskState, i, j);
        if (neighboursSum == 3) {
            return 1;
        }
        return 0;
    }

    private int calculateNeighboursSum(int[][] oldDeskState, int i, int j) {
        int neighboursSum;
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        if (i > 0 && j > 0) {
            n1 = oldDeskState[i - 1][j - 1];
        }
        if (i > 0) {
            n2 = oldDeskState[i - 1][j];
        }
        if (i > 0 && j < oldDeskState[i].length - 1) {
            n3 = oldDeskState[i - 1][j + 1];
        }
        if (j > 0) {
            n4 = oldDeskState[i][j - 1];
        }
        if (j < oldDeskState[i].length - 1) {
            n5 = oldDeskState[i][j + 1];
        }
        if (i < oldDeskState.length - 1 && j > 0) {
            n6 = oldDeskState[i + 1][j - 1];
        }
        if (i < oldDeskState.length - 1) {
            n7 = oldDeskState[i + 1][j];
        }
        if (i < oldDeskState.length - 1 && j < oldDeskState[i].length - 1) {
            n8 = oldDeskState[i + 1][j + 1];
        }
        neighboursSum = n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8;
        return neighboursSum;
    }
    
    public void printDesk () {
        System.out.println();
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                System.out.print(desk[i][j] + " ");
            }
            System.out.println();
        }
    }
}
