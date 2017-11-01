-- User creating
CREATE TABLE users (
  id  BIGSERIAL NOT NULL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) ,
  lastname VARCHAR(255) ,
  firstname VARCHAR(255)
);
-- Roles creating
CREATE TABLE  roles(
  id BIGSERIAL NOT NULL  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE  user_roles(
  user_id int not NULL,
  role_id int not null,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),

  UNIQUE (user_id,role_id)
);
-- events creating
CREATE TABLE events  (
  id  BIGSERIAL NOT NULL PRIMARY KEY,
  event_name VARCHAR(255) NOT NULL,
  event_type VARCHAR(255),
  author_user_id int,
  createdata timestamp,
  timebegin timestamp,
  timeend timestamp ,
  event_location VARCHAR(255) ,
  description VARCHAR(1000),
  FOREIGN KEY (author_user_id ) REFERENCES users(id)
);

CREATE TABLE events_users  (
  event_id INT NOT NULL,
  user_id int,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (event_id) REFERENCES events(id)
);

-- tags creating
CREATE TABLE tags  (
  id  BIGSERIAL NOT NULL PRIMARY KEY,
  tag_name VARCHAR(255) NOT NULL
);

CREATE TABLE events_tags (
  event_id SERIAL NOT NULL,
  tag_id SERIAL,
  FOREIGN KEY (tag_id) REFERENCES tags(id),
  FOREIGN KEY (event_id) REFERENCES events(id)
);


-- User inserting

INSERT INTO users (username, password, email, lastname, firstname)
VALUES ( 'admin','$2a$11$28XVU8iIIPW19FKs/A579Og41R.vxvSrT.vi7gnfyXJoV7lppQEI6',
         'email', 'lastname', 'firstname');

INSERT into roles VALUES (1,'ROLE_USER');
INSERT into roles VALUES (2,'ROLE_ADMIN');

INSERT INTO user_roles VALUES(1,2);

-- events inserting

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('pianka', 'OFFLINE', 1, '2017-10-26 10:30:00+02',
        '2017-10-27 12:30:00+02', '2017-10-27 13:30:00+02',
  'Moldova', 'ohohoh!!! '
);

INSERT INTO events (event_name, event_type, author_user_id, createdata,
                    timebegin, timeend, event_location, description)
VALUES ('Birthday', 'TRAINING', 2, '2017-10-27 10:30:00+02',
        '2017-10-28 11:30:00+02', '2017-10-28 15:30:00+02',
        'Restaurant', 'Good mood'
);


INSERT into events_users  VALUES (1,1);
INSERT into events_users VALUES (1,2);
INSERT into events_users VALUES (2,1);
INSERT into events_users VALUES (2,2);

INSERT INTO tags (tag_name ) VALUES ( 'Java stream');
INSERT INTO tags (tag_name ) VALUES ('All Staff');
INSERT INTO tags (tag_name ) VALUES ('Hello new Endavas');
INSERT INTO tags (tag_name ) VALUES ('AM Internship');

INSERT into events_tags VALUES (1,1);
INSERT into events_tags VALUES (1,2);
INSERT into events_tags VALUES (1,3);
INSERT into events_tags VALUES (2,4);
INSERT into events_tags VALUES (2,3);
INSERT into events_tags VALUES (2,1);

drop TABLE tags, events_tags;
