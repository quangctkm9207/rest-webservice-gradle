package com.emopass.rest;

import java.util.List;

public interface NoteDatastore {
  List<Note> getNotes();

  Note getNote(int noteId);

  int addNote(String content);

  boolean updateNote(int noteId, String content);

  boolean deleteNote(int noteId);
}
