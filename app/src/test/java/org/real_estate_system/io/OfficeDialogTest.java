package org.real_estate_system.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.real_estate_system.model.Office;
import org.real_estate_system.repository.Repository;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OfficeDialogTest {
    @Mock
    private Repository<Office> repository;

    private OfficeDialog dialog;

    @Test
    void testGetEntityName() {
        dialog = new OfficeDialog(repository);
        
        assertEquals("Office", dialog.getEntityName());
    }

    @Test
    void testFormat() {
        dialog = new OfficeDialog(repository);
        Office office = new Office("Гашека, 7", 100, "Владимир Владимирович Путин");
        String formatted = dialog.format(office);

        assertEquals("Адрес: Гашека, 7, Площадь: 100.0, Владелец: Владимир Владимирович Путин", formatted);
    }

    @Test
    void testRead_IdealInput() {
        String input = "ул. Гашека, 7\n300\nВладимир Владимирович Путин\n";
        Scanner scanner = new Scanner(input);

        dialog = new OfficeDialog(repository, scanner);
        Office office = dialog.read();


        assertNotNull(office);
        assertEquals("ул. Гашека, 7", office.getAddress());
        assertEquals(300, office.getArea());
        assertEquals("Владимир Владимирович Путин", office.getOfficeOwner());
    }

    @Test
    void testRead_InvalidInputs() {
        String input = "ул. Гашека, 7\n\nэщкере\n300\n\n\n\nВладимир Владимирович Путин\n";
        Scanner scanner = new Scanner(input);

        dialog = new OfficeDialog(repository, scanner);
        Office office = dialog.read();


        assertNotNull(office);
        assertEquals("ул. Гашека, 7", office.getAddress());
        assertEquals(300, office.getArea());
        assertEquals("Владимир Владимирович Путин", office.getOfficeOwner());
    }

}
