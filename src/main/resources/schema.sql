DROP TABLE IF EXISTS member;

CREATE TABLE member(
    user_id VARCHAR(20) NOT NULL,
    password VARCHAR(300) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY(user_id)
);
