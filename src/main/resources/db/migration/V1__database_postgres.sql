-- User creating
CREATE TABLE users (
  id                         BIGSERIAL    NOT NULL PRIMARY KEY,
  username                   VARCHAR(255) NOT NULL UNIQUE ,
  password                   VARCHAR(255) NOT NULL,
  email                      VARCHAR(255) UNIQUE,
  lastname                   VARCHAR(255),
  firstname                  VARCHAR(255),
  subscription_by_event_type VARCHAR(255),
  subscription_by_tag_type   VARCHAR(255),
  position                   VARCHAR(255)
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

-- INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
-- VALUES ('Kuzea', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
--         'kuzea.k96@gmail.com', 'Kuznetski', 'Dmitry', 'TRAINING,OTHER', 'TOWER');
--
-- INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
-- VALUES ('Vasea', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
--         'dmitruk.vasilii@gmail.com', 'Dmitruk', 'Vasilii', 'OFFLINE,TRAINING', 'NBC');
--
-- INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
-- VALUES ('ArturAD', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
--         'adamaa14@gmail.com', 'Dobrya', 'Artur', 'MEETING', 'DEVELOPMENT');
--
-- INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type)
-- VALUES ('maximH123', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
--         'maxhristiniuc@mail.ru', 'Hristiniuc', 'Maxim', 'MEETING, TRAINING', 'DEVELOPMENT');


