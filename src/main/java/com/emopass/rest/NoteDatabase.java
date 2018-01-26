package com.emopass.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A note database which connects and persists data to PostgreSQL database.
 */
public class NoteDatabase implements NoteDatastore {
  private static final String DB_NAME = "notes";
  private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
  private static final String DB_USERNAME = "user";
  private static final String DB_PASSWORD = "123456";
  private static final String TABLE_NAME = "notes";
  private static final String ID_KEY = "id";
  private static final String CONTENT_KEY = "content";
  private Connection db = null;

  public NoteDatabase() {

  }

  public void connect() {
    checkPostgreDriver();

    try {
      db = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (db == null) {
      System.out.println("Failed to make connection!");
    }
  }

  public void close() {
    if (db == null) {
      return;
    }
    try {
      if (!db.isClosed()) {
        db.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createTableIfNotExists() {
    try {
      Statement createTableStatement = db.createStatement();
      createTableStatement.execute(
          "CREATE TABLE IF NOT EXISTS "
              + TABLE_NAME
              + " ("
              + ID_KEY
              + " SERIAL PRIMARY KEY, "
              + CONTENT_KEY
              + " TEXT NOT NULL)");
      createTableStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Note> getNotes() {
    List<Note> notes = new ArrayList<>();
    try {
      Statement query = db.createStatement();
      ResultSet resultSet =
          query.executeQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID_KEY);
      while (resultSet.next()) {
        int id = resultSet.getInt(ID_KEY);
        String content = resultSet.getString(CONTENT_KEY);
        Note note = new Note(id, content);
        notes.add(note);
      }
      query.close();
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return notes;
  }

  @Override
  public int addNote(String content) {
    String insertSql =
        "INSERT INTO " + TABLE_NAME + "(" + CONTENT_KEY + ") VALUES ('" + content + "')";
    try {
      PreparedStatement statement = db.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("Creating note failed, no rows affected.");
      }

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int row = generatedKeys.getInt(1);
          statement.close();
          return row;
        } else {
          throw new SQLException("Creating note failed, no ID obtained.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public boolean deleteNote(int id) {
    try {
      Statement statement = db.createStatement();
      statement.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + ID_KEY + " = " + id);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Note getNote(int id) {
    Note note = null;
    try {
      Statement query = db.createStatement();
      ResultSet resultSet =
          query.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_KEY + " = " + id);
      while (resultSet.next()) {
        String content = resultSet.getString(CONTENT_KEY);
        note = new Note(id, content);
      }
      query.close();
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return note;
  }

  @Override
  public boolean updateNote(int id, String content) {
    try {
      Statement statement = db.createStatement();
      statement.executeUpdate("UPDATE "
          + TABLE_NAME
          + " SET "
          + CONTENT_KEY
          + " = '"
          + content
          + "' WHERE "
          + ID_KEY
          + " = "
          + id);
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Checks PostgreSQL driver.
   *
   * @throws
   */
  public void checkPostgreDriver() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Could not find PostgreSQL JDBC Driver? Please include in library path.");
      e.printStackTrace();
    }
  }
}
