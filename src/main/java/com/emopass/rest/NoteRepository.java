package com.emopass.rest;

import java.util.LinkedList;
import java.util.List;

public class NoteRepository {
  private static final Note DEFAULT_NOTE_1 = new Note(1, "Open the door");
  private static final Note DEFAULT_NOTE_2 = new Note(2, "Close the door");

  private List<Note> nodes;

  private static NoteRepository INSTANCE;

  private NoteRepository() {
    nodes = new LinkedList<>();
    // Add default data
    nodes.add(DEFAULT_NOTE_1);
    nodes.add(DEFAULT_NOTE_2);
  }

  public static NoteRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new NoteRepository();
    }
    return INSTANCE;
  }

  public List<Note> getNotes() {
    return nodes;
  }

  public void addNote(String content) {
    Note note = new Note(getGeneratedId(), content);
    nodes.add(note);
  }

  private int getGeneratedId() {
    return nodes.size() + 1;
  }
}
