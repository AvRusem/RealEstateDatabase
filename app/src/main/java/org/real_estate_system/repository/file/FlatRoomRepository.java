package org.real_estate_system.repository.file;

import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.repository.AbstractFileRepository;

public class FlatRoomRepository extends AbstractFileRepository<FlatRoom> {
    
    public FlatRoomRepository(String fileName) {
        super(fileName);
    } 

    @Override
    protected FlatRoom deserialize(String content) {
        String[] splited = content.split(delimiter);
        if (splited.length != 4)
            throw new IllegalArgumentException("Error parsing: " + content);
        
        return new FlatRoom(
            splited[1],
            Double.parseDouble(splited[0]),
            Integer.parseInt(splited[2]),
            Integer.parseInt(splited[3])
        );
    }

    @Override
    protected String serialize(FlatRoom object) {
        StringBuilder builder = new StringBuilder();
        builder.append(object.getArea());
        builder.append(delimiter);
        builder.append(object.getAddress());
        builder.append(delimiter);
        builder.append(object.getFloor());
        builder.append(delimiter);
        builder.append(object.getFlatNumber());
        builder.append(delimiter);

        return builder.toString();
    }
}
