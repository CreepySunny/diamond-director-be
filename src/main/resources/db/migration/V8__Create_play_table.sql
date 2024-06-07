CREATE TABLE play (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  game_id BIGINT NOT NULL,
  batter_id BIGINT,
  play_result VARCHAR(255),
  rbi INT,
  inning INT,
  half VARCHAR(255),
  pitcher_id BIGINT,
  FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,
  FOREIGN KEY (batter_id) REFERENCES player(id),
  FOREIGN KEY (pitcher_id) REFERENCES player(id)
);
