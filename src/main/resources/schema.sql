CREATE TABLE hotels (
  id   INTEGER NOT NULL AUTO_INCREMENT,
  hotel_name VARCHAR(60) NOT NULL,
  standard VARCHAR(6),
  description VARCHAR(128),
  PRIMARY KEY (id)
);