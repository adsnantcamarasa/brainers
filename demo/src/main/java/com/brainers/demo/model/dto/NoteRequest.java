package com.brainers.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteRequest(
        @NotBlank
        @Size(max = 100)
        String title,
        String content
        ){}