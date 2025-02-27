package org.real_estate_system.repository.file;

import org.real_estate_system.model.Office;
import org.real_estate_system.repository.AbstractFileRepository;

public class OfficeRepository extends AbstractFileRepository<Office> {
    
    public OfficeRepository(String filename){
        super(filename);
    }


    // оверрайды deserialize и serialize
}
