package com.example.DP_RPG.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.DP_RPG.domain.MagicItem;
import com.example.DP_RPG.records.MagicItemCreateDTO;
import com.example.DP_RPG.records.MagicItemDTO;

@Component
public class MagicItemAdapter {

    public static MagicItem toEntity(MagicItemCreateDTO dto) {
        return MagicItem.builder()
                .name(dto.name())
                .magicType(dto.magicType())
                .strength(dto.strength())
                .defense(dto.defense())
                .build();
    }

    public static MagicItemDTO fromEntity(MagicItem magicItem) {
        return new MagicItemDTO(
                magicItem.getId(),
                magicItem.getName(),
                magicItem.getMagicType(),
                magicItem.getStrength(),
                magicItem.getDefense(),
                magicItem.getCharacter() != null ? magicItem.getCharacter().getId() : null
        );
    }

    public static List<MagicItemDTO> fromEntityToList(List<MagicItem> magicItems) {
        return magicItems.stream()
                .map(MagicItemAdapter::fromEntity)
                .collect(Collectors.toList());
    }
}
