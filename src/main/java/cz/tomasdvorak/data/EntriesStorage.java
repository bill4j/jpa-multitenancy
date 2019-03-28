package cz.tomasdvorak.data;

import cz.tomasdvorak.model.TodoEntry;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EntriesStorage {

    /**
     * Save text in a persistent storage
     * @param text text payload
     */
    void saveEntry(String text);

    /**
     * Retrieve all messages available in a persistent storage
     */
    List<TodoEntry> getAllEntries();
}
