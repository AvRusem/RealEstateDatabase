package org.real_estate_system;

import java.util.Scanner;

public class Application {
    private static ApplicationContext context;
    private static Scanner scanner;

    public static void main(String[] args) {
        context = new ApplicationContext();
        scanner = new Scanner(System.in);

        run();
    }

    private static void run() {
        while (true) {
            printMenu();
            int choice = readChoice();
            if (choice == 0) {
                break;
            }
            handleChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("[1] - Houses");
        System.out.println("[2] - FlatRooms");
        System.out.println("[3] - Offices");
        System.out.println("[0] - Exit");
    }

    private static int readChoice() {
        while (true) {
            System.out.print("Выберите пункт меню: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор. Введите число от 0 до 3.");
            }
        }
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                context.getHouseDialog().run();
                break;
            case 2:
                context.getFlatRoomDialog().run();
                break;
            case 3:
                context.getOfficeDialog().run();
                break;
            default:
                System.out.println("Некорректный выбор. Повторите попытку");
        }
    }
}
