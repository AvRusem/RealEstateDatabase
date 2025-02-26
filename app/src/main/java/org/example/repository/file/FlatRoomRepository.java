package org.example.repository.file;

import org.example.model.FlatRoom;
import org.example.repository.AbstractFileRepository;

public class FlatRoomRepository extends AbstractFileRepository<FlatRoom> {
    
    public FlatRoomRepository(String fileName) {
        super(fileName);
    } 

    // оверрайды deserialize и serialize
}
