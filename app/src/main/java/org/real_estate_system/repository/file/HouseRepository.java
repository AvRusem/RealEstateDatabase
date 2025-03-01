package org.real_estate_system.repository.file;

import org.real_estate_system.model.House;
import org.real_estate_system.repository.AbstractFileRepository;

public class HouseRepository extends AbstractFileRepository<House> {
    
    public HouseRepository(String filename) {
        super(filename);
    }

    @Override
    protected House deserialize(String content) {
        String[] splited = content.split("\0");
        if (splited.length != 4)
            throw new IllegalArgumentException("Error parsing: " + content);
        
        return new House(
            splited[1],
            Double.parseDouble(splited[0]),
            Integer.parseInt(splited[2]),
            Boolean.parseBoolean(splited[3])
        );
    }

    @Override
    protected String serialize(House object) {
        StringBuilder builder = new StringBuilder();
        builder.append(object.getArea());
        builder.append("\0");
        builder.append(object.getAddress());
        builder.append("\0");
        builder.append(object.getFloors());
        builder.append("\0");
        builder.append(object.hasGarage());
        builder.append("\0");

        return builder.toString();
    }
}
