package com.example.DP_RPG.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DP_RPG.records.CharacterCreateDTO;
import com.example.DP_RPG.records.CharacterDTO;
import com.example.DP_RPG.records.CharacterUpdateWarNameDTO;
import com.example.DP_RPG.records.MagicItemDTO;
import com.example.DP_RPG.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/character")
@RequiredArgsConstructor
@Tag(name = "Character", description = "API to Character")
public class CharacterResource {

    private final CharacterService characterService;

    @PostMapping
    @Operation(summary = "Create a new character")
    public ResponseEntity<CharacterDTO> create(@Valid @RequestBody CharacterCreateDTO dto) {
        CharacterDTO createdCharacter = characterService.create(dto);
        return new ResponseEntity<>(createdCharacter, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Find All Characters")
    public ResponseEntity<List<CharacterDTO>> findAll() {
        List<CharacterDTO> characters = characterService.findAll();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Character By ID")
    public ResponseEntity<CharacterDTO> findById(@PathVariable Long id) {
        CharacterDTO character = characterService.findById(id);
        return ResponseEntity.ok(character);
    }

    @PatchMapping("/{id}/war-name")
    @Operation(summary = "Update character war name")
    public ResponseEntity<CharacterDTO> updateWarName(
            @PathVariable Long id,
            @RequestBody CharacterUpdateWarNameDTO warName) {
        CharacterDTO character = characterService.updateWarName(id, warName);
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Character")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{characterId}/{magicItemsId}")
    @Operation(summary = "Add Magic Items")
    public ResponseEntity<CharacterDTO> addMagicItems(
            @PathVariable Long characterId,
            @PathVariable Long magicItemsId) {
        CharacterDTO character = characterService.addMagicItem(characterId, magicItemsId);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/{characterId}/items")
    @Operation(summary = "Find Character Magic Items")
    public ResponseEntity<List<MagicItemDTO>> findAllMagicItems(@PathVariable Long characterId) {
        List<MagicItemDTO> items = characterService.findAllMagicItemsForCharacter(characterId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{characterId}/items/{itemId}")
    @Operation(summary = "Remove Magic Item from Character")
    public ResponseEntity<CharacterDTO> removeMagicItem(
            @PathVariable Long characterId,
            @PathVariable Long itemId) {
        CharacterDTO character = characterService.removeMagicItem(characterId, itemId);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/{characterId}/amulet")
    @Operation(summary = "Find Amulet Character")
    public ResponseEntity<MagicItemDTO> findAmuletCharacter(@PathVariable Long characterId) {
        MagicItemDTO magicAmulet = characterService.getCharacterAmulet(characterId);
        return ResponseEntity.ok(magicAmulet);
    }
}
