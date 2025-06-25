package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BulkController {
    private final NoteRepository noteRepository;

    // csv 파일 bulk import data
    @PostMapping("/notes/bulk-import")
    public ResponseEntity<?> importDummyData() {
        try {
            List<Note> notes = new ArrayList<>();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/dummy_notes.csv"))));

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Note note = new Note();
                note.setTitle(data[1]);
                note.setContent(data[2]);
                notes.add(note);
            }

            noteRepository.saveAll(notes);
            return ResponseEntity.ok().body("Successfully imported " + notes.size() + " notes");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Importing Dummy Data: " + e.getMessage());
        }
    }

    @DeleteMapping("/notes/bulk-delete")
    public ResponseEntity<?> deleteDummyData() {
        try {
            noteRepository.deleteAll();
            return ResponseEntity.ok().body("Successfully deleted all notes");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error Deleting Dummy Data: " + e.getMessage());
        }
    }
}