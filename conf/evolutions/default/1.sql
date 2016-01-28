# User table

# --- !Ups

CREATE TABLE Car (
    reg_number varchar(50) NOT NULL,
    make varchar(255) NOT NULL,
    owner varchar(50) NOT NULL,
    v5c bigint NULL,
    PRIMARY KEY (reg_number)
);

# --- !Downs

DROP TABLE Car;