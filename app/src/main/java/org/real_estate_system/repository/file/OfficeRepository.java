package org.real_estate_system.repository.file;

import org.real_estate_system.model.Office;
import org.real_estate_system.repository.AbstractFileRepository;

public class OfficeRepository extends AbstractFileRepository<Office> {
    
    public OfficeRepository(String filename){
        super(filename);
    }

    @Override
    protected Office deserialize(String content) {
        String[] splited = content.split(delimiter);
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
        builder.append(delimiter);
        builder.append(object.getAddress());
        builder.append(delimiter);
        builder.append(object.getOfficeOwner());
        builder.append(delimiter);

        return builder.toString();
    }
}
