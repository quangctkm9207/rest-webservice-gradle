package com.emopass.rest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class NoteRepositoryTest {
  private static final String NOTE1 = "Open the door";
  private static final String NOTE2 = "Close the door";
  private static final String NOTE3 = "Go for a walk";

  private NoteRepository noteRepository;

  @Before
  public void setup() {
    // TODO: Currently, we use in-memory repository, if we use real database, we need a mock database for testing
    noteRepository = NoteRepository.getInstance();
    noteRepository.getNotes().clear();
  }

  @Test
  public void noteListShouldBeEmptyAtFirst() {
    assertThat(noteRepository.getNotes().size(), equalTo(0));
  }

  @Test
  public void noteShouldBeAddSuccessfully() {
    noteRepository.addNote(NOTE1);

    assertThat(noteRepository.getNotes().size(), equalTo(1));
    assertThat(noteRepository.getNotes(), hasItem(new Note(1, NOTE1)));
  }

  @Test
  public void shouldReturnAllNotesWhenGetNotes() {
    noteRepository.addNote(NOTE1);
    noteRepository.addNote(NOTE2);
    noteRepository.addNote(NOTE3);

    assertThat(noteRepository.getNotes().size(), equalTo(3));
    assertThat(noteRepository.getNotes(), hasItem(new Note(1, NOTE1)));
    assertThat(noteRepository.getNotes(), hasItem(new Note(2, NOTE2)));
    assertThat(noteRepository.getNotes(), hasItem(new Note(3, NOTE3)));
  }
}