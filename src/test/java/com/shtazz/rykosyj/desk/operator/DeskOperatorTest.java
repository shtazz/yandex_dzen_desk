package com.shtazz.rykosyj.desk.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.shtazz.rykosyj.desk.operator.DeskOperator;

public class DeskOperatorTest {

    private DeskOperator deskOperator = new DeskOperator();
    
    private int[][] inputDesk1 = { 
            { 1, 1, 0 }, 
            { 1, 1, 0 }, 
            { 0, 0, 0 } };
    private int[][] expectedDesk1 = { 
            { 1, 1, 0 },
            { 1, 1, 0 }, 
            { 0, 0, 0 } };
    private int[][] inputDesk2 = { 
            { 1, 1, 0, 0, 0 }, 
            { 1, 1, 0, 1, 0 }, 
            { 0, 0, 0, 0, 1 },
            { 1, 0, 0, 1, 1 },
            { 0, 1, 0, 0, 1 } };
    private int[][] expectedDesk2 = {
            { 1, 1, 1, 0, 0 },
            { 1, 1, 1, 0, 0 }, 
            { 1, 1, 1, 0, 1 }, 
            { 0, 0, 0, 1, 1 },
            { 0, 0, 0, 1, 1 } };
    private int[][] inputDesk3 = { 
            { 1, 0, 0, 0, 1, 0, 0, 1, 1, 0 },
            { 1, 0, 1, 1, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1, 1, 0 },
            { 1, 1, 1, 1, 0, 1, 1, 1, 0, 0 },
            { 1, 0, 0, 1, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1, 0, 1 }, 
            { 1, 0, 0, 0, 0, 0, 1, 0, 1, 1 }, 
            { 1, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, 
            { 0, 1, 1, 1, 0, 1, 1, 0, 0, 1 } };
    private int[][] expectedDesk3 = { 
            { 0, 1, 0, 1, 0, 0, 0, 1, 0, 0 },
            { 0, 1, 1, 0, 0, 1, 1, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
            { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0 }, 
            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
            { 0, 0, 0, 1, 1, 0, 0, 1, 0, 1 }, 
            { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, 
            { 0, 1, 1, 0, 1, 1, 0, 0, 0, 1 } };

    @Test
    void getUpdatedDeskStateShouldReturnCorrectDesk1() {
        deskOperator.setDesk(inputDesk1);
        deskOperator.updateDeskState();
        int[][] actualDesk1 = deskOperator.getDesk();
        for (int i = 0; i < actualDesk1.length; i++) {
            for (int j = 0; j < actualDesk1[i].length; j++) {
                assertEquals(expectedDesk1[i][j], actualDesk1[i][j]);
            }
        }
    }

    @Test
    void getUpdatedDeskStateShouldReturnCorrectDesk2() {
        deskOperator.setDesk(inputDesk2);
        deskOperator.updateDeskState();
        int[][] actualDesk2 = deskOperator.getDesk();

        for (int i = 0; i < actualDesk2.length; i++) {
            for (int j = 0; j < actualDesk2[i].length; j++) {
                assertEquals(expectedDesk2[i][j], actualDesk2[i][j]);
            }
        }
    }

    @Test
    void getUpdatedDeskStateShouldReturnCorrectDesk3() {
        deskOperator.setDesk(inputDesk3);
        deskOperator.updateDeskState();
        int[][] actualDesk3 = deskOperator.getDesk();

        for (int i = 0; i < actualDesk3.length; i++) {
            for (int j = 0; j < actualDesk3[i].length; j++) {
                assertEquals(expectedDesk3[i][j], actualDesk3[i][j]);
            }
        }
    }
}
