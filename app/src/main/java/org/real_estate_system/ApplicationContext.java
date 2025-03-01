package org.real_estate_system;

import org.real_estate_system.io.AbstractDialog;
import org.real_estate_system.io.FlatRoomDialog;
import org.real_estate_system.io.HouseDialog;
import org.real_estate_system.io.OfficeDialog;
import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.model.House;
import org.real_estate_system.model.Office;
import org.real_estate_system.repository.Repository;
import org.real_estate_system.repository.file.FlatRoomRepository;
import org.real_estate_system.repository.file.HouseRepository;
import org.real_estate_system.repository.file.OfficeRepository;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class ApplicationContext {
    private Repository<FlatRoom> flatRoomRepository;
    private Repository<House> houseRepository;
    private Repository<Office> officeRepository;

    private AbstractDialog<FlatRoom> flatRoomDialog;
    private AbstractDialog<House> houseDialog;
    private AbstractDialog<Office> officeDialog;

    public ApplicationContext() {
        String repoName = getRepo();
        flatRoomRepository = new FlatRoomRepository(repoName + "/flat_room");
        houseRepository = new HouseRepository(repoName + "/house");
        officeRepository = new OfficeRepository(repoName + "/office");

        flatRoomDialog = new FlatRoomDialog(flatRoomRepository);
        houseDialog = new HouseDialog(houseRepository);
        officeDialog = new OfficeDialog(officeRepository);
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

    public AbstractDialog<FlatRoom> getFlatRoomDialog() {
        return flatRoomDialog;
    }

    public AbstractDialog<House> getHouseDialog() {
        return houseDialog;
    }
    public AbstractDialog<Office> getOfficeDialog() {
        return officeDialog;
    }

    private String getRepo() {
        Dotenv dotenv = Dotenv.load();
        String repo = dotenv.get("REPOSITORY");
        if (repo == null)
            throw new MissingEnvironmentVariable("Can not find REPOSITORY variable in .env");
        
        return repo;
    }
}

class MissingEnvironmentVariable extends RuntimeException {
    public MissingEnvironmentVariable(String error) {
        super(error);
    }
}
