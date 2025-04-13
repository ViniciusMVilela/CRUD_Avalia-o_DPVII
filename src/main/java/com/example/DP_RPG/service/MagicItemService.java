package com.example.DP_RPG.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DP_RPG.adapter.MagicItemAdapter;
import com.example.DP_RPG.domain.MagicItem;
import com.example.DP_RPG.enums.MagicType;
import com.example.DP_RPG.records.MagicItemCreateDTO;
import com.example.DP_RPG.records.MagicItemDTO;
import com.example.DP_RPG.repository.MagicItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MagicItemService {

    private final MagicItemRepository magicItemRepository;

    public MagicItemDTO create(MagicItemCreateDTO dto) {
        isMagicItemValid(dto);

        final MagicItem magicItem = MagicItemAdapter.toEntity(dto);
        return MagicItemAdapter.fromEntity(magicItemRepository.save(magicItem));
    }

    public List<MagicItemDTO> findAll() {
        final List<MagicItem> magicItems = magicItemRepository.findAll();
        return MagicItemAdapter.fromEntityToList(magicItems);
    }

    public MagicItemDTO findById(Long id) throws Exception {
        final MagicItem magicItem = magicItemRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Magic Item not found")
        );

        return MagicItemAdapter.fromEntity(magicItem);
    }

    private void isMagicItemValid(MagicItemCreateDTO dto) {
        if (dto.strength() == 0 && dto.defense() == 0) {
            throw new IllegalArgumentException("Item mágico deve ter força ou defesa maior que zero");
        }

        if (dto.magicType() == MagicType.WEAPON && dto.defense() > 0) {
            throw new IllegalArgumentException("Item do tipo ARMA deve ter defesa igual a zero");
        }

        if (dto.magicType() == MagicType.ARMOR && dto.strength() > 0) {
            throw new IllegalArgumentException("Item do tipo ARMADURA deve ter força igual a zero");
        }
    }
}