--Users for demo
INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('dkuznetski', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'dkuznetski@gmail.com', 'Cuznetchii', 'Dmitrii', 'TRAINING,OTHER', 'TOWER', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('vdmitruc', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'vdmitruc@gmail.com', 'Dmitruc', 'Vasile', 'OFFLINE,TRAINING', 'NBC', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('adobrya', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'adobrya@gmail.com', 'Dobrea', 'Artur', 'MEETING', 'DEVELOPMENT', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('mhristiniuc', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'mhristiniuc@gmail.com', 'Hristiniuc', 'Maxim', 'MEETING, TRAINING', 'DEVELOPMENT', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('smorari', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'smorari@gmail.com', 'Morari', 'Serghei', 'MEETING, TRAINING', 'DEVELOPMENT', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('isirosenco', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'isirosenco@gmail.com', 'Sirosenco', 'Ivan', 'MEETING, TRAINING', 'DEVELOPMENT', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('icebotari', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'icebotari@gmail.com', 'Cebotari', 'Ilia', 'MEETING, TRAINING', 'DEVELOPMENT', 'AM Java Intern');

INSERT INTO users (username, password, email, lastname, firstname, subscription_by_event_type, subscription_by_tag_type, position)
VALUES ('eciolacu', '$2a$11$4ZwgE8rsoWXEBWsZcIld/.3lJ4y2PLAmigqFX7O2oyKau9j6aS6IC',
        'eciolacu@gmail.com', 'Ciolacu', 'Eugen', 'MEETING, TRAINING', 'DEVELOPMENT', 'AM Java Intern');



INSERT INTO roles VALUES (1, 'ROLE_SUPREME_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');
INSERT INTO roles VALUES (3, 'ROLE_USER');
INSERT INTO roles VALUES (4, 'ROLE_GUEST');

INSERT INTO user_roles VALUES (1, 1);--admin
-- INSERT INTO user_roles VALUES (2, 3);--kuzea
-- INSERT INTO user_roles VALUES (3, 3);--Vasea
-- INSERT INTO user_roles VALUES (4, 3);--ArturAD
-- INSERT INTO user_roles VALUES (5, 3);--maximH123


INSERT INTO user_roles VALUES (2, 3); --dima
INSERT INTO user_roles VALUES (3, 3);--vasea
INSERT INTO user_roles VALUES (4, 3);--artur
INSERT INTO user_roles VALUES (5, 3);--max
INSERT INTO user_roles VALUES (6, 3);--serghei
INSERT INTO user_roles VALUES (7, 3);--ivan
INSERT INTO user_roles VALUES (8, 3);--ilia
INSERT INTO user_roles VALUES (9, 3);--eugen


--Events inserting with participants
--Events created by Dima
INSERT INTO events (event_name, event_type, author_user_id, createdata, --id:1 Tower
                    timebegin, timeend, event_location, description)
VALUES ('SQL 1', 'TRAINING', 2, '2017-12-06 10:30:00+02',
        '2017-12-06 11:30:00+02', '2017-12-06 12:30:00+02',
        'Prut, Tower 1st floor', 'Introduction to SQL'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:2 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Spring MVC', 'WORKSHOP', 2, '2017-11-27 12:30:00+02',
        '2017-12-21 12:30:00+02', '2017-12-21 14:00:00+02',
        'Nistru, Tower 1st floor', 'Introduction to Spring MVC'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:3 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Java Core', 'TRAINING', 2, '2017-11-27 9:30:00+02',
        '2017-12-07 14:30:00+02', '2017-12-07 15:30:00+02',
        'Eugen Doga, Tower', 'Collections in Java'
);


--Events created by Vasea
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:4 All staff
                    timebegin, timeend, event_location, description)
VALUES ('Christmas Party', 'OFFLINE', 3, '2017-12-15 16:30:00+02',
        '2017-12-15 19:30:00+02', '2017-12-16 00:30:00+02',
        'Chisinau Pub', 'FUN FUN FUN'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:5 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Git', 'TRAINING', 3, '2017-11-27 9:00:00+02',
        '2017-12-27 12:00:00+02', '2017-12-27 13:00:00+02',
        'NBC, Casa Mare', 'Introduction to Git'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:6 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Final interview', 'OTHER', 3, '2017-11-27 11:00:00+02',
        '2017-12-14 10:30:00+02', '2017-12-14 11:30:00+02',
        'NBC, Vatra', 'Interview at the end of the internship'
);


--Events created by Artur
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:7 Tower
                    timebegin, timeend, event_location, description)
VALUES ('RegEx', 'WORKSHOP', 4, '2017-11-28 12:30:00+02',
        '2017-12-11 14:30:00+02', '2017-12-11 15:30:00+02',
        'Tower, Nistru', 'Regular expressions workshop'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:8 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Linux', 'TRAINING', 4, '2017-11-28 9:30:00+02',
        '2017-11-29 11:00:00+02', '2017-11-29 12:00:00+02',
        'Tower, Eugen Doga', 'Introduction to Linux'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:9 Application Management
                    timebegin, timeend, event_location, description)
VALUES ('Trip to Cricova', 'TEAM_BUILDING', 4, '2017-11-28 9:30:00+02',
        '2017-12-02 11:00:00+02', '2017-12-02 12:00:00+02',
        'Eugen Doga', 'Trip to Cricova'
);

--Events created by Maxim
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:10 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Spring Security', 'TRAINING', 5, '2017-11-27 10:00:00+02',
        '2017-12-18 11:00:00+02', '2017-12-18 11:15:00+02',
        'NBC, Vatra, 3rd floor', 'Training on Spring Security'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:11 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Customer Care', 'MEETING', 5, '2017-11-27 11:30:00+02',
        '2017-12-20 14:00:00+02', '2017-12-20 15:00:00+02',
        'NBC, Dor, 3rd floor', 'Training on Customer care'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:12 All staff
                    timebegin, timeend, event_location, description)
VALUES ('New Year Party', 'OFFLINE', 5, '2017-11-29 10:00:00+02',
        '2017-12-30 19:30:00+02', '2017-12-31 01:00:00+02',
        'Chisinau Restaurant', 'Final party of the year'
);


--Events created by Serghei
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:13 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Business Writing', 'TRAINING', 6, '2017-11-27 10:00:00+02',
        '2017-12-01 11:00:00+02', '2017-12-01 11:15:00+02',
        'NBC, Vatra, 3rd floor', 'Training on Spring Security'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:14 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Final Demo', 'OTHER', 6, '2017-11-27 11:30:00+02',
        '2017-12-04 16:30:00+02', '2017-12-04 18:00:00+02',
        'NBC, Dor, 3rd floor', 'Training on Customer care'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:15 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Automation testing', 'OFFLINE', 6, '2017-11-29 10:00:00+02',
        '2017-12-5 10:00:00+02', '2017-12-5 11:00:00+02',
        'NBC, Casa Mare', 'Training on automation testing'
);

--Events created by Ivan
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:16 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Java Script basics', 'TRAINING', 7, '2017-11-27 10:00:00+02',
        '2017-12-6 14:00:00+02', '2017-12-6 15:00:00+02',
        'Tower, 3rd floor', 'Training on Java Script'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:17 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Unit Test', 'WORKSHOP', 7, '2017-11-27 11:30:00+02',
        '2017-12-04 10:30:00+02', '2017-12-04 12:00:00+02',
        'Tower, Stefan cel Mare, 2nd floor', 'Training on Customer care'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:18 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Java Core', 'TRAINING', 7, '2017-11-29 10:00:00+02',
        '2017-12-5 13:30:00+02', '2017-12-5 15:00:00+02',
        'Tower, Prut', 'Induction to Java 8'
);


--Events created by Ilia
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:19 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Data Management', 'MEETING', 8, '2017-11-27 10:00:00+02',
        '2017-11-27 11:00:00+02', '2017-11-27 12:00:00+02',
        'NBC, Vatra, 3rd floor', 'Training on Data Management'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:20 NBC
                    timebegin, timeend, event_location, description)
