package com.emopass.rest;

import java.util.LinkedList;
import java.util.List;

public class NoteRepository {

  private List<Note> notes;

  private static NoteRepository INSTANCE;

  private NoteRepository() {
    notes = new LinkedList<>();
  }

  public static NoteRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new NoteRepository();
    }
    return INSTANCE;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public int addNote(String content) {
    int noteId = getGeneratedId();
    Note note = new Note(noteId, content);
    notes.add(note);
    return noteId;
  }

  public Note getNote(int noteId) {
    for (Note note : notes) {
      if (noteId == note.getId()) {
        return note;
      }
    }
    return null;
  }

  public boolean updateNote(int noteId, String newContent) {
    Note note = getNote(noteId);
    if (note != null) {
      note.setContent(newContent);
      return true;
    } else {
      return false;
    }
  }

  public boolean deleteNote(int noteId) {
    Note note = getNote(noteId);
    if (note != null) {
      notes.remove(note);
      return true;
    } else {
      return false;
    }
  }

  private int getGeneratedId() {
    return notes.size() + 1;
  }
}
