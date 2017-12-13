package com.emopass.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("notes")
public class NoteResource {
  private static final String NOTE_LIST_HEADER = "Note list: ";

  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public String getNotes() {
    List<Note> notes = NoteRepository.getInstance().getNotes();
    StringBuilder messageBuilder = new StringBuilder(NOTE_LIST_HEADER);
    for (Note note : notes) {
      messageBuilder.append("\n");
      messageBuilder.append(note.toString());
    }
    return messageBuilder.toString();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.TEXT_PLAIN)
  public String addNote(String content) {
    NoteRepository.getInstance().addNote(content);
    return getNotes();
  }
}