VALUES ('Stand Up', 'STAND_UP', 8, '2017-11-27 11:30:00+02',
        '2017-12-12 10:00:00+02', '2017-12-12 10:15:00+02',
        'NBC, Dor, 3rd floor', 'Stand up Meeting'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:21 All staff
                    timebegin, timeend, event_location, description)
VALUES ('Endava football cup', 'OFFLINE', 8, '2017-11-29 10:00:00+02',
        '2017-12-16 09:30:00+02', '2017-12-16 15:00:00+02',
        'Zimbru Stadium', 'Football tournament'
);

--Events created by Eugen
INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:22 Tower
                    timebegin, timeend, event_location, description)
VALUES ('How to win friends and influence people', 'TRAINING', 9, '2017-11-27 10:00:00+02',
        '2017-12-13 16:00:00+02', '2017-12-13 17:00:00+02',
        'Tower, Nistru meeting room', 'Presentation based on a book by Dale Carnegie'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:23 Tower
                    timebegin, timeend, event_location, description)
VALUES ('SQL advanced', 'WORKSHOP', 9, '2017-11-27 11:30:00+02',
        '2017-12-18 16:30:00+02', '2017-12-18 18:00:00+02',
        'Tower, Prut meeting room', 'Training on SQL procedures'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:24 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Linux advanced', 'MEETING', 9, '2017-11-29 10:00:00+02',
        '2017-12-07 10:30:00+02', '2017-12-07 11:30:00+07',
        'Prut', 'Induction to Java 8'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,--id:25 Tower
                    timebegin, timeend, event_location, description)
VALUES ('Jenkins', 'WORKSHOP', 9, '2017-11-29 10:00:00+02',
        '2017-12-06 15:30:00+02', '2017-12-06 16:30:00+07',
        'Tower, Prut', 'Jenkins basics'
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Stand up', 'STAND_UP', 5, '2017-11-29 10:00:00+02',
        '2017-12-08 10:00:00+02', '2017-12-08 10:20:00+02',
        'Tower, Raut meeting room', 'Stand up'
);


INSERT INTO events_users VALUES (1, 2);
INSERT INTO events_users VALUES (1, 3);
INSERT INTO events_users VALUES (1, 4);
INSERT INTO events_users VALUES (1, 5);
INSERT INTO events_users VALUES (1, 6);
INSERT INTO events_users VALUES (1, 7);
INSERT INTO events_users VALUES (1, 8);
INSERT INTO events_users VALUES (1, 9);

INSERT INTO events_users VALUES (2, 2);
INSERT INTO events_users VALUES (2, 3);
INSERT INTO events_users VALUES (2, 4);
INSERT INTO events_users VALUES (2, 5);
INSERT INTO events_users VALUES (2, 6);
INSERT INTO events_users VALUES (2, 7);
INSERT INTO events_users VALUES (2, 8);
INSERT INTO events_users VALUES (2, 9);

INSERT INTO events_users VALUES (3, 2);
INSERT INTO events_users VALUES (3, 3);
INSERT INTO events_users VALUES (3, 4);
INSERT INTO events_users VALUES (3, 5);
INSERT INTO events_users VALUES (3, 6);
INSERT INTO events_users VALUES (3, 7);
INSERT INTO events_users VALUES (3, 8);
INSERT INTO events_users VALUES (3, 9);

INSERT INTO events_users VALUES (4, 2);
INSERT INTO events_users VALUES (4, 3);
INSERT INTO events_users VALUES (4, 4);
INSERT INTO events_users VALUES (4, 5);
INSERT INTO events_users VALUES (4, 6);
INSERT INTO events_users VALUES (4, 7);
INSERT INTO events_users VALUES (4, 8);
INSERT INTO events_users VALUES (4, 9);

INSERT INTO events_users VALUES (5, 2);
INSERT INTO events_users VALUES (5, 3);
INSERT INTO events_users VALUES (5, 4);
INSERT INTO events_users VALUES (5, 5);
INSERT INTO events_users VALUES (5, 6);
INSERT INTO events_users VALUES (5, 7);
INSERT INTO events_users VALUES (5, 8);
INSERT INTO events_users VALUES (5, 9);

INSERT INTO events_users VALUES (6, 2);
INSERT INTO events_users VALUES (6, 3);
INSERT INTO events_users VALUES (6, 4);
INSERT INTO events_users VALUES (6, 5);
INSERT INTO events_users VALUES (6, 6);
INSERT INTO events_users VALUES (6, 7);
INSERT INTO events_users VALUES (6, 8);
INSERT INTO events_users VALUES (6, 9);

INSERT INTO events_users VALUES (7, 2);
INSERT INTO events_users VALUES (7, 3);
INSERT INTO events_users VALUES (7, 4);
INSERT INTO events_users VALUES (7, 5);
INSERT INTO events_users VALUES (7, 6);
INSERT INTO events_users VALUES (7, 7);
INSERT INTO events_users VALUES (7, 8);
INSERT INTO events_users VALUES (7, 9);

INSERT INTO events_users VALUES (8, 2);
INSERT INTO events_users VALUES (8, 3);
INSERT INTO events_users VALUES (8, 4);
INSERT INTO events_users VALUES (8, 5);
INSERT INTO events_users VALUES (8, 6);
INSERT INTO events_users VALUES (8, 7);
INSERT INTO events_users VALUES (8, 8);
INSERT INTO events_users VALUES (8, 9);

INSERT INTO events_users VALUES (9, 2);
INSERT INTO events_users VALUES (9, 3);
INSERT INTO events_users VALUES (9, 4);
INSERT INTO events_users VALUES (9, 5);
INSERT INTO events_users VALUES (9, 6);
INSERT INTO events_users VALUES (9, 7);
INSERT INTO events_users VALUES (9, 8);
INSERT INTO events_users VALUES (9, 9);

INSERT INTO events_users VALUES (10, 2);
INSERT INTO events_users VALUES (10, 3);
INSERT INTO events_users VALUES (10, 4);
INSERT INTO events_users VALUES (10, 5);
INSERT INTO events_users VALUES (10, 6);
INSERT INTO events_users VALUES (10, 7);
INSERT INTO events_users VALUES (10, 8);
INSERT INTO events_users VALUES (10, 9);

INSERT INTO events_users VALUES (11, 2);
INSERT INTO events_users VALUES (11, 3);
INSERT INTO events_users VALUES (11, 4);
INSERT INTO events_users VALUES (11, 5);
INSERT INTO events_users VALUES (11, 6);
INSERT INTO events_users VALUES (11, 7);
INSERT INTO events_users VALUES (11, 8);
INSERT INTO events_users VALUES (11, 9);

INSERT INTO events_users VALUES (12, 2);
INSERT INTO events_users VALUES (12, 3);
INSERT INTO events_users VALUES (12, 4);
INSERT INTO events_users VALUES (12, 5);
INSERT INTO events_users VALUES (12, 6);
INSERT INTO events_users VALUES (12, 7);
INSERT INTO events_users VALUES (12, 8);
INSERT INTO events_users VALUES (12, 9);

