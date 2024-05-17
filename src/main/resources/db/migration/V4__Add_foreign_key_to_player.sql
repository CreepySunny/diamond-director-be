ALTER TABLE player ADD COLUMN user_entity_id BIGINT NOT NULL;

ALTER TABLE player
    ADD CONSTRAINT fk_user_entity_player
        FOREIGN KEY (user_entity_id)
            REFERENCES user_account (id)
            ON DELETE CASCADE;
