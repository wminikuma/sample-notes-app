package com.example.easynotes.dto;

import com.example.easynotes.model.Note;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Note API 응답
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteApiResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private List<Note> message;

    public static NoteApiResponse of(int status, List<Note> message){
        return NoteApiResponse.builder()
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
