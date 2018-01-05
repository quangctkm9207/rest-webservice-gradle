package com.emopass.rest;

import java.util.List;

public class NoteRepository implements NoteDatastore{
  private NoteDatabase database;
  private static NoteRepository INSTANCE;

  private NoteRepository() {
    this.database = NoteDatabase.getInstance();
    this.database.connect();
    this.database.createTableIfNotExists();
  }

  public static NoteRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new NoteRepository();
    }
    return INSTANCE;
  }

  @Override
  public List<Note> getNotes() {
    return database.getNotes();
  }

  @Override
  public int addNote(String content) {
    return database.addNote(content);
  }

  @Override
  public Note getNote(int noteId) {
    return database.getNote(noteId);
  }

  @Override
  public boolean updateNote(int noteId, String newContent) {
    return database.updateNote(noteId, newContent);
  }

  @Override
  public boolean deleteNote(int noteId) {
    return database.deleteNote(noteId);
  }
}
