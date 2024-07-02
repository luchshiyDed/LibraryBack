package org.example.util;


import com.google.inject.Guice;
import com.google.inject.Injector;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ApplicationPath;
import org.example.controller.BookController;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationPath("/")
public class App extends ResourceConfig {
    public App() {
        packages(BookController.class.getPackage().getName());
        register(JacksonFeature.class);
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

    }

}