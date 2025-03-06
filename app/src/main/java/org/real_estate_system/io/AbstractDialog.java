package org.real_estate_system.io;


import java.util.List;
import java.util.Scanner;

import org.real_estate_system.model.Entity;
import org.real_estate_system.repository.Repository;

public abstract class AbstractDialog<T extends Entity> {
    private final Repository<T> repository;
    protected Scanner scanner;

    protected AbstractDialog(Repository<T> repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = readChoice();
            if (choice == 0) {
                break;
            }
            handleChoice(choice);
        }
    }

    private void printMenu() {
        System.out.println("[" + getEntityName() + "]");
        System.out.println("[1] - Показать все");
        System.out.println("[2] - Создать");
        System.out.println("[3] - Обновить");
        System.out.println("[4] - Удалить");
        System.out.println("[0] - Выход");
    }

    public int readChoice() {
        while (true) {
            System.out.print("Выберите пункт меню: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор. Введите число от 0 до 4.");
            }
        }
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                showAll();
                break;
            case 2:
                create();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            default:
                System.out.println("Некорректный выбор. Повторите попытку");
        }
    }

    private void showAll() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            for (int i = 0; i < entities.size(); i++) {
                System.out.println("[" + i + "] - " + format(entities.get(i)));
            }
        }
    }

    private void create() {
        T entity = read();
        repository.create(entity);
        System.out.println("Сохранено.");
    }

    private void update() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        showAll(); 
        int index;
        while (true) {
            System.out.print("Введите индекс обновляемого объекта: ");
            String input = scanner.nextLine();
            try {
                index = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор.");
            }
        }
        if (index >= 0 && index < entities.size()) {
            T entity = read();
            repository.update(index, entity);
            System.out.println("Успех.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }

    private void delete() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        showAll();
        int index;
        while (true) {
            System.out.print("Введите индекс удаляемого объекта: ");
            String input = scanner.nextLine();
            try {
                index = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор.");
            }
        }
        if (index >= 0 && index < entities.size()) {
            repository.delete(index);
            System.out.println("Успех.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }

    // Абстрактные методы, которые должны быть реализованы в наследниках
    protected abstract String getEntityName();
    protected abstract String format(T object);
    protected abstract T read();
}