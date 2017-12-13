package com.emopass.rest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteRepositoryTest {

  private NoteRepository noteRepository;

  @Before
  public void setup() {
    noteRepository = NoteRepository.getInstance();
  }

  @Test
  public void addNote() {
    final String newNoteContent = "Buy two eggs.";
    final int beforeNoteCount = noteRepository.getNotes().size();

    noteRepository.addNote(newNoteContent);
    final int afterNoteCount = noteRepository.getNotes().size();

    assertEquals(afterNoteCount, beforeNoteCount + 1);
    assertEquals(noteRepository.getNotes().get(afterNoteCount - 1).getContent(), newNoteContent);
  }
}