DROP TABLE IF EXISTS user;

CREATE TABLE user (
   username varchar(255),
   avatar_image_path varchar(255),
   email varchar(255) NOT NULL,
   enabled tinyint(1) DEFAULT '0',
   password varchar(255) NOT NULL,
   PRIMARY KEY (username)
 );

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  name varchar(255),
  PRIMARY KEY (name)
);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
  username varchar(255) ,
  role_name varchar(255) NOT NULL,
  PRIMARY KEY (username,role_name),
  FOREIGN KEY (role_name) REFERENCES role(name),
  FOREIGN KEY (username) REFERENCES user(username)
);

DROP TABLE IF EXISTS verification_token;

CREATE TABLE verification_token (
  id bigint(20) AUTO_INCREMENT,
  expire_date datetime(6),
  value varchar(255),
  username varchar(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (username) REFERENCES user(username)
);

DROP TABLE IF EXISTS category;

CREATE TABLE category (
  name varchar(255),
  description varchar(1000),
  image_path varchar(255),
  PRIMARY KEY (name)
);

DROP TABLE IF EXISTS hero;

CREATE TABLE hero (
  name varchar(255),
  avatar_image_path varchar(255),
  rate bigint(20),
  PRIMARY KEY (name)
);

DROP TABLE IF EXISTS hero_category;

CREATE TABLE hero_category (
  hero_name varchar(255) NOT NULL,
  category_name varchar(255) NOT NULL,
  PRIMARY KEY (hero_name,category_name),
  FOREIGN KEY (hero_name) REFERENCES hero(name),
  FOREIGN KEY (category_name) REFERENCES category(name)
);

DROP TABLE IF EXISTS hero_rate;

CREATE TABLE hero_rate (
  hero_id varchar(255) NOT NULL,
  user_id varchar(255) NOT NULL,
  rate int(11) NOT NULL,
  PRIMARY KEY (hero_id,user_id),
  FOREIGN KEY (user_id) REFERENCES user(username),
  FOREIGN KEY (hero_id) REFERENCES hero(name)
);

DROP TABLE IF EXISTS article;

CREATE TABLE article (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  created_at bigint(20) NOT NULL,
  text varchar(255) NOT NULL,
  title varchar(255) NOT NULL UNIQUE,
  hero_full_name varchar(255),
  username varchar(255),
  PRIMARY KEY (id),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (hero_full_name) REFERENCES hero(name)
);

DROP TABLE IF EXISTS article_comment;

CREATE TABLE article_comment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  content varchar(255) NOT NULL,
  created_at bigint(20) NOT NULL,
  article_id bigint(20) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (article_id) REFERENCES article(id)
);

DROP TABLE IF EXISTS favourite_user_article;

CREATE TABLE favourite_user_article (
  article_id bigint(20) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (article_id,username),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (article_id) REFERENCES article(id)
);

DROP TABLE IF EXISTS favourite_user_hero;

CREATE TABLE favourite_user_hero (
  hero_name varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (hero_name,username),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (hero_name) REFERENCES hero(name)
);

DROP TABLE IF EXISTS quote;

CREATE TABLE quote (
  id bigint(20) AUTO_INCREMENT,
  content varchar(255) NOT NULL,
  hero_name varchar(255),
  username varchar(255),
  PRIMARY KEY (id),
  FOREIGN KEY (hero_name) REFERENCES hero(name),
  FOREIGN KEY (username) REFERENCES user(username)
);

DROP TABLE IF EXISTS favourite_user_quote;

CREATE TABLE favourite_user_quote (
  quote_id bigint(20) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (quote_id,username),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (quote_id) REFERENCES quote(id)
);


