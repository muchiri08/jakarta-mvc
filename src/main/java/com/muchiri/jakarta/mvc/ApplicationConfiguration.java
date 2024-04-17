package com.muchiri.jakarta.mvc;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.krazo.Properties;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("mvc")
public class ApplicationConfiguration extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(Properties.DEFAULT_VIEW_FILE_EXTENSION, "xhtml");
        
        return props;
    }
    
}
