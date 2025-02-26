package org.example;

import org.example.model.FlatRoom;
import org.example.model.House;
import org.example.model.Office;
import org.example.repository.Repository;

public class ApplicationContext {
    private Repository<FlatRoom> flatRoomRepository;
    private Repository<House> houseRepository;
    private Repository<Office> officeRepository;

    public ApplicationContext() {
        // Инициализация
    }

    public Repository<FlatRoom> getFlatRoomRepository() {
        return flatRoomRepository;
    }

    public Repository<House> getHouseRepository() {
        return houseRepository;
    }

    public Repository<Office> getOfficeRepository() {
        return officeRepository;
    }

}
