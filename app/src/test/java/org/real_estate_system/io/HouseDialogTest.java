package org.real_estate_system.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.real_estate_system.model.House;
import org.real_estate_system.repository.Repository;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HouseDialogTest {

    @Mock
    private Repository<House> repository;

    private HouseDialog dialog;

    @Test
    void testGetEntityName() {
        dialog = new HouseDialog(repository);

        assertEquals("Дом", dialog.getEntityName());
    }
    
    @Test
    void testFormat() {
        dialog = new HouseDialog(repository);
        House house = new House("Гашека, 7", 300, 3, true);
        String formatted = dialog.format(house);

        assertEquals("Адрес: Гашека, 7, Площадь: 300.0, Этажей: 3, Гараж: есть", formatted);
    }

    @Test
    void testRead_IdealInput() {
        String input = "ул. Гашека, 7\n300\n3\nда\n\n";
        Scanner scanner = new Scanner(input);

        dialog = new HouseDialog(repository, scanner);
        House house = dialog.read();


        assertNotNull(house);
        assertEquals("ул. Гашека, 7", house.getAddress());
        assertEquals(300, house.getArea());
        assertEquals(3, house.getFloors());
        assertEquals(true, house.hasGarage());
    }

    @Test
    void testRead_InvalidInputs() {
        String input = "\nул. Гашека, 7\n\nэщкере\n300\nхахахывахыа\n3\nд\nа\nнет\n\n";
        Scanner scanner = new Scanner(input);

        dialog = new HouseDialog(repository, scanner);
        House house = dialog.read();


        assertNotNull(house);
        assertEquals("ул. Гашека, 7", house.getAddress());
        assertEquals(300, house.getArea());
        assertEquals(3, house.getFloors());
        assertEquals(false, house.hasGarage());
    }
}