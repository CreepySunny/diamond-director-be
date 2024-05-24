-- Add foreign key relationships between game and lineup_card tables
ALTER TABLE game
    ADD CONSTRAINT fk_away_lineup_card FOREIGN KEY (away_lineup_card_id) REFERENCES lineup_card(id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_home_lineup_card FOREIGN KEY (home_lineup_card_id) REFERENCES lineup_card(id) ON DELETE SET NULL;

-- Add foreign key relationship between lineup_card_entry and player table
ALTER TABLE lineup_card_entry
    ADD CONSTRAINT fk_player_lineup_card_entry FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE;

-- Add foreign key relationship between lineup_card_entry and lineup_card table
ALTER TABLE lineup_card_entry
    ADD CONSTRAINT fk_lineup_card_lineup_card_entry FOREIGN KEY (lineup_card_id) REFERENCES lineup_card(id) ON DELETE CASCADE;
