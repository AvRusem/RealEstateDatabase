package org.example.repository.file;

import org.example.model.House;
import org.example.repository.AbstractFileRepository;

public class HouseRepository extends AbstractFileRepository<House> {
    
    public HouseRepository(String filename) {
        super(filename);
    }

    // оверрайды deserialize и serialize
}
