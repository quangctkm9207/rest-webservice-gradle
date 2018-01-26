package com.emopass.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listens to servlet callbacks
 */
public class CustomServletContextListener implements ServletContextListener {

  @Override public void contextInitialized(ServletContextEvent sce) {
    // Connect to database and create table for the first time
    Injection.provideNoteDatabase().connect();
    Injection.provideNoteDatabase().createTableIfNotExists();
  }

  @Override public void contextDestroyed(ServletContextEvent sce) {
    // Disconnect database
    Injection.provideNoteDatabase().close();
  }
}
