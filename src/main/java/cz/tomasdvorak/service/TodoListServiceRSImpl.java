package cz.tomasdvorak.service;

import cz.tomasdvorak.data.EntriesStorage;
import cz.tomasdvorak.dto.TodoItem;
import cz.tomasdvorak.exceptions.InvalidCredentialsException;
import cz.tomasdvorak.multitenancy.TenantInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Interceptors(TenantInterceptor.class)
public class TodoListServiceRSImpl implements TodoListService {

    /**
     * Demonstrates that Tenant information is propagated to other EJBs too. The Entries service is not wrapped nor
     * anyhow uses the tenant information.
     */
    @Inject
    private EntriesStorage storage;

    @Override
    public void insertItem(final String todoItem) throws InvalidCredentialsException {
        // storage is already tenant-aware thanks to Interceptor configured on this WS
        storage.saveEntry(todoItem);
    }

    @Override
    public List<TodoItem> readItems() throws InvalidCredentialsException {
        // storage is already tenant-aware thanks to Interceptor configured on this WS
        return storage
            .getAllEntries()
            .stream()
            .map(entry -> new TodoItem(entry.getText(), entry.getCreated())) // do not expose our LogEntry model, convert them to Message objects!
            .collect(Collectors.toList());
    }

}
