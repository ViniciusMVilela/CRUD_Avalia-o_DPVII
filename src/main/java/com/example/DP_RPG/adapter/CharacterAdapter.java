package com.example.DP_RPG.adapter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.DP_RPG.domain.Character;
import com.example.DP_RPG.records.CharacterCreateDTO;
import com.example.DP_RPG.records.CharacterDTO;
import com.example.DP_RPG.records.MagicItemDTO;

@Component
public class CharacterAdapter {

    public static CharacterDTO fromEntity(Character character) {
        List<MagicItemDTO> magicItemDTOS = character.getMagicItems().stream()
                .map(MagicItemAdapter::fromEntity)
                .toList();

        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getWarName(),
                character.getCharacterType(),
                character.getLevel(),
                character.getStrength(),
                character.getDefense(),
                character.getTotalStrength(),
                character.getTotalDefense(),
                magicItemDTOS
        );
    }

    public static Character toEntity(CharacterCreateDTO dto) {
        return Character.builder()
                .name(dto.name())
                .warName(dto.warName())
                .characterType(dto.characterType())
                .level(dto.level())
                .strength(dto.strength())
                .defense(dto.defense())
                .magicItems(Collections.emptyList())
                .build();
    }

    public static List<CharacterDTO> fromEntityToList(List<Character> characters) {
        return characters.stream()
                .map(CharacterAdapter::fromEntity)
                .collect(Collectors.toList());
    }
}
