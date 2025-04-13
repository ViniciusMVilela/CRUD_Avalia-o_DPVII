package com.example.DP_RPG.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DP_RPG.domain.MagicItem;
import com.example.DP_RPG.enums.MagicType;

@Repository
public interface MagicItemRepository extends JpaRepository<MagicItem, Long> {

    List<MagicItem> findByCharacterId(Long characterId);

    @Query("SELECT i FROM MagicItem i WHERE i.character.id = :characterId AND i.magicType = :magicType")
    List<MagicItem> findByCharacterIdAndMagicItem(@Param("characterId") Long characterId, @Param("magicType") MagicType magicType);

    @Query("SELECT i FROM MagicItem i WHERE i.character.id = :characterId AND i.magicType = com.example.DP_RPG.enums.MagicType.AMULET")
    Optional<MagicItem> findAmuletByCharacterId(@Param("characterId") Long characterId);

}
