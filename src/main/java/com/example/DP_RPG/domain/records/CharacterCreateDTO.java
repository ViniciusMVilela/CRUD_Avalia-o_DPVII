package com.example.DP_RPG.domain.records;

import com.example.DP_RPG.enums.CharacterType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterCreateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "War Name is required")
        String warName,

        @NotNull(message = "Character Type is required ") CharacterType characterType,

        @NotNull(message = "Level is required")
        Integer level,

        @NotNull(message = "Strength points are required")
        @Min(value = 0, message = "Strength must be at least 0")
        @Max(value = 10, message = "Strength cannot exceed 10")
        Integer strength,

        @NotNull(message = "Defense points are required")
        @Min(value = 0, message = "Defense must be at least 0")
        @Max(value = 10, message = "Defense cannot exceed 10")
        Integer defense
) {}
