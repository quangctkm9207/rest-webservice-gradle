package com.emopass.rest;

/**
 * A simple dependence injector which creates and provides app's objects.
 */
public class Injection {
  private static NoteDatabase noteDatabase;
  private static NoteRepository noteRepository;

  /**
   * Returns a singleton NoteDatabase.
   */
  public static NoteDatabase provideNoteDatabase() {
    if (noteDatabase == null) {
      noteDatabase = new NoteDatabase();
    }
    return noteDatabase;
  }

  /**
   * Returns a singleton NoteRepository.
   */
  public static NoteRepository provideNoteRepository() {
    if (noteRepository == null) {
      noteRepository = new NoteRepository(provideNoteDatabase());
    }
    return noteRepository;
  }
}
