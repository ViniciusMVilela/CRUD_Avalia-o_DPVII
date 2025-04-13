package com.example.DP_RPG.records;

import java.util.List;

import com.example.DP_RPG.enums.CharacterType;

public record CharacterDTO(Long id,
                           String name,
                           String warName,
                           CharacterType characterType,
                           Integer level,
                           Integer strength,
                           Integer defense,
                           Integer totalStrength,
                           Integer totalDefense,
                           List<MagicItemDTO> magicItems) {


}
