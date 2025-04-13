package com.example.DP_RPG.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.DP_RPG.enums.CharacterType;
import com.example.DP_RPG.enums.MagicType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Setter
    private String warName;

    @Enumerated(EnumType.STRING)
    private CharacterType characterType;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MagicItem> magicItems = new ArrayList<>();

    private Integer level;

    private Integer strength;

    private Integer defense;

    public Integer getTotalStrength() {
        return strength + magicItems.stream()
                .mapToInt(MagicItem::getStrength)
                .sum();
    }

    public Integer getTotalDefense() {
        return defense + magicItems.stream()
                .mapToInt(MagicItem::getDefense)
                .sum();
    }

    public boolean hasAmulet() {
        return magicItems.stream()
                .anyMatch(item -> item.getMagicType() == MagicType.AMULET);
    }

    public void addMagicItem(MagicItem magicItem) {
        magicItems.add(magicItem);
        magicItem.setCharacter(this);
    }

    public void removeMagicItem(MagicItem magicItem) {
        magicItems.remove(magicItem);
        magicItem.setCharacter(null);
    }

    public Optional<MagicItem> getAmulet() {
        return magicItems.stream()
                .filter(item -> item.getMagicType() == MagicType.AMULET)
                .findFirst();
    }

}
