package com.IMT2023075.todo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class TodoList {
    private final List<TodoItem> items = new ArrayList<>();
    private int nextId = 1;


    public synchronized TodoItem add(String text) {
        TodoItem item = new TodoItem(nextId++, text);
        items.add(item);
        return item;
    }


    public synchronized boolean remove(int id) {
        Optional<TodoItem> found = items.stream().filter(i -> i.getId() == id).findFirst();
        if (found.isPresent()) {
            items.remove(found.get());
            return true;
        }
        return false;
    }


    public synchronized List<TodoItem> list() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }


    public synchronized void clear() {
        items.clear();
        nextId = 1;
    }
}
