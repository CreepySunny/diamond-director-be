CREATE TABLE player (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    handed_bats VARCHAR(50),
    handed_throws VARCHAR(50),
    position VARCHAR(50),
    date_of_birth DATE,
    height DOUBLE,
    weight DOUBLE
);
