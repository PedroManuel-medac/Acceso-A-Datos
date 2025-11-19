DROP DATABASE  IF EXISTS TiendaVideojuegos;
CREATE DATABASE TiendaVideojuegos;
USE TiendaVideojuegos;

-- Tabla de usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    pais VARCHAR(50),
    fecha_registro DATE
);

-- Tabla de plataformas
CREATE TABLE Plataformas (
    id_plataforma INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    compania VARCHAR(50)
);

-- Tabla de juegos
CREATE TABLE Juegos (
    id_juego INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    genero VARCHAR(50),
    precio DECIMAL(6,2),
    id_plataforma INT,
    FOREIGN KEY (id_plataforma) REFERENCES Plataformas(id_plataforma)
);

-- Tabla de compras
CREATE TABLE Compras (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_juego INT,
    fecha_compra DATE,
    metodo_pago VARCHAR(30),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES Juegos(id_juego)
);

-- Tabla de reseñas
CREATE TABLE Reseñas (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_juego INT,
    puntuacion INT CHECK (puntuacion BETWEEN 1 AND 10),
    comentario TEXT,
    fecha_resena DATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES Juegos(id_juego)
);

INSERT INTO Usuarios (nombre, email, pais, fecha_registro) VALUES
('Ana Torres', 'ana@email.com', 'España', '2023-05-10'),
('Carlos Ruiz', 'carlos@email.com', 'México', '2022-12-01'),
('Lucía Gómez', 'lucia@email.com', 'Argentina', '2023-01-15');

INSERT INTO Plataformas (nombre, compania) VALUES
('Steam', 'Valve'),
('Epic Games', 'Epic'),
('PlayStation Store', 'Sony');

INSERT INTO Juegos (titulo, genero, precio, id_plataforma) VALUES
('Elden Ring', 'RPG', 59.99, 1),
('Fortnite', 'Battle Royale', 0.00, 2),
('The Last of Us', 'Acción', 49.99, 3),
('Hades', 'Roguelike', 19.99, 1);

INSERT INTO Compras (id_usuario, id_juego, fecha_compra, metodo_pago) VALUES
(1, 1, '2023-06-10', 'Tarjeta'),
(2, 2, '2023-07-01', 'PayPal'),
(3, 4, '2023-08-15', 'Tarjeta'),
(1, 3, '2023-09-20', 'PayPal');

INSERT INTO Reseñas (id_usuario, id_juego, puntuacion, comentario, fecha_resena) VALUES
(1, 1, 9, 'Increíble juego, mundo enorme.', '2023-06-15'),
(2, 2, 7, 'Divertido pero repetitivo.', '2023-07-05'),
(3, 4, 10, 'Perfecto en todo.', '2023-08-20');


												-- REPASO --

-- Mostrar todos los juegos disponibles.
SELECT * FROM juegos;

-- Ver los usuarios registrados en España.
SELECT * FROM usuarios WHERE pais = 'España';

-- Mostrar los juegos gratuitos (precio = 0).
SELECT * FROM juegos WHERE precio = 0;

-- Contar cuántos usuarios hay en total.
SELECT COUNT(id_usuario) FROM usuarios;

-- Mostrar los juegos de género "RPG".
SELECT * FROM juegos WHERE genero = 'RPG';

-- Mostrar las compras realizadas por el usuario "Ana Torres".
SELECT compras.* FROM compras
INNER JOIN usuarios ON compras.id_usuario = usuarios.id_usuario
WHERE usuarios.nombre = 'Ana Torres';

-- Listar los juegos con su nombre de plataforma
SELECT juegos.*, plataformas.nombre FROM juegos
INNER JOIN plataformas ON juegos.id_plataforma = plataformas.id_plataforma;

-- Mostrar los usuarios que han hecho al menos una compra.
SELECT usuarios.nombre, COUNT(compras.id_usuario) FROM usuarios
INNER JOIN compras ON usuarios.id_usuario = compras.id_usuario
GROUP BY usuarios.nombre;

-- Calcular el precio medio de todos los juegos.
SELECT AVG(precio) FROM juegos;

-- Ver los juegos y su puntuación media en las reseñas.
SELECT juegos.titulo, AVG(reseñas.puntuacion) FROM juegos
INNER JOIN reseñas ON juegos.id_juego = reseñas.id_juego
GROUP BY juegos.titulo;

-- Ver qué plataforma tiene más juegos.
SELECT plataformas.nombre, COUNT(juegos.id_plataforma) AS NUM_JUEGOS FROM plataformas
INNER JOIN juegos ON plataformas.id_plataforma = juegos.id_plataforma
GROUP BY plataformas.nombre
HAVING COUNT(juegos.id_plataforma) >= 2
ORDER BY NUM_JUEGOS ;


