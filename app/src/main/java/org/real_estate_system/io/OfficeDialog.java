package org.real_estate_system.io;


import java.util.Scanner;

import org.real_estate_system.model.Office;
import org.real_estate_system.repository.Repository;

public class OfficeDialog extends AbstractDialog<Office> {
    private final Scanner scanner;

    public OfficeDialog(Repository<Office> repository, Scanner scanner) {
        super(repository);
        this.scanner = scanner;
    }

    public OfficeDialog(Repository<Office> repository) {
        super(repository);
        this.scanner = new Scanner(System.in);
    }

    @Override
    protected String getEntityName() {
        return "Офис";
    }

    @Override
    protected String format(Office object) {
        return "Адрес: " + object.getAddress() +
               ", Площадь: " + object.getArea() +
               ", Владелец: " + object.getOfficeOwner();
    }

    @Override
    protected Office read() {
        String address = "";
        while (address.trim().isEmpty()) { 
            System.out.print("Введите адрес: ");
            address = scanner.nextLine().trim();
            if (address.trim().isEmpty()) {
                System.out.println("Адрес не может быть пустым. Повторите ввод");
            }
        }

        double area;
        while (true) {
            System.out.print("Введите площадь: ");
            try {
                area = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число с плавающей точкой");
            }
        }

        String officeOwner = "";
        while (officeOwner.trim().isEmpty()) { 
            System.out.print("Введите имя владельца офиса: ");
            officeOwner = scanner.nextLine().trim();
            if (officeOwner.trim().isEmpty()) {
                System.out.println("Имя владельца не может быть пустым. Повторите ввод");
            }
        }

        return new Office(address, area, officeOwner);
    }   
}

