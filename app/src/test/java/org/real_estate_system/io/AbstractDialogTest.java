package org.real_estate_system.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.real_estate_system.repository.Repository;

import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AbstractDialogTest {

    @Mock
    private Repository<String> repository;

    private AbstractDialog<String> dialog;

    @BeforeEach
    void setUp() {
        dialog = new AbstractDialog<>(repository) {
            @Override
            protected String getEntityName() {
                return "TestEntity";
            }

            @Override
            protected String format(String object) {
                return "Formatted: " + object;
            }

            @Override
            protected String read() {
                return "NewEntity";
            }
        };
    }

    @Test
    void testShowAll() {
        when(repository.getAll()).thenReturn(List.of("Entity1", "Entity2"));

        dialog.showAll();

        verify(repository, times(1)).getAll();
    }

    @Test
    void testCreate() {
        dialog.create();

        verify(repository, times(1)).create("NewEntity");
    }

    @Test
    void testUpdate() throws Exception {
        when(repository.getAll()).thenReturn(List.of("Entity1", "Entity2"));

        String input = "0\n"; 
        Scanner scanner = new Scanner(input);
        dialog.setScanner(scanner);
        
        dialog.update();

        verify(repository, times(1)).update(0, "NewEntity");
    }

    @Test
    void testDelete() throws Exception {
        when(repository.getAll()).thenReturn(List.of("Entity1", "Entity2"));

        String input = "1\n";
        Scanner scanner = new Scanner(input);
        dialog.setScanner(scanner);

        dialog.delete();

        verify(repository, times(1)).delete(1);
    }

    @Test
    void testReadChoice() throws Exception {
        String input = "2\n";
        Scanner scanner = new Scanner(input);
        dialog.setScanner(scanner);
        
        int choice = dialog.readChoice();

        assertEquals(2, choice);
    }

    @Test
    void testReadChoice_InvalidInput() throws Exception {
        String input = "abc\n2\n";
        Scanner scanner = new Scanner(input);

        dialog.setScanner(scanner);
        int choice = dialog.readChoice();

        assertEquals(2, choice);
    }

}