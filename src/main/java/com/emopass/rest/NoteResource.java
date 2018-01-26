package com.emopass.rest;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("notes")
public class NoteResource {
  private static final String NOTE_LIST_HEADER = "Note list: ";
  private NoteRepository noteRepository = Injection.provideNoteRepository();
  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getNotes() {
    List<Note> notes = noteRepository.getNotes();
    StringBuilder messageBuilder = new StringBuilder(NOTE_LIST_HEADER);
    for (Note note : notes) {
      messageBuilder.append("\n");
      messageBuilder.append(note.toString());
    }
    return Response.ok(messageBuilder.toString()).build();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.TEXT_PLAIN)
  public Response addNote(String content) {
    int noteId = noteRepository.addNote(content);
    return Response.created(URI.create("/api/v1/notes/" + noteId)).build();
  }

  @GET
  @Path("/{noteId}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getNote(@PathParam("noteId") int noteId) {
    Note note = noteRepository.getNote(noteId);
    if (note != null) {
      return Response.ok(note.toString()).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @PUT
  @Path("/{noteId}")
  @Consumes(MediaType.TEXT_PLAIN)
  public Response updateNote(@PathParam("noteId") int noteId, String newContent) {
    boolean success = noteRepository.updateNote(noteId, newContent);
    if (success) {
      return Response.ok(noteRepository.getNote(noteId).toString()).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @DELETE
  @Path("/{noteId}")
  public Response deleteNote(@PathParam("noteId") int noteId) {
    boolean success = noteRepository.deleteNote(noteId);
    if (success) {
      return Response.noContent().build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }
}
