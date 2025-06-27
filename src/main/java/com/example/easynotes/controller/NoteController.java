package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteApiResponse;
import com.example.easynotes.model.Note;
import com.example.easynotes.service.NoteService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Note API", description = "Note API (GET/POST/PUT/DELETE)")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<?> findAll() {
        List<Note> findNotes = noteService.getAllNotes();
        NoteApiResponse response = NoteApiResponse.of(200, findNotes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<?> getNoteById(
            @Parameter(description = "노트 ID", required = true, example = "1")
            @PathVariable(value = "id") final Long id) {
        List<Note> result = new ArrayList<>();
        Note findNote = noteService.getNoteById(id);
        result.add(findNote);
        NoteApiResponse response = NoteApiResponse.of(200, result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/notes")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        URI createLocation = URI.create("/api/notes/" + note.getId());
        Note createNote = noteService.createNote(note);
        List<Note> result = new ArrayList<>();
        result.add(createNote);
        NoteApiResponse response = NoteApiResponse.of(200, result);
        return ResponseEntity.created(createLocation).body(response);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "id") final Long id, @Valid @RequestBody Note newNote) {
        Note updateNote = noteService.updateNote(id, newNote);
        List<Note> result = new ArrayList<>();
        result.add(updateNote);
        NoteApiResponse response = NoteApiResponse.of(200, result);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        noteService.deleteNoteById(id);
        NoteApiResponse response = NoteApiResponse.of(200, Collections.emptyList());
        return ResponseEntity.ok(response);
    }
}
