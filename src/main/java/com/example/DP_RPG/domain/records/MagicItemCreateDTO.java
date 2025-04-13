package com.example.DP_RPG.domain.records;

import com.example.DP_RPG.enums.MagicType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MagicItemCreateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Item type is required")
        MagicType magicType,

        @NotNull(message = "Strength points are required")
        @Min(value = 0, message = "Strength must be at least 0")
        @Max(value = 10, message = "Strength cannot exceed 10")
        Integer strength,

        @NotNull(message = "Defense points are required")
        @Min(value = 0, message = "Defense must be at least 0")
        @Max(value = 10, message = "Defense cannot exceed 10")
        Integer defense
) {
    public void validate() {


    }
}