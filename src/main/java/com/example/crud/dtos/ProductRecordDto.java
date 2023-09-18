package com.example.crud.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(UUID id, @NotBlank String name, @NotNull BigDecimal price) {

	
}
