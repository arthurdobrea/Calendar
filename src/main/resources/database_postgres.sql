-- auto-generated definition
CREATE TABLE users
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  username  VARCHAR(255),
  firstname VARCHAR(255),
  lastname  VARCHAR(255),
  email     VARCHAR(255),
  password  VARCHAR(255)
);

-- auto-generated definition
CREATE TABLE roles
(
  id   BIGSERIAL NOT NULL
    CONSTRAINT roles_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);

-- auto-generated definition
CREATE TABLE user_roles
(
  user_id BIGINT
    CONSTRAINT user_roles_fk
    REFERENCES users,
  role_id BIGINT
    CONSTRAINT user_roles_roles_id_fk
    REFERENCES roles
);

INSERT INTO users (username, password) VALUES ('admin', '$2a$11$oBd.94VWAUq6RejwkI4sh.eo7XHOUXpw2oNAMFCLEHpV8fWUligLK');
--password - 12345678

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);

--Inserts for testing
INSERT INTO users(id, username, firstname, lastname, email, password)
    VALUES (99, 'UserTest', 'Qawsed', 'Qawsed', 'adamaa14@gmail.com', '$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG');

INSERT INTO user_roles VALUES(99, 1);