DROP TABLE IF EXISTS member;
CREATE TABLE member(
    user_id VARCHAR(20) NOT NULL,
    password VARCHAR(300),
    name VARCHAR(50),
    reg_no VARCHAR(300),
    PRIMARY KEY(user_id)
);
