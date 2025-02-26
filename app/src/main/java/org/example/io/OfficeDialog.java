package org.example.io;


import org.example.model.Office;
import org.example.repository.Repository;

public class OfficeDialog extends AbstractDialog<Office> {
    
    public OfficeDialog(Repository<Office> repository) {
        super(repository);
    }

    // TODO: методы абстракции    
}

