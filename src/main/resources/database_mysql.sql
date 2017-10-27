CREATE TABLE users (
  id       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)

  ENGINE = InnoDB;

CREATE TABLE  roles(
  id int NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE  user_roles(
  user_id int not NULL,
  role_id int not null,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),

  UNIQUE (user_id,role_id)
)

  ENGINE = InnoDB;

INSERT INTO users VALUES (1, 'admin','312r821747f19f1830ue984f57910fj');

INSERT roles VALUES (1,'ROLE_USER');
INSERT roles VALUES (2,'ROLE_ADMIN');

INSERT INTO user_roles VALUES(1,2);


############### MY JOB ###########
# creating table for the events
CREATE TABLE events
(
  id             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  event_name     VARCHAR(255) NOT NULL,
  event_type     VARCHAR(255),
  author_user_id INT,
  createdata     DATETIME,
  timebegin      DATETIME,
  timeend        DATETIME,
  event_location VARCHAR(255),
  description    VARCHAR(1000),

  FOREIGN KEY (author_user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;


#create mapping table for the participants at the events
CREATE TABLE events_users
(
  event_id BIGINT NOT NULL,
  user_id INT,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (event_id) REFERENCES events(id)
)
  ENGINE = InnoDB;


#adding some events to the events table
INSERT INTO events(event_name, event_type, author_user_id, createdata, timebegin, timeend, event_location, description)
VALUES ('pianka', 'standup', 1, now(), '2017-10-27 12:30:00', '2017-10-27 13:30:00',
        'Moldova', 'ohohoh!!! ');

INSERT INTO events (event_name, event_type, author_user_id,
                    timebegin, timeend, createdata, event_location, description)
VALUES ('Im the boss', 'celebration', 2, '2017-10-25 11:30:00', '2017-10-28 15:30:00', now(),
        'Restaurant', 'uraaaa');


INSERT into events_users VALUES (1,1);
INSERT into events_users VALUES (1,2);
INSERT into events_users VALUES (2,1);
INSERT into events_users VALUES (2,2);
