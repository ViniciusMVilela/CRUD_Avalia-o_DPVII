package com.example.DP_RPG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<com.example.DP_RPG.domain.Character, Long> {
}
