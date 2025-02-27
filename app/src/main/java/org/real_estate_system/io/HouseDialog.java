package org.real_estate_system.io;

import org.real_estate_system.model.House;
import org.real_estate_system.repository.Repository;

public class HouseDialog extends AbstractDialog<House> {

    public HouseDialog(Repository<House> repository) {
        super(repository);
    }

    // TODO: методы абстракции  
}
