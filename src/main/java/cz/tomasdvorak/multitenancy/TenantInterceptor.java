package cz.tomasdvorak.multitenancy;

import cz.tomasdvorak.exceptions.InvalidCredentialsException;
import cz.tomasdvorak.util.TenantRegistry;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Map;

/**
 * Wrap every call with tenant identification, detected from list of parameters of called method.
 */
public class TenantInterceptor {

    @Inject
    private TenantRegistry tenantRegistry;

    @AroundInvoke
    public Object wrapWithTenant(final InvocationContext ctx) throws Exception {

        final Map<String, Object> msgContext = ctx.getContextData();
//        final String tenantName = (String)msgContext.get(BindingProvider.USERNAME_PROPERTY);
        final String tenantName = "Alice";
//        final String password = (String)msgContext.get(BindingProvider.PASSWORD_PROPERTY);
        final String password = "lorem";

//        verifyCredentials(tenantName, password);

        final String oldValue = TenantHolder.getCurrentTenant();
        try {
            TenantHolder.setTenant(tenantName);
            // executes the real webservice method. Tenant is already set.
            return ctx.proceed();
        } finally {
            if (oldValue != null) {
                TenantHolder.setTenant(oldValue);
            } else {
                TenantHolder.cleanupTenant();
            }
        }
    }

    private void verifyCredentials(final String tenantName, final String password) throws InvalidCredentialsException {
        tenantRegistry.getTenant(tenantName)
            .filter(tenant -> tenant.getPassword().equals(password))
            .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password!"));
    }

}
