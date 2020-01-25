package com.shtazz.rykosyj.desk;

import java.io.FileNotFoundException;

import com.shtazz.rykosyj.desk.creator.DeskCreator;
import com.shtazz.rykosyj.desk.menu.ConsoleMenu;
import com.shtazz.rykosyj.desk.operator.DeskOperator;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        DeskCreator deskCreator = new DeskCreator();
        DeskOperator deskOperator = new DeskOperator();
        ConsoleMenu menu = new ConsoleMenu(deskCreator, deskOperator); 
        menu.printConsoleMenu();
        int inputCommandIndex = menu.recogniseCommandIndex();
        menu.executeCommand(inputCommandIndex);
    }
}
