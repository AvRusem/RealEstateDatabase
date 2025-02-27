package org.real_estate_system.repository.file;

import org.real_estate_system.model.House;
import org.real_estate_system.repository.AbstractFileRepository;

public class HouseRepository extends AbstractFileRepository<House> {
    
    public HouseRepository(String filename) {
        super(filename);
    }

    // оверрайды deserialize и serialize
}
