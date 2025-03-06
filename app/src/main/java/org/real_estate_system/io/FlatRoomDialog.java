package org.real_estate_system.io;

import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.repository.Repository;

import java.util.Scanner;

public class FlatRoomDialog extends AbstractDialog<FlatRoom> {
    private final Scanner scanner;

    public FlatRoomDialog(Repository<FlatRoom> repository, Scanner scanner) {
        super(repository);
        this.scanner = scanner;
    }

    public FlatRoomDialog(Repository<FlatRoom> repository) {
        super(repository);
        this.scanner = new Scanner(System.in);
    }

    @Override
    protected String getEntityName() {
        return "Квартира";
    }

    @Override
    protected String format(FlatRoom object) {
        return "Адрес: " + object.getAddress() +
               ", Площадь: " + object.getArea() +
               ", Этаж: " + object.getFloor() +
               ", Номер квартиры: " + object.getFlatNumber();
    }

    @Override
    protected FlatRoom read() {
        String address = "";
        while (address.trim().isEmpty()) { 
            System.out.print("Введите адрес: ");
            address = scanner.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Адрес не может быть пустым. Повторите ввод");
            }
        }

        double area = 0;
        while (true) {
            System.out.print("Введите площадь: ");
            try {
                area = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число с плавающей точкой");
            }
        }

        int floor;
        while (true) {
            System.out.print("Введите этаж: ");
            try {
                floor = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число");
            }
        }

        int flatNumber;
        while (true) {
            System.out.print("Введите номер квартиры: ");
            try {
                flatNumber = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число");
            }
        }
   
        return new FlatRoom(address, area, floor, flatNumber);
    }
}
