package com.example.DP_RPG.domain.records;

import com.example.DP_RPG.enums.MagicType;

public record MagicItemDTO(Long id, String name, MagicType magicType, Integer strength, Integer defense, Long characterId) {

}
