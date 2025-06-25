package com.example.easynotes.service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    // find all
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // find by id
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }

    // save
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // update
    public Note updateNote(Long id, Note note) {
        Note findNote = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        findNote.setTitle(note.getTitle());
        findNote.setContent(note.getContent());

        return noteRepository.save(findNote);
    }

    // delete by id
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
