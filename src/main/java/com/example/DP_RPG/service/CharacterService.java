package com.example.DP_RPG.service;

import org.springframework.stereotype.Service;

import com.example.DP_RPG.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

}
