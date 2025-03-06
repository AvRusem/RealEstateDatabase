package org.real_estate_system.io;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.real_estate_system.model.FlatRoom;
import org.real_estate_system.repository.Repository;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FlatRoomDialogTest {

    @Mock
    private Repository<FlatRoom> repository;

    @InjectMocks
    private FlatRoomDialog dialog;

    @Test
    void testGetEntityName() {
        assertEquals("Квартира", dialog.getEntityName());
    }

    @Test
    void testFormat() {
        FlatRoom flatRoom = new FlatRoom("Gasheka, 7", 150, 12, 3);
        String formatted = dialog.format(flatRoom);

        assertEquals("Адрес: Gasheka, 7, Площадь: 150.0, Этаж: 12, Номер квартиры: 3", formatted);
    }

    @Test
    void testRead_IdealInput() {
        String input = "ул. Ленина, 10\n50.5\n3\n15\n"; // Адрес, площадь, этаж, номер квартиры
        Scanner scanner = new Scanner(input);

        dialog = new FlatRoomDialog(repository, scanner);
        // Вызов метода read
        FlatRoom flatRoom = dialog.read();


        // Проверка результата
        assertNotNull(flatRoom);
        assertEquals("ул. Ленина, 10", flatRoom.getAddress());
        assertEquals(50.5, flatRoom.getArea());
        assertEquals(3, flatRoom.getFloor());
        assertEquals(15, flatRoom.getFlatNumber());
    }

    @Test
    void testRead_InvalidInputs() {
        String input = "\nул. Гашека, 7\n\nэщкере\n300\nхахахывахыа\n\nд\nа\n3\n321\n";
        Scanner scanner = new Scanner(input);

        dialog = new FlatRoomDialog(repository, scanner);
        FlatRoom flatRoom = dialog.read();


        assertNotNull(flatRoom);
        assertEquals("ул. Гашека, 7", flatRoom.getAddress());
        assertEquals(300, flatRoom.getArea());
        assertEquals(3, flatRoom.getFloor());
        assertEquals(321, flatRoom.getFlatNumber());
    }
}