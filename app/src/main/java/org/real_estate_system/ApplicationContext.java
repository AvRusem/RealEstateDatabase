package org.real_estate_system;

import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.model.House;
import org.real_estate_system.model.Office;
import org.real_estate_system.repository.Repository;

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
