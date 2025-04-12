package com.example.DP_RPG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DP_RPG.domain.MagicItem;

@Repository
public interface MagicItemRepository extends JpaRepository<MagicItem, Long> {
}
