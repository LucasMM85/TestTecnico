DROP TABLE IF EXISTS superheroes;

CREATE TABLE superheroes (id INT AUTO_INCREMENT  PRIMARY KEY,
                          nombre VARCHAR(250) NOT NULL);

INSERT INTO superheroes (nombre) VALUES
('Superman'),
('Batman'),
('Spider Man'),
('Captain Marvel'),
('Ghost Rider'),
('Manolito el fuerte');