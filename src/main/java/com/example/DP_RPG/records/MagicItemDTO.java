package com.example.DP_RPG.records;

import com.example.DP_RPG.domain.MagicItem;

public record MagicItemDTO(Long id, String name, String magicType, Integer strength, Integer defense, Long characterId) {

    public MagicItemDTO(MagicItem item) {
        this(
                item.getId(),
                item.getName(),
                item.getMagicType().name(),
                item.getStrength(),
                item.getDefense(),
                item.getCharacter() != null ? item.getCharacter().getId() : null
        );
    }
}
