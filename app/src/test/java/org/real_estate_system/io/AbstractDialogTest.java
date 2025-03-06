package org.real_estate_system.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.real_estate_system.model.Entity;
import org.real_estate_system.repository.Repository;

import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class AbstractDialogTest {

    @Mock
    private Repository<Entity> repository;

    private AbstractDialog<Entity> dialog;

    private Entity testEntity1;
    private Entity testEntity2;
    private Entity newEntity;

    @BeforeEach
    void setUp() {

        testEntity1 = new TestEntity("Entity1", 100.0);
        testEntity2 = new TestEntity("Entity2", 100.0);
        newEntity = new TestEntity("NewEntity", 100.0);

        dialog = new AbstractDialog<>(repository) {
            @Override
            protected String getEntityName() {
                return "TestEntity";
            }

            @Override
            protected String format(Entity object) {
                return "Formatted: " + object.toString();
            }

            @Override
            protected Entity read() {
                return newEntity;
            }
        };
    }

    @Test
    void testShowAll() {
        when(repository.getAll()).thenReturn(List.of(testEntity1, testEntity2));

        dialog.handleChoice(1);

        verify(repository, times(1)).getAll();
    }

    @Test
    void testCreate() {
        dialog.handleChoice(2);

        verify(repository, times(1)).create(newEntity);
    }

    @Test
    void testUpdate() throws Exception {
        when(repository.getAll()).thenReturn(List.of(testEntity1, testEntity2));

        String input = "0\n"; 
        Scanner scanner = new Scanner(input);
        dialog.setScanner(scanner);
        
        dialog.handleChoice(3);

        verify(repository, times(1)).update(0, newEntity);
    }

    @Test
    void testDelete() throws Exception {
        when(repository.getAll()).thenReturn(List.of(
            new TestEntity("Entity1", 100),
            new TestEntity("Entity2", 100))
            );

        String input = "1\n";
        Scanner scanner = new Scanner(input);
        dialog.setScanner(scanner);

        dialog.handleChoice(4);

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

