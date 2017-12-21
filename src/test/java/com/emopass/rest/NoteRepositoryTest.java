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
    initializeNoteList();

    assertThat(noteRepository.getNotes().size(), equalTo(3));
    assertThat(noteRepository.getNotes(), hasItem(new Note(1, NOTE1)));
    assertThat(noteRepository.getNotes(), hasItem(new Note(2, NOTE2)));
    assertThat(noteRepository.getNotes(), hasItem(new Note(3, NOTE3)));
  }

  @Test
  public void shouldReturnSpecificNoteWhenQueryingByItsCorrectId() {
    initializeNoteList();

    assertThat(noteRepository.getNote(1), equalTo(new Note(1, NOTE1)));
    assertThat(noteRepository.getNote(3), equalTo(new Note(3, NOTE3)));
  }

  @Test
  public void shouldReturnNullWhenQueryInvalidNoteId() {
    initializeNoteList();

    assertThat(noteRepository.getNote(4), nullValue());
    assertThat(noteRepository.getNote(0), nullValue());
  }

  @Test
  public void shouldReturnTrueAndUpdateNoteContentWhenIdIsValid() {
    initializeNoteList();
    String NEW_NOTE_CONTENT = "Turn the light off.";
    int VALID_ID = 1;
    assertThat(noteRepository.getNote(VALID_ID).getContent(), equalTo(NOTE1));

    boolean success = noteRepository.updateNote(VALID_ID, NEW_NOTE_CONTENT);

    assertThat(success, is(true));
    assertThat(noteRepository.getNote(VALID_ID).getContent(), equalTo(NEW_NOTE_CONTENT));
  }

  @Test
  public void shouldReturnFalseWhenRemovingNoteWithInvalid() {
    initializeNoteList();
    String NEW_NOTE_CONTENT = "Turn the light off.";
    int INVALID_ID = 5;

    boolean success = noteRepository.updateNote(INVALID_ID, NEW_NOTE_CONTENT);

    assertThat(success, is(false));
  }

  @Test
  public void shouldRemoveNoteFromListWhenIdIsValid() {
    initializeNoteList();
    int VALID_ID = 1;
    assertThat(noteRepository.getNotes(), hasItem(new Note(VALID_ID, NOTE1)));

    boolean success = noteRepository.deleteNote(VALID_ID);

    assertThat(success, is(true));
    assertThat(noteRepository.getNotes(), not(hasItem(new Note(VALID_ID, NOTE1))));
  }

  @Test
  public void shouldReturnFalseWhenRemovingNoteWithInvalidId() {
    initializeNoteList();
    int INVALID_ID = 5;

    boolean success = noteRepository.deleteNote(INVALID_ID);

    assertThat(success, is(false));
  }

  private void initializeNoteList() {
    noteRepository.addNote(NOTE1);
    noteRepository.addNote(NOTE2);
    noteRepository.addNote(NOTE3);
  }
}