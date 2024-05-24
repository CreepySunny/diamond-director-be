CREATE TABLE lineup_card_entry (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   lineup_position INT,
   fielding_position VARCHAR(255),
   player_id BIGINT,
   lineup_card_id BIGINT
);
