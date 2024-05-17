CREATE TABLE coaches (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 date_of_birth DATE,
 user_entity_id BIGINT NOT NULL,
 CONSTRAINT fk_user_entity_coach
     FOREIGN KEY (user_entity_id)
         REFERENCES user_account (id)
         ON DELETE CASCADE
);
