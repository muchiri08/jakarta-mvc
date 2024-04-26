package com.muchiri.jakarta.mvc;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.krazo.Properties;

/**
 * Configures Jakarta RESTful Web Services for the application.
 *
 * @author muchiri
 */
@DatabaseIdentityStoreDefinition(
        callerQuery = "SELECT password FROM basic_auth_user WHERE username = ?",
        groupsQuery = "SELECT name FROM basic_auth_group WHERE username = ?",
        dataSourceLookup = "jdbc/kennedy_resource"
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/mvc/login",
                errorPage = "",
                useForwardToLogin = false
        )
)
@DeclareRoles({"user", "caller"})
@ApplicationScoped
@ApplicationPath("mvc")
public class ApplicationConfiguration extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(Properties.DEFAULT_VIEW_FILE_EXTENSION, "xhtml");
        props.put(Properties.REDIRECT_SCOPE_COOKIES, true);

        return props;
    }

}
