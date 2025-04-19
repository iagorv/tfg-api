-- Crear base de datos
drop database if exists letterboxd_juegos;
CREATE DATABASE IF NOT EXISTS letterboxd_juegos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE letterboxd_juegos;

-- Tabla: Usuario
CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL, -- guardada como hash
    fecha_alta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla: Genero
CREATE TABLE Genero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla: Plataforma
CREATE TABLE Plataforma (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla: Juego
CREATE TABLE Juego (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    desarrollador VARCHAR(100),
    genero_id BIGINT,
    anio_salida INT,
    FOREIGN KEY (genero_id) REFERENCES Genero(id) ON DELETE SET NULL
);

-- Relación N:N entre Juego y Plataforma
CREATE TABLE Juego_Plataforma (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    juego_id BIGINT NOT NULL,
    plataforma_id BIGINT NOT NULL,
    UNIQUE (juego_id, plataforma_id),
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE,
    FOREIGN KEY (plataforma_id) REFERENCES Plataforma(id) ON DELETE CASCADE
);

-- Tabla: Review (ahora permite múltiples por usuario y juego)
CREATE TABLE Review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    juego_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    reseña TEXT,
    nota INT CHECK (nota BETWEEN 0 AND 10),
    fecha_review TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Tabla: Estado de juego por usuario
CREATE TABLE Juego_Usuario_Estado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    juego_id BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL, -- ejemplo: 'pendiente', 'jugando', 'terminado', 'favorito'
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (usuario_id, juego_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE
);



INSERT INTO Genero (nombre) VALUES 
('Acción'),
('Aventura'),
('RPG'),
('Estrategia'),
('Shooter'),
('Deportes');


INSERT INTO Plataforma (nombre) VALUES 
('PC'),
('PlayStation 5'),
('Xbox Series X'),
('Nintendo Switch'),
('Steam Deck');


INSERT INTO Usuario (nombre, email, contraseña) VALUES 
('JuanGamer', 'juan@gamer.com', '1234_hash_falso'),
('LauPlayer', 'laura@juegos.net', 'abcd_hash_falso'),
('LeoRetro', 'leo@retrofan.org', 'retro_hash_falso');

INSERT INTO Juego (nombre, descripcion, desarrollador, genero_id, anio_salida) VALUES 
('Elden Ring', 'Un mundo abierto de fantasía oscura con combate desafiante.', 'FromSoftware', 3, 2022),
('The Legend of Zelda: Tears of the Kingdom', 'Secuela directa de Breath of the Wild.', 'Nintendo', 2, 2023),
('FIFA 24', 'El simulador de fútbol más popular.', 'EA Sports', 6, 2023),
('Halo Infinite', 'Shooter en primera persona futurista.', '343 Industries', 5, 2021),
('Stardew Valley', 'Un relajante juego de granja con elementos RPG.', 'ConcernedApe', 3, 2016);


-- Elden Ring
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(1, 1), -- PC
(1, 2), -- PS5
(1, 3); -- Xbox

-- Zelda TOTK
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(2, 4); -- Nintendo Switch

-- FIFA 24
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(3, 1), (3, 2), (3, 3);

-- Halo Infinite
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(4, 1), (4, 3);

-- Stardew Valley
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(5, 1), (5, 2), (5, 4), (5, 5);


-- JuanGamer reviewea Elden Ring 2 veces
INSERT INTO Review (juego_id, usuario_id, reseña, nota, fecha_review) VALUES
(1, 1, 'Una obra maestra. El diseño del mundo es increíble.', 10, '2024-01-05 14:30:00'),
(1, 1, 'Volví a jugarlo y sigo encontrando secretos. Impresionante.', 10, '2024-03-12 18:45:00');

-- LauPlayer comenta Zelda
INSERT INTO Review (juego_id, usuario_id, reseña, nota) VALUES
(2, 2, 'Mejor que BOTW. La exploración es brutal.', 9);

-- LeoRetro en Stardew
INSERT INTO Review (juego_id, usuario_id, reseña, nota) VALUES
(5, 3, 'Mi juego de cabecera. Pura paz.', 10);


INSERT INTO Juego_Usuario_Estado (usuario_id, juego_id, estado) VALUES
(1, 1, 'terminado'),
(1, 2, 'pendiente'),
(2, 2, 'jugando'),
(3, 5, 'favorito'),
(2, 3, 'pendiente');


