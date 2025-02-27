package org.real_estate_system.repository;

public abstract class AbstractFileRepository<T> implements Repository<T> {
    private final String fileName;

    protected AbstractFileRepository(String fileName) {
        this.fileName = fileName;
    }

    protected abstract T deserialize(String content);
    protected abstract String serialize(T object);

    // Реализация методов интерфейса Repository
}
