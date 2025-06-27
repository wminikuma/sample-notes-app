package com.example.easynotes.service;

import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.exception.ErrorCode;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import jakarta.transaction.Transactional;
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
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.INTERNAL_SERVER_ERROR, "Note not found"));
    }

    // save
    @Transactional
    public Note createNote(NoteDto note) {
        Note noteEntity = new Note();
        noteEntity.setTitle(note.getTitle());
        noteEntity.setContent(note.getContent());
        return noteRepository.save(noteEntity);
    }

    // update
    @Transactional
    public Note updateNote(Long id, Note note) {
        Note findNote = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.INTERNAL_SERVER_ERROR, "Note not found"));

        findNote.setTitle(note.getTitle());
        findNote.setContent(note.getContent());

        return noteRepository.save(findNote);
    }

    // delete by id
    @Transactional
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
