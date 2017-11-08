drop TABLE roles;
drop TABLE users;
drop TABLE user_roles;

drop TABLE events;
drop TABLE events_users;
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
    REFERENCES users(id),
  role_id BIGINT
    CONSTRAINT user_roles_roles_id_fk
    REFERENCES roles(id)
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

CREATE TABLE events(
  id        SERIAL NOT NULL
    CONSTRAINT events_pkey
    PRIMARY KEY,
  event_type  VARCHAR(255),
  event_location VARCHAR(255),
  timebegin  VARCHAR(255),
  timend     VARCHAR(255),
  createdata  TIMESTAMP,
  description VARCHAR(255),
  author_user_id BIGINT
    CONSTRAINT events_users__fk
    REFERENCES users(id),
  event_name VARCHAR(255)
);

CREATE TABLE events_users(
  user_id BIGINT
    CONSTRAINT events_users_users_fk
    REFERENCES users(id),
  event_id BIGINT
    CONSTRAINT events_users_events_fk
    REFERENCES events(id)
);

insert into events values(1, 'MEETING', 'NBC','2017-11-11 10:23:54', '2017-11-11 10:24:54',
                          now(), 'description', '1', 'example');

create table persistent_logins(
  username character varying (255),
  series character varying (255),
  token character varying (255),
  last_used timestamp without time zone,
  constraint persistent_logins_pk primary key (series)
);