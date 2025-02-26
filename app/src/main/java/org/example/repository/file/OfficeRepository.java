package org.example.repository.file;

import org.example.model.Office;
import org.example.repository.AbstractFileRepository;

public class OfficeRepository extends AbstractFileRepository<Office> {
    
    public OfficeRepository(String filename){
        super(filename);
    }


    // оверрайды deserialize и serialize
}
