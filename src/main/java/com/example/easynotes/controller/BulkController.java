package com.example.easynotes.controller;

import com.example.easynotes.dto.IndexApiResponse;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "데이터 등록/삭제", description = "테스트 데이터 등록/삭제")
public class BulkController {
    private final NoteRepository noteRepository;

    // csv 파일 bulk import data
    @PostMapping("/notes/bulk-import")
    @Operation(summary = "데이터 등록", description = "초기 테스트 데이터를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패")
    })
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
            IndexApiResponse response = IndexApiResponse.of(200, "Successfully imported " + notes.size() + " notes");
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            IndexApiResponse errorResponse = IndexApiResponse.of(400, "Error Importing Dummy Data: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/notes/bulk-delete")
    @Operation(summary = "데이터 삭제", description = "초기 테스트 데이터를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "삭제 실패")
    })
    public ResponseEntity<?> deleteDummyData() {
        try {
            noteRepository.deleteAll();
            IndexApiResponse response = IndexApiResponse.of(200, "Successfully Deleted Dummy Data");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            IndexApiResponse errorResponse = IndexApiResponse.of(400, "Error Deleting Dummy Data: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}