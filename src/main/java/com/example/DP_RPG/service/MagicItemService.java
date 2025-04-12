package com.example.DP_RPG.service;

import org.springframework.stereotype.Service;

import com.example.DP_RPG.repository.MagicItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MagicItemService {

    private final MagicItemRepository magicItemRepository;
}
