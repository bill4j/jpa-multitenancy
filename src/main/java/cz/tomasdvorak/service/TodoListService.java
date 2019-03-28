package cz.tomasdvorak.service;

import cz.tomasdvorak.dto.TodoItem;
import cz.tomasdvorak.exceptions.InvalidCredentialsException;

import java.util.List;

public interface TodoListService {
    /**
     * Add an entry to currently logged tenant
     * @param content text of the entry
     */
    void insertItem(String content) throws InvalidCredentialsException;

    /**
     * Read all entries of logged-in tenant
     * @return List of all available items for this tenant
     */
    List<TodoItem> readItems() throws InvalidCredentialsException;
}