INSERT INTO events_users VALUES (13, 2);
INSERT INTO events_users VALUES (13, 3);
INSERT INTO events_users VALUES (13, 4);
INSERT INTO events_users VALUES (13, 5);
INSERT INTO events_users VALUES (13, 6);
INSERT INTO events_users VALUES (13, 7);
INSERT INTO events_users VALUES (13, 8);
INSERT INTO events_users VALUES (13, 9);

INSERT INTO events_users VALUES (14, 2);
INSERT INTO events_users VALUES (14, 3);
INSERT INTO events_users VALUES (14, 4);
INSERT INTO events_users VALUES (14, 5);
INSERT INTO events_users VALUES (14, 6);
INSERT INTO events_users VALUES (14, 7);
INSERT INTO events_users VALUES (14, 8);
INSERT INTO events_users VALUES (14, 9);

INSERT INTO events_users VALUES (15, 2);
INSERT INTO events_users VALUES (15, 3);
INSERT INTO events_users VALUES (15, 4);
INSERT INTO events_users VALUES (15, 5);
INSERT INTO events_users VALUES (15, 6);
INSERT INTO events_users VALUES (15, 7);
INSERT INTO events_users VALUES (15, 8);
INSERT INTO events_users VALUES (15, 9);

INSERT INTO events_users VALUES (16, 2);
INSERT INTO events_users VALUES (16, 3);
INSERT INTO events_users VALUES (16, 4);
INSERT INTO events_users VALUES (16, 5);
INSERT INTO events_users VALUES (16, 6);
INSERT INTO events_users VALUES (16, 7);
INSERT INTO events_users VALUES (16, 8);
INSERT INTO events_users VALUES (16, 9);

INSERT INTO events_users VALUES (17, 2);
INSERT INTO events_users VALUES (17, 3);
INSERT INTO events_users VALUES (17, 4);
INSERT INTO events_users VALUES (17, 5);
INSERT INTO events_users VALUES (17, 6);
INSERT INTO events_users VALUES (17, 7);
INSERT INTO events_users VALUES (17, 8);
INSERT INTO events_users VALUES (17, 9);

INSERT INTO events_users VALUES (18, 2);
INSERT INTO events_users VALUES (18, 3);
INSERT INTO events_users VALUES (18, 4);
INSERT INTO events_users VALUES (18, 5);
INSERT INTO events_users VALUES (18, 6);
INSERT INTO events_users VALUES (18, 7);
INSERT INTO events_users VALUES (18, 8);
INSERT INTO events_users VALUES (18, 9);

INSERT INTO events_users VALUES (19, 2);
INSERT INTO events_users VALUES (19, 3);
INSERT INTO events_users VALUES (19, 4);
INSERT INTO events_users VALUES (19, 5);
INSERT INTO events_users VALUES (19, 6);
INSERT INTO events_users VALUES (19, 7);
INSERT INTO events_users VALUES (19, 8);
INSERT INTO events_users VALUES (19, 9);

INSERT INTO events_users VALUES (20, 2);
INSERT INTO events_users VALUES (20, 3);
INSERT INTO events_users VALUES (20, 4);
INSERT INTO events_users VALUES (20, 5);
INSERT INTO events_users VALUES (20, 6);
INSERT INTO events_users VALUES (20, 7);
INSERT INTO events_users VALUES (20, 8);
INSERT INTO events_users VALUES (20, 9);

INSERT INTO events_users VALUES (21, 2);
INSERT INTO events_users VALUES (21, 3);
INSERT INTO events_users VALUES (21, 4);
INSERT INTO events_users VALUES (21, 5);
INSERT INTO events_users VALUES (21, 6);
INSERT INTO events_users VALUES (21, 7);
INSERT INTO events_users VALUES (21, 8);
INSERT INTO events_users VALUES (21, 9);

INSERT INTO events_users VALUES (22, 2);
INSERT INTO events_users VALUES (22, 3);
INSERT INTO events_users VALUES (22, 4);
INSERT INTO events_users VALUES (22, 5);
INSERT INTO events_users VALUES (22, 6);
INSERT INTO events_users VALUES (22, 7);
INSERT INTO events_users VALUES (22, 8);
INSERT INTO events_users VALUES (22, 9);

