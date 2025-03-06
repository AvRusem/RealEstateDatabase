package org.real_estate_system.io;

import java.util.Scanner;

import org.real_estate_system.model.House;
import org.real_estate_system.repository.Repository;

public class HouseDialog extends AbstractDialog<House> {
    private final Scanner scanner;

    public HouseDialog(Repository<House> repository) {
        super(repository);
        this.scanner = new Scanner(System.in);
    }

    public HouseDialog(Repository<House> repository, Scanner scanner) {
        super(repository);
        this.scanner = scanner;
    }

    @Override
    protected String getEntityName() {
        return "House";
    }

    @Override
    protected String format(House object) {
        return "Адрес: " + object.getAddress() +
               ", Площадь: " + object.getArea() +
               ", Этажей: " + object.getFloors() +
               ", Гараж: " + (object.hasGarage() ? "есть" : "нет");
    }

    @Override
    protected House read() {
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

        int floors;
        while (true) {
            System.out.print("Введите количество этажей: ");
            try {
                floors = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число");
            }
        }

        Boolean hasGarage = false;
        while (true) {
            System.out.print("У дома есть гараж? (да/нет): ");
            String garageInput = scanner.nextLine().trim().toLowerCase();
            if (garageInput.equals("да")) {
                hasGarage = true;
                break;
            } else if (garageInput.equals("нет")) {
                break;
            } else {
                System.out.println("Некорректный ввод. Введите \"да\" или \"нет\".");
            }
        }

        return new House(address, area, floors, hasGarage);
    }
}
