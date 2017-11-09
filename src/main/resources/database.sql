CREATE TABLE users (
  id        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username  VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  firstname VARCHAR(255),
  lastname  VARCHAR(255),
  email     VARCHAR(255),
  subscription_by_event_type VARCHAR(255),
  subscription_by_tag_type VARCHAR(255)
  )

ENGINE = InnoDB;

CREATE TABLE  roles(
  id    INT NOT NULL  AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  UNIQUE (user_id,role_id)
)

ENGINE = InnoDB;

CREATE TABLE events
(
  id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  event_name     VARCHAR(255) NOT NULL,
  event_type     VARCHAR(255),
  author_user_id INT,
  createdata     DATETIME,
  timebegin      DATETIME,
  timeend        DATETIME,
  event_location VARCHAR(255),
  description    VARCHAR(1000),

  FOREIGN KEY (author_user_id) REFERENCES users (id) ON DELETE CASCADE
)
  ENGINE = InnoDB;

CREATE TABLE events_users
(
  event_id  INT NOT NULL,
  user_id   INT,

  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
)
  ENGINE = InnoDB;

CREATE TABLE tags
(
  id        INT NOT NULL PRIMARY KEY,
  tag_name  VARCHAR(255) NOT NULL,
  tag_color VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE events_tags (
  event_id INT NOT NULL,
  tag_id   INT,

  FOREIGN KEY (tag_id) REFERENCES tags (id),
  FOREIGN KEY (event_id) REFERENCES events (id)
)
  ENGINE = InnoDB;

CREATE TABLE persistent_logins (
  username  VARCHAR(255),
  series    VARCHAR(255),
  token     VARCHAR(255),
  last_used DATETIME,

  CONSTRAINT persistent_logins_pk PRIMARY KEY (series)
)
  ENGINE = InnoDB;

INSERT INTO roles values (1, 'ROLE_SUPREME_ADMIN');
INSERT INTO roles values (2, 'ROLE_ADMIN');
INSERT INTO roles values (3, 'ROLE_USER');
INSERT INTO roles values (4, 'ROLE_GUEST');
