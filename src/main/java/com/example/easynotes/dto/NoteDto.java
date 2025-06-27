package com.example.easynotes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
