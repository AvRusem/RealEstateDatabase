package org.real_estate_system.repository.file;

import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.repository.AbstractFileRepository;

public class FlatRoomRepository extends AbstractFileRepository<FlatRoom> {
    
    public FlatRoomRepository(String fileName) {
        super(fileName);
    } 

    @Override
    protected FlatRoom deserialize(String content) {
        String[] splited = content.split("\0");
        if (splited.length != 4)
            throw new IllegalArgumentException("Error parsing: " + content);
        
        return new FlatRoom(
            splited[0],
            Double.parseDouble(splited[1]),
            Integer.parseInt(splited[2]),
            Integer.parseInt(splited[3])
        );
    }

    @Override
    protected String serialize(FlatRoom object) {
        StringBuilder builder = new StringBuilder();
        builder.append(object.getArea());
        builder.append("\0");
        builder.append(object.getAddress());
        builder.append("\0");
        builder.append(object.getFloor());
        builder.append("\0");
        builder.append(object.getFlatNumber());
        builder.append("\0");

        return builder.toString();
    }
}
