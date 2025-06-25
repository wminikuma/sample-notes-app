package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping("/notes")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        URI createLocation = URI.create("/api/notes/" + note.getId());
        Note createNote = noteService.createNote(note);
        return ResponseEntity.created(createLocation).body(createNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody Note newNote) {
        return ResponseEntity.ok(noteService.updateNote(id, newNote));
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.ok().build();
    }
}
