package com.example.DP_RPG.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.DP_RPG.adapter.CharacterAdapter;
import com.example.DP_RPG.adapter.MagicItemAdapter;
import com.example.DP_RPG.domain.Character;
import com.example.DP_RPG.domain.MagicItem;
import com.example.DP_RPG.enums.CharacterType;
import com.example.DP_RPG.enums.MagicType;
import com.example.DP_RPG.records.CharacterCreateDTO;
import com.example.DP_RPG.records.CharacterDTO;
import com.example.DP_RPG.records.CharacterUpdateWarNameDTO;
import com.example.DP_RPG.records.MagicItemDTO;
import com.example.DP_RPG.repository.CharacterRepository;
import com.example.DP_RPG.repository.MagicItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MagicItemRepository magicItemRepository;

    @SneakyThrows
    public CharacterDTO create(CharacterCreateDTO dto) {

        isCharacterValid(dto);

        final Character newCharacter = CharacterAdapter.toEntity(dto);

        return CharacterAdapter.fromEntity(characterRepository.save(newCharacter));
    }

    public List<CharacterDTO> findAll() {
        List<Character> characters = characterRepository.findAll();
        return CharacterAdapter.fromEntityToList(characters);

    }

    public CharacterDTO findById(Long id) {
        final Character character = findCharacter(id);

        return CharacterAdapter.fromEntity(character);
    }

    public CharacterDTO updateWarName(Long id, CharacterUpdateWarNameDTO updatedWarName) {
        final Character character = findCharacter(id);

        character.setWarName(updatedWarName.warName());

        return CharacterAdapter.fromEntity(characterRepository.save(character));
    }

    public void delete(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new RuntimeException("Character not found");
        }
        characterRepository.deleteById(id);
    }

    public CharacterDTO addMagicItem(Long characterId, Long magicItemId) {
        final Character character = findCharacter(characterId);
        final MagicItem magicItem = findMagicItemById(magicItemId);

        if (Objects.nonNull(magicItem.getCharacter())) {
            throw new RuntimeException("Magic Item already have Character");
        }

        if (character.hasAmulet() && magicItem.getMagicType().equals(MagicType.AMULET)) {
            throw new RuntimeException("Character already has Magic Item with AMULET type");
        }

        character.addMagicItem(magicItem);
        return CharacterAdapter.fromEntity(characterRepository.save(character));

    }

    public CharacterDTO removeMagicItem(Long characterId, Long magicItemId) {
        final Character character = findCharacter(characterId);
        final MagicItem magicItem = findMagicItemById(magicItemId);

        final Character magicItemCharacter = magicItem.getCharacter();

        if (Objects.isNull(magicItemCharacter) || !magicItemCharacter.getId().equals(characterId)) {
            throw new RuntimeException("Magic Item is not associate to Character");
        }

        character.removeMagicItem(magicItem);
        return CharacterAdapter.fromEntity(characterRepository.save(character));
    }

    public List<MagicItemDTO> findAllMagicItemsForCharacter(Long characterId) {
        findCharacter(characterId);

        List<MagicItem> magicItems = magicItemRepository.findByCharacterId(characterId);

        return magicItems.stream()
                .map(MagicItemAdapter::fromEntity)
                .toList();
    }

    public MagicItemDTO getCharacterAmulet(Long characterId) {
        final Character character = findCharacter(characterId);

        // VERIFICAR POR CONTA DO RETORNO OPTIONAL NA DOMAIN
        MagicItem magicItem = character.getAmulet().orElseThrow(() -> new RuntimeException("Character don't have Amulet"));

        return MagicItemAdapter.fromEntity(magicItem);

    }

    private Character findCharacter(Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));
    }

    private MagicItem findMagicItemById(Long id) {
        return magicItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Magic Item not found"));
    }

    private void isCharacterValid(CharacterCreateDTO dto) throws Exception {

        final int characterSumAttributes = dto.strength() + dto.defense();

        if (characterSumAttributes != 10) {
            throw new Exception("Total attribute points (Strength + Defense) must not exceed 10");
        }

        try {
            CharacterType.valueOf(dto.characterType().name());
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid character class. Must be one of: WARRIOR, MAGE, ARCHER, ROGUE, BARD");
        }
    }
}
