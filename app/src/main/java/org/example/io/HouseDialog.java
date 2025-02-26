package org.example.io;

import org.example.model.House;
import org.example.repository.Repository;

public class HouseDialog extends AbstractDialog<House> {

    public HouseDialog(Repository<House> repository) {
        super(repository);
    }

    // TODO: методы абстракции  
}
