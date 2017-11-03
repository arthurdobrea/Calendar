CREATE TABLE users (
  id       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstname VARCHAR(255),
  lastname  VARCHAR(255),
  email     VARCHAR(255),
  password  VARCHAR(255)
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

CREATE TABLE events
(
  id             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  event_name     VARCHAR(255) NOT NULL,
  event_type     VARCHAR(255),
  author_user_id INT,
  event_location VARCHAR(255),
  timebegin      DATETIME,
  timeend        DATETIME,
  createdata     DATETIME,
  description    VARCHAR(1000),

  FOREIGN KEY (author_user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

CREATE TABLE events_users
(
  event_id BIGINT NOT NULL,
  user_id INT,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (event_id) REFERENCES events(id)
)
  ENGINE = InnoDB;

ALTER TABLE `spring_security_app`.`users`
ADD COLUMN `labels` VARCHAR(255) NULL AFTER `lastName`;