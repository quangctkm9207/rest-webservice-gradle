package com.emopass.rest;

import java.util.LinkedList;
import java.util.List;

public class NoteRepository {

  private List<Note> nodes;

  private static NoteRepository INSTANCE;

  private NoteRepository() {
    nodes = new LinkedList<>();
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
