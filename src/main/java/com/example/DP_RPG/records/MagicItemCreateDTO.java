package com.example.DP_RPG.records;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MagicItemCreateDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Item type is required")
        String magicType,

        @NotNull(message = "Strength points are required")
        @Min(value = 0, message = "Strength must be at least 0")
        @Max(value = 10, message = "Strength cannot exceed 10")
        Integer strength,

        @NotNull(message = "Defense points are required")
        @Min(value = 0, message = "Defense must be at least 0")
        @Max(value = 10, message = "Defense cannot exceed 10")
        Integer defense
) {
    public boolean isMagicItemValid() {
        if (strength == null || defense == null) {
            return false;
        }

        if (strength == 0 && defense == 0) {
            return false;
        }

        if ("WEAPON".equalsIgnoreCase(magicType) && defense != 0) {
            return false;
        }

        if ("ARMOR".equalsIgnoreCase(magicType) && strength != 0) {
            return false;
        }

        return strength >= 0 && strength <= 10 && defense >= 0 && defense <= 10;
    }
}