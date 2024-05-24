CREATE TABLE team (
  team_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  team_name VARCHAR(255) NOT NULL
);

ALTER TABLE coaches
    ADD COLUMN team_id BIGINT,
    ADD CONSTRAINT fk_coach_team FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE SET NULL;

ALTER TABLE player
    ADD COLUMN team_id BIGINT,
    ADD CONSTRAINT fk_player_team FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE SET NULL;
