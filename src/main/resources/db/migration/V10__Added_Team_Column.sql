ALTER TABLE game
    ADD COLUMN home_team_id BIGINT,
    ADD COLUMN away_team_id BIGINT;

ALTER TABLE game
    ADD CONSTRAINT fk_home_team
        FOREIGN KEY (home_team_id)
            REFERENCES team(team_id),
    ADD CONSTRAINT fk_away_team
        FOREIGN KEY (away_team_id)
            REFERENCES team(team_id);