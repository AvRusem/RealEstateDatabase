package org.real_estate_system.io;


import org.real_estate_system.model.Office;
import org.real_estate_system.repository.Repository;

public class OfficeDialog extends AbstractDialog<Office> {
    
    public OfficeDialog(Repository<Office> repository) {
        super(repository);
    }

    // TODO: методы абстракции    
}

