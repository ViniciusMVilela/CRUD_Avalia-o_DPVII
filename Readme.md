### RPG API

### [Swagger Documentation](http://localhost:8080/swagger-ui/index.html#/)


### H2 Initial Insert Data

##### Crate 5 Characters without magic items and One with magic items (CharacterId 6)

INSERT INTO character (id, name, war_name, character_type, level, strength, defense) VALUES
(1, 'Character 1', 'WarName1', 'WARRIOR', 1, 6, 4),
(2, 'Character 2', 'WarName2', 'MAGE', 2, 3, 7),
(3, 'Character 3', 'WarName3', 'ARCHER', 4, 5, 5),
(4, 'Character 4', 'WarName4', 'ROGUE', 0, 7, 3),
(5, 'Character 5', 'WarName5', 'BARD', 1, 4, 6),
(6, 'Character With Items', 'WarName6', 'ROGUE', 12, 7, 3);


##### Create 4 Magic Items not associate a Character and 3 associate to Character 6

INSERT INTO magic_item (id, name, magic_type, character_id, strength, defense) VALUES
(1, 'WEAPON 1', 'WEAPON', NULL, 10, 0),
(2, 'ARMOR 1', 'ARMOR', NULL, 0, 10),
(3, 'Amulet 1', 'AMULET', NULL, 10, 10),
(4, 'WEAPON 2', 'WEAPON', NULL, 5, 0),
(5, 'ARMOR Character', 'ARMOR', 6, 0, 10),
(6, 'Amulet Character', 'AMULET', 6, 10, 10),
(7, 'WEAPON Character', 'WEAPON', 6, 5, 0);