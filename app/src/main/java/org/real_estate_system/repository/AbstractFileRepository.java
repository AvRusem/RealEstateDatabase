package org.real_estate_system.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileRepository<T> implements Repository<T> {
    private final String filePath;
    protected final String delimiter = "\0";

    public AbstractFileRepository(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                boolean isCreated = file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in creation file: " + filePath);
        }
    }

    protected abstract T deserialize(String content);

    protected abstract String serialize(T object);

    @Override
    public void create(T entity) {
        List<T> entities = getAll();
        entities.add(entity);
        saveToFile(entities);
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entities.add(deserialize(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in file reading: " + filePath);
        }
        return entities;
    }

    @Override
    public void update(int index, T entity) {
        List<T> entities = getAll();
        if (index < 0 || index >= entities.size()) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
        entities.set(index, entity);
        saveToFile(entities);
    }

    @Override
    public void delete(int index) {
        List<T> entities = getAll();
        if (index < 0 || index >= entities.size()) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
        entities.remove(index);
        saveToFile(entities);
    }

    private void saveToFile(List<T> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : entities) {
                writer.write(serialize(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in file writing: " + filePath);
        }
    }
}
