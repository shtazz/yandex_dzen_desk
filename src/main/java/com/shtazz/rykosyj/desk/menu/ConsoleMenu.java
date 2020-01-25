package com.shtazz.rykosyj.desk.menu;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.shtazz.rykosyj.desk.creator.DeskCreator;
import com.shtazz.rykosyj.desk.operator.DeskOperator;

public class ConsoleMenu {

    private static final String MANUAL_SETTING = "Manual size setting and random filling in.";
    private static final String READ_FROM_FILE = "Manual size setting and get state from file.";

    private Map<String, Runnable> commands = new LinkedHashMap<>();
    private List<String> commandsNames = new LinkedList<>();
    private DeskCreator deskCreator;
    private DeskOperator deskOperator;

    public ConsoleMenu(DeskCreator deskCreator, DeskOperator deskOperator) {
        commands.put(MANUAL_SETTING, manualSetting());
        commands.put(READ_FROM_FILE, readFromFile());
        commands.forEach((name, command) -> commandsNames.add(name));
        this.deskCreator = deskCreator;
        this.deskOperator = deskOperator;
    }

    public void printConsoleMenu() {
        AtomicInteger commandsCount = new AtomicInteger(0);
        System.out.println("Please choose one of the next commands for execution or type 0 for exit:");
        commandsNames.forEach(name -> System.out.printf("%d. %s%n", commandsCount.incrementAndGet(), name));
    }

    public int recogniseCommandIndex() {
        int inputCommandIndex = 0;
        try {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            inputCommandIndex = scanner.nextInt();
            if (inputCommandIndex < 0 || inputCommandIndex > commandsNames.size()) {
                throw new IllegalArgumentException();
            }
        } catch (InputMismatchException ime) {
            System.out.println("Token does not match the Integer regular expression, or is out of range");
        } catch (NoSuchElementException ime) {
            System.out.println("Input is empty");
        } catch (IllegalArgumentException nsee) {
            System.out.println("You have entered incorrect command index. Please try again");
        }
        return inputCommandIndex;
    }

    public void executeCommand(int inputCommandIndex) {
        if (inputCommandIndex == 0) {
            System.out.println("Exited application");
            System.exit(0);
        }
        commands.get(commandsNames.get(inputCommandIndex - 1))
                .run();
    }

    private Runnable readFromFile() {
        return () -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter desk hight.");
            int hight = scanner.nextInt();
            System.out.println("Please enter desk width.");
            int width = scanner.nextInt();
            System.out.println("Please enter file name.");
            String fileName = scanner.next();
            scanner.close();
            int[][] desk = deskCreator.getDeskFromFile(width, hight, fileName);
            deskOperator.setDesk(desk);
            deskOperator.printDesk();
            Thread deskOperatorThread = new Thread(deskOperator);
            deskOperatorThread.start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deskOperatorThread.interrupt();
        };
    }

    private Runnable manualSetting() {
        return () -> {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter desk hight.");
            int hight = scanner.nextInt();
            System.out.println("Please enter desk width.");
            int width = scanner.nextInt();
            int[][] desk = deskCreator.createDesk(width, hight);
            deskOperator.setDesk(desk);
            deskOperator.printDesk();
            Thread deskOperatorThread = new Thread(deskOperator);
            deskOperatorThread.start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deskOperatorThread.interrupt();
        };
    }

}
