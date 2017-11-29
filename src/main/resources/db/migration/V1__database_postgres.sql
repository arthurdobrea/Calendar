-- User creating
CREATE TABLE users (
  id                         BIGSERIAL    NOT NULL PRIMARY KEY,
  username                   VARCHAR(255) NOT NULL,
  password                   VARCHAR(255) NOT NULL,
  email                      VARCHAR(255),
  lastname                   VARCHAR(255),
  firstname                  VARCHAR(255),
  subscription_by_event_type VARCHAR(255),
  subscription_by_tag_type   VARCHAR(255)
);

-- Roles creating
CREATE TABLE roles (
  id   BIGSERIAL    NOT NULL  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

--Users-roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);

--Events
CREATE TABLE events (
  id             BIGSERIAL    NOT NULL PRIMARY KEY,
  event_name     VARCHAR(255) NOT NULL,
  event_type     VARCHAR(255),
  author_user_id INT,
  createdata     TIMESTAMP,
  timebegin      TIMESTAMP,
  timeend        TIMESTAMP,
  event_location VARCHAR(255),
  description    VARCHAR(1000),

  FOREIGN KEY (author_user_id) REFERENCES users (id) ON DELETE CASCADE
);

--Events-user
CREATE TABLE events_users (
  event_id INT NOT NULL,
  user_id  INT,

  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE
);

--Tags
CREATE TABLE tags (
  id        BIGSERIAL    NOT NULL PRIMARY KEY,
  tag_name  VARCHAR(255) NOT NULL,
  tag_color VARCHAR(255) NOT NULL
);

--Events-tags
CREATE TABLE events_tags (
  event_id SERIAL NOT NULL,
  tag_id   SERIAL,

  FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE,
  FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE
);

