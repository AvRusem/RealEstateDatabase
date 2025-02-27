package org.real_estate_system.repository.file;

import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.repository.AbstractFileRepository;

public class FlatRoomRepository extends AbstractFileRepository<FlatRoom> {
    
    public FlatRoomRepository(String fileName) {
        super(fileName);
    } 

    // оверрайды deserialize и serialize
}
