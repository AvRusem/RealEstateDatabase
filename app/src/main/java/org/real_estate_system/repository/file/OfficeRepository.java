package org.real_estate_system.repository.file;

import org.real_estate_system.model.Office;
import org.real_estate_system.repository.AbstractFileRepository;

public class OfficeRepository extends AbstractFileRepository<Office> {
    
    public OfficeRepository(String filename){
        super(filename);
    }

    @Override
    protected Office deserialize(String content) {
        String[] splited = content.split("\0");
        if (splited.length != 3)
            throw new IllegalArgumentException("Error parsing: " + content);
        
        return new Office(
            splited[1],
            Double.parseDouble(splited[0]),
            splited[2]
        );
    }

    @Override
    protected String serialize(Office object) {
        StringBuilder builder = new StringBuilder();
        builder.append(object.getArea());
        builder.append("\0");
        builder.append(object.getAddress());
        builder.append("\0");
        builder.append(object.getOfficeOwner());
        builder.append("\0");

        return builder.toString();
    }
}