-- Users inserting
INSERT INTO users (username, password, lastname, firstname)
VALUES ('admin', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC', 'admin', 'admin');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
VALUES ('Kuzea', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'kuzea.k96@gmail.com', 'Kuznetski', 'Dmitry', 'TRAINING,OTHER', 'TOWER');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
VALUES ('Vasea', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'dmitruk.vasilii@gmail.com', 'Dmitruk', 'Vasilii', 'OFFLINE,TRAINING', 'NBC');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
VALUES ('ArturAD', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'adamaa14@gmail.com', 'Dobrya', 'Artur', 'MEETING', 'DEVELOPMENT');

INSERT INTO roles VALUES (1, 'ROLE_SUPREME_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles VALUES (3, 'ROLE_USER');
INSERT INTO roles VALUES (4, 'ROLE_GUEST');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 3);
INSERT INTO user_roles VALUES (3, 3);
INSERT INTO user_roles VALUES (4, 3);

--Events inserting with participants
--1-st part
INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('SQL', 'OTHER', 2, '2017-11-27 10:30:00+02',
        '2017-11-28 11:30:00+02', '2017-11-28 12:00:00+02',
        'Prut', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('MVC', 'WORKSHOP', 2, '2017-11-27 12:30:00+02',
        '2017-11-29 12:30:00+02', '2017-11-29 14:00:00+02',
        'Nistru', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Java Core', 'TRAINING', 2, '2017-11-27 9:30:00+02',
        '2017-11-28 14:30:00+02', '2017-11-28 15:30:00+02',
        'Eugen Doga', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Spring Security', 'TRAINING', 2, '2017-11-29 11:30:00+02',
        '2017-11-30 12:30:00+02', '2017-11-30 14:30:00+02',
        'Vatra', 'Test decription'
);

INSERT INTO events_users VALUES (1, 2);
INSERT INTO events_users VALUES (1, 3);
INSERT INTO events_users VALUES (1, 4);

INSERT INTO events_users VALUES (2, 2);
INSERT INTO events_users VALUES (2, 3);

INSERT INTO events_users VALUES (3, 2);
INSERT INTO events_users VALUES (3, 3);

INSERT INTO events_users VALUES (4, 2);
INSERT INTO events_users VALUES (4, 3);
INSERT INTO events_users VALUES (4, 4);

--2-nd part of events
INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Pianka', 'OFFLINE', 3, '2017-11-23 12:30:00+02',
        '2017-11-24 19:30:00+02', '2017-11-24 22:30:00+02',
        'Pub', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Git', 'TRAINING', 3, '2017-11-27 9:00:00+02',
        '2017-11-27 12:00:00+02', '2017-11-27 13:00:00+02',
        'Stefan cel Mare', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('OneToOne', 'OTHER', 3, '2017-11-27 11:00:00+02',
        '2017-11-28 10:30:00+02', '2017-11-28 10:45:00+02',
        'Prut', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('RegEx', 'WORKSHOP', 3, '2017-11-28 12:30:00+02',
        '2017-11-29 14:30:00+02', '2017-11-29 15:30:00+02',
        'Nistru', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Linux', 'TRAINING', 3, '2017-11-28 9:30:00+02',
        '2017-11-29 11:00:00+02', '2017-11-29 12:00:00+02',
        'Eugen Doga', 'Test decription'
);

INSERT INTO events_users VALUES (5, 3);
INSERT INTO events_users VALUES (5, 2);
INSERT INTO events_users VALUES (5, 4);

INSERT INTO events_users VALUES (6, 3);
INSERT INTO events_users VALUES (6, 2);

INSERT INTO events_users VALUES (7, 3);
INSERT INTO events_users VALUES (7, 4);

INSERT INTO events_users VALUES (8, 3);
INSERT INTO events_users VALUES (8, 2);
INSERT INTO events_users VALUES (8, 4);

INSERT INTO events_users VALUES (9, 3);
INSERT INTO events_users VALUES (9, 4);

--3-rd part of events
INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('StandUp #13', 'STANDUP', 4, '2017-11-27 10:00:00+02',
        '2017-11-27 11:00:00+02', '2017-11-27 11:15:00+02',
        'Raut', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Jenkins', 'TRAINING', 4, '2017-11-27 11:30:00+02',
        '2017-11-27 14:00:00+02', '2017-11-27 15:00:00+02',
        'Stefan cel Mare', 'Test decription'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Hibernate', 'TRAINING', 4, '2017-11-29 10:00:00+02',
        '2017-11-30 11:30:00+02', '2017-11-30 13:00:00+02',
        'Prut', 'Test decription'
);

INSERT INTO events_users VALUES (10, 4);
INSERT INTO events_users VALUES (10, 3);
INSERT INTO events_users VALUES (10, 2);

INSERT INTO events_users VALUES (10, 4);
INSERT INTO events_users VALUES (11, 3);

INSERT INTO events_users VALUES (12, 2);
INSERT INTO events_users VALUES (12, 4);

--Insert tags
INSERT INTO tags (tag_name, tag_color) VALUES ('APPLICATION_MANAGEMENT', '#de681b');
INSERT INTO tags (tag_name, tag_color) VALUES ('DEVELOPMENT', '#de251b');
INSERT INTO tags (tag_name, tag_color) VALUES ('TESTING', '#6f1894');
INSERT INTO tags (tag_name, tag_color) VALUES ('TOWER', '#dea11b');
INSERT INTO tags (tag_name, tag_color) VALUES ('NBC', '#737b8a');
INSERT INTO tags (tag_name, tag_color) VALUES ('ALL_STAFF', '#213e96');

--Insert events-tags
INSERT INTO events_tags VALUES (1, 5);
INSERT INTO events_tags VALUES (2, 4);
INSERT INTO events_tags VALUES (3, 3);
INSERT INTO events_tags VALUES (4, 4);
INSERT INTO events_tags VALUES (5, 6);
INSERT INTO events_tags VALUES (6, 2);
INSERT INTO events_tags VALUES (7, 1);
INSERT INTO events_tags VALUES (8, 3);
INSERT INTO events_tags VALUES (9, 6);
INSERT INTO events_tags VALUES (10, 2);
INSERT INTO events_tags VALUES (11, 1);
INSERT INTO events_tags VALUES (12, 5);

--Persistent logins
CREATE TABLE persistent_logins (
  username  VARCHAR(255),
  series    VARCHAR(255),
  token     VARCHAR(255),
  last_used TIMESTAMP WITHOUT TIME ZONE,

  CONSTRAINT persistent_logins_pk PRIMARY KEY (series)
);