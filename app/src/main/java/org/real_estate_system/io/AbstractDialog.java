package org.real_estate_system.io;


import java.util.List;
import java.util.Scanner;

import org.real_estate_system.repository.Repository;

public abstract class AbstractDialog<T> {
    private final Repository<T> repository;
    private final Scanner scanner;

    protected AbstractDialog(Repository<T> repository) {
        this.repository = repository;
        this.scanner = new Scanner(System.in);
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
        System.out.println("[1] - Show all");
        System.out.println("[2] - Create");
        System.out.println("[3] - Update");
        System.out.println("[4] - Delete");
        System.out.println("[0] - Exit");
    }

    private int readChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
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

    public void showAll() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("No entities found.");
        } else {
            for (int i = 0; i < entities.size(); i++) {
                System.out.println("[" + i + "] - " + format(entities.get(i)));
            }
        }
    }

    public void create() {
        T entity = read();
        repository.create(entity);
        System.out.println("Сохранено.");
    }

    public void update() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        showAll();
        System.out.print("Введите индекс обновляемого объекта: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < entities.size()) {
            T entity = read();
            repository.update(index, entity);
            System.out.println("Успех.");
        } else {
            System.out.println("Некорректный индекс.");
        }
    }

    public void delete() {
        List<T> entities = repository.getAll();
        if (entities.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }
        showAll();
        System.out.print("Введите индекс удаляемого объекта: ");
        int index = scanner.nextInt();
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