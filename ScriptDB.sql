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

ALTER TABLE Juego DROP FOREIGN KEY juego_ibfk_1; -- o el nombre de la FK
ALTER TABLE Juego DROP COLUMN genero_id;

CREATE TABLE Juego_Genero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    juego_id BIGINT NOT NULL,
    genero_id BIGINT NOT NULL,
    UNIQUE (juego_id, genero_id),
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE,
    FOREIGN KEY (genero_id) REFERENCES Genero(id) ON DELETE CASCADE
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
('Steam Deck'),
('PlayStation 4'),
('PlayStation 3'),
('PlayStation 2'),
('Xbox One'),
('Xbox 360'),
('Wii'),
('Wii U'),
('Nintendo DS'),
('Nintendo 3DS');


INSERT INTO Usuario (nombre, email, contraseña) VALUES 
('JuanGamer', 'juan@gamer.com', '1234_hash_falso'),
('LauPlayer', 'laura@juegos.net', 'abcd_hash_falso'),
('LeoRetro', 'leo@retrofan.org', 'retro_hash_falso');

INSERT INTO Juego (nombre, descripcion, desarrollador, anio_salida) VALUES 
('Elden Ring', 'Un mundo abierto de fantasía oscura con combate desafiante.', 'FromSoftware', 2022),
('The Legend of Zelda: Tears of the Kingdom', 'Secuela directa de Breath of the Wild.', 'Nintendo',  2023),
('FIFA 24', 'El simulador de fútbol más popular.', 'EA Sports', 2023),
('Halo Infinite', 'Shooter en primera persona futurista.', '343 Industries',2021),
('Stardew Valley', 'Un relajante juego de granja con elementos RPG.', 'ConcernedApe', 2016),
('The Last of Us', 'Un viaje emocional en un mundo post-apocalíptico.', 'Naughty Dog',  2013),
('God of War II', 'Kratos busca venganza contra los dioses del Olimpo.', 'Santa Monica Studio', 2007),
('Super Mario Galaxy', 'Mario explora planetas en 3D con gravedad única.', 'Nintendo',  2007),
('Call of Duty: Modern Warfare 2', 'Shooter bélico en escenarios realistas.', 'Infinity Ward',2009),
('Fire Emblem: Awakening', 'Juego de estrategia táctica con elementos RPG.', 'Intelligent Systems',  2012);

-- Elden Ring
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (1, 1), (1, 3); -- Acción, RPG

-- Zelda TOTK
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (2, 2), (2, 3); -- Aventura, RPG

-- FIFA 24
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (3, 6); -- Deportes

-- Halo Infinite
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (4, 5), (4, 1); -- Shooter, Acción

-- Stardew Valley
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (5, 3), (5, 4); -- RPG, Estrategia

-- The Last of Us
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (6, 1), (6, 2); -- Acción, Aventura

-- God of War II
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (7, 1), (7, 2); -- Acción, Aventura

-- Super Mario Galaxy
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (8, 2), (8, 1); -- Aventura, Acción

-- CoD MW2
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (9, 5); -- Shooter

-- Fire Emblem: Awakening
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (10, 4), (10, 3); -- Estrategia, RPG


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
(3, 1), (3, 2), (3, 3),(3, 4);

-- Halo Infinite
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(4, 1), (4, 3);

-- Stardew Valley
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(5, 1), (5, 2), (5, 4), (5, 5);

-- The Last of Us
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(6, 6), -- PS4
(6, 7); -- PS3

-- God of War II
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(7, 8); -- PS2

-- Super Mario Galaxy
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(8, 11); -- Wii

-- Modern Warfare 2
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(9, 9), -- Xbox 360
(9, 7); -- PS3

-- Fire Emblem: Awakening
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(10, 13); -- Nintendo 3DS


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


