package com.example.DP_RPG.records;

import java.util.List;

public record CharacterDTO(Long id,
                           String name,
                           String adventurerName,
                           String characterClass,
                           Integer level,
                           Integer strength,
                           Integer defense,
                           List<MagicItemDTO> magicItems) {

    public CharacterDTO(com.example.DP_RPG.domain.Character character, List<MagicItemDTO> items) {
        this(
                character.getId(),
                character.getName(),
                character.getWarName(),
                character.getCharacterType().name(),
                character.getLevel(),
                character.getTotalStrength(),
                character.getTotalDefense(),
                items
        );
    }

    public CharacterDTO(com.example.DP_RPG.domain.Character character) {
        this(
                character.getId(),
                character.getName(),
                character.getWarName(),
                character.getCharacterType().name(),
                character.getLevel(),
                character.getTotalStrength(),
                character.getTotalDefense(),
                null
        );
    }
}
