package com.atrdev.reactive.users.infrastructure;

import org.h2.tools.Server;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
//@Profile({"dev", "test"}) // Uncomment this to enable only for 'dev' and 'test' profiles
@Profile({"!prod", "!production"})// Ensures this configuration is active for all profiles except 'prod' and 'production'
public class H2ConsoleConfiguration {

    // The port on which the H2 web console will be accessible
    private Server webServer;

    /**
     * Starts the H2 web console server when the application starts.
     * This allows accessing the database UI at http://localhost:8082
     */
    @EventListener(ApplicationStartedEvent.class)
    public void start() throws SQLException {
        String WEB_PORT = "8082";
        this.webServer = Server.createWebServer("-webPort", WEB_PORT).start();
    }

    /**
     * Stops the H2 web console server when the application context is closed.
     */
    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
    }
}