package com.IMT2023075.todo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;


public class TodoListTest {
    private TodoList todo;


    @BeforeEach
    void setUp() {
        todo = new TodoList();
    }


    @AfterEach
    void tearDown() {
        todo.clear();
    }


    @Test
    void testAddAndList() {
        TodoItem i1 = todo.add("Buy milk");
        TodoItem i2 = todo.add("Call mom");


        List<TodoItem> all = todo.list();
        Assertions.assertEquals(2, all.size());
        Assertions.assertEquals("Buy milk", all.get(0).getText());
        Assertions.assertEquals("Call mom", all.get(1).getText());
    }


    @Test
    void testRemove() {
        TodoItem i1 = todo.add("Task1");
        boolean removed = todo.remove(i1.getId());
        Assertions.assertTrue(removed);
        Assertions.assertTrue(todo.list().isEmpty());
    }


    @Test
    void testRemoveNotFound() {
        boolean removed = todo.remove(999);
        Assertions.assertFalse(removed);
    }
}