INSERT INTO events_users VALUES (23, 2);
INSERT INTO events_users VALUES (23, 3);
INSERT INTO events_users VALUES (23, 4);
INSERT INTO events_users VALUES (23, 5);
INSERT INTO events_users VALUES (23, 6);
INSERT INTO events_users VALUES (23, 7);
INSERT INTO events_users VALUES (23, 8);
INSERT INTO events_users VALUES (23, 9);

INSERT INTO events_users VALUES (24, 2);
INSERT INTO events_users VALUES (24, 3);
INSERT INTO events_users VALUES (24, 4);
INSERT INTO events_users VALUES (24, 5);
INSERT INTO events_users VALUES (24, 6);
INSERT INTO events_users VALUES (24, 7);
INSERT INTO events_users VALUES (24, 8);
INSERT INTO events_users VALUES (24, 9);

INSERT INTO events_users VALUES (25, 2);
INSERT INTO events_users VALUES (25, 3);
INSERT INTO events_users VALUES (25, 4);
INSERT INTO events_users VALUES (25, 5);
INSERT INTO events_users VALUES (25, 6);
INSERT INTO events_users VALUES (25, 7);
INSERT INTO events_users VALUES (25, 8);
INSERT INTO events_users VALUES (25, 9);

INSERT INTO events_users VALUES (26, 2);
INSERT INTO events_users VALUES (26, 3);
INSERT INTO events_users VALUES (26, 4);
INSERT INTO events_users VALUES (26, 5);
INSERT INTO events_users VALUES (26, 6);
INSERT INTO events_users VALUES (26, 7);
INSERT INTO events_users VALUES (26, 8);
INSERT INTO events_users VALUES (26, 9);

--Insert tags
INSERT INTO tags (tag_name, tag_color) VALUES ('APPLICATION_MANAGEMENT', '#de681b');
INSERT INTO tags (tag_name, tag_color) VALUES ('DEVELOPMENT', '#de251b');
INSERT INTO tags (tag_name, tag_color) VALUES ('TESTING', '#6f1894');
INSERT INTO tags (tag_name, tag_color) VALUES ('TOWER', '#dea11b');
INSERT INTO tags (tag_name, tag_color) VALUES ('NBC', '#737b8a');
INSERT INTO tags (tag_name, tag_color) VALUES ('ALL_STAFF', '#213e96');

--Insert events-tags
INSERT INTO events_tags VALUES (1, 4);
INSERT INTO events_tags VALUES (2, 4);
INSERT INTO events_tags VALUES (3, 4);
INSERT INTO events_tags VALUES (4, 6);
INSERT INTO events_tags VALUES (5, 5);
INSERT INTO events_tags VALUES (6, 5);
INSERT INTO events_tags VALUES (7, 4);
INSERT INTO events_tags VALUES (8, 4);
INSERT INTO events_tags VALUES (9, 1);
INSERT INTO events_tags VALUES (10, 5);
INSERT INTO events_tags VALUES (11, 5);
INSERT INTO events_tags VALUES (12, 6);
INSERT INTO events_tags VALUES (13, 5);
INSERT INTO events_tags VALUES (14, 5);
INSERT INTO events_tags VALUES (15, 5);
INSERT INTO events_tags VALUES (16, 4);
INSERT INTO events_tags VALUES (17, 4);
INSERT INTO events_tags VALUES (18, 4);
INSERT INTO events_tags VALUES (19, 5);
INSERT INTO events_tags VALUES (20, 5);
INSERT INTO events_tags VALUES (21, 6);
INSERT INTO events_tags VALUES (22, 4);
INSERT INTO events_tags VALUES (23, 4);
INSERT INTO events_tags VALUES (24, 4);
INSERT INTO events_tags VALUES (25, 4);
INSERT INTO events_tags VALUES (26, 4);


--Persistent logins
CREATE TABLE persistent_logins (
  username  VARCHAR(255),
  series    VARCHAR(255),
  token     VARCHAR(255),
  last_used TIMESTAMP WITHOUT TIME ZONE,

  CONSTRAINT persistent_logins_pk PRIMARY KEY (series)
);