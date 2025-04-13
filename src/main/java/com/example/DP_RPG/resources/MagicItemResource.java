package com.example.DP_RPG.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DP_RPG.domain.records.MagicItemCreateDTO;
import com.example.DP_RPG.domain.records.MagicItemDTO;
import com.example.DP_RPG.service.MagicItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/magic-item")
@RequiredArgsConstructor
@Tag(name = "Magic Item", description = "API for magic items manipulation")
public class MagicItemResource {

    private final MagicItemService magicItemService;

    @PostMapping
    @Operation(summary = "Create new Magic Item")
    public ResponseEntity<MagicItemDTO> crate(@Valid @RequestBody MagicItemCreateDTO requestDTO) throws Exception {
        MagicItemDTO responseDTO = magicItemService.create(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Find All Magic Items")
    public ResponseEntity<List<MagicItemDTO>> findAll() {
        List<MagicItemDTO> magicItems = magicItemService.findAll();
        return ResponseEntity.ok(magicItems);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Magic Item By ID")
    public ResponseEntity<MagicItemDTO> findById(@PathVariable Long id) throws Exception {
        MagicItemDTO magicItem = magicItemService.findById(id);
        return ResponseEntity.ok(magicItem);
    }
}
