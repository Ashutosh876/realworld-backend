CREATE TABLE user_table(
id              UUID            PRIMARY KEY         NOT NULL,
username        VARCHAR(100)                        NOT NULL,
email           VARCHAR(255)    UNIQUE                    NOT NULL,
password        VARCHAR(255),
bio             VARCHAR(1024),
token           VARCHAR(64),
image           VARCHAR(1024)
);