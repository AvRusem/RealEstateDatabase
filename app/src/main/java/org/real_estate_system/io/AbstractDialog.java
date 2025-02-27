package org.real_estate_system.io;

import org.real_estate_system.repository.Repository;

public abstract class AbstractDialog<T> {
    private final Repository<T> repository;

    protected AbstractDialog(Repository<T> repository) {
        this.repository = repository;
    }

    public void run() {
        // Логика взаимодействия с пользователем
    }

    protected abstract String getEntityName();
    protected abstract String format(T object);
    protected abstract T read();

}
