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

--Inserts for testing
-- if there are some problems with inserts - correct them
INSERT INTO users
    VALUES (99, 'UserTest', 'Qawsed', 'Qawsed', 'adamaa14@gmail.com', '$2a$11$TDrIdfhId/ON7V0han8Fa.tS7eBdJ6LooYNQPnBU8CM3Jgcf7q2UG');

INSERT INTO user_roles VALUES(99,1);