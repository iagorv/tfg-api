-- Crear base de datos
drop database if exists letterboxd_juegos;
CREATE DATABASE IF NOT EXISTS letterboxd_juegos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE letterboxd_juegos;
-- Tabla: Usuario
CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    fecha_alta DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
	fecha_nacimiento DATE NOT NULL,
    premium BOOLEAN NOT NULL DEFAULT FALSE
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

-- Tabla: Desarrollador
CREATE TABLE Desarrollador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: Juego (sin género directo, con fk a desarrollador)
CREATE TABLE Juego (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    desarrollador_id INT,
    anio_salida INT,
    FOREIGN KEY (desarrollador_id) REFERENCES Desarrollador(id) ON DELETE SET NULL
);

-- Relación N:N entre Juego y Genero
CREATE TABLE Juego_Genero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    juego_id BIGINT NOT NULL,
    genero_id BIGINT NOT NULL,
    UNIQUE (juego_id, genero_id),
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE,
    FOREIGN KEY (genero_id) REFERENCES Genero(id) ON DELETE CASCADE
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

-- Tabla: Review
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

-- Tabla: Estado del juego para cada usuario
CREATE TABLE Juego_Usuario_Estado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    juego_id BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL, -- 'pendiente', 'jugando', 'terminado', etc.
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
('Deportes'),
('Simulación'),
('Survival Horror'),
('Puzzle'),
('Carreras'),
('Metroidvania');


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
('Nintendo 3DS'),
('PlayStation Vita'),
('GameCube'),
('Android'),
('iOS'),
('Browser'),
('Sega Genesis'),
('Dreamcast');

INSERT INTO Desarrollador (nombre) VALUES
('FromSoftware'),
('Nintendo'),
('EA Sports'),
('343 Industries'),
('ConcernedApe'),
('Naughty Dog'),
('Santa Monica Studio'),
('Infinity Ward'),
('Intelligent Systems'),
('Capcom'),
('Polyphony Digital'),
('Team Cherry'),
('Monstars Inc. / Resonair'),
('Innersloth'),
('Vicarious Visions'),
('Christian Whitehead'),
('Game Freak'),
('Matt Makes Games'),
('Supergiant Games');


INSERT INTO Usuario (nombre, email, contraseña, fecha_nacimiento) VALUES ('JuanGamer', 'juan@gamer.com', '1234_hash_falso', '1989-05-09'),
('LauPlayer', 'laura@juegos.net', 'abcd_hash_falso', '2009-05-05'),
 ('LeoRetro', 'leo@retrofan.org', 'retro_hash_falso', '2002-10-18');


INSERT INTO Juego (nombre, descripcion, desarrollador_id, anio_salida) VALUES 
('Elden Ring', 'Un mundo abierto de fantasía oscura con combate desafiante.', 1, 2022),
('The Legend of Zelda: Tears of the Kingdom', 'Secuela directa de Breath of the Wild.', 2, 2023),
('FIFA 24', 'El simulador de fútbol más popular.', 3, 2023),
('Halo Infinite', 'Shooter en primera persona futurista.', 4, 2021),
('Stardew Valley', 'Un relajante juego de granja con elementos RPG.', 5, 2016),
('The Last of Us', 'Un viaje emocional en un mundo post-apocalíptico.', 6, 2013),
('God of War II', 'Kratos busca venganza contra los dioses del Olimpo.', 7, 2007),
('Super Mario Galaxy', 'Mario explora planetas en 3D con gravedad única.', 2, 2007),
('Call of Duty: Modern Warfare 2', 'Shooter bélico en escenarios realistas.', 8, 2009),
('Fire Emblem: Awakening', 'Juego de estrategia táctica con elementos RPG.', 9, 2012),
('Resident Evil 4', 'Survival horror con acción en tercera persona.', 10, 2005),
('Gran Turismo 7', 'Simulador de conducción con cientos de vehículos.', 11, 2022),
('Hollow Knight', 'Metroidvania en un mundo subterráneo dibujado a mano.', 12, 2017),
('Tetris Effect', 'Una versión moderna del clásico puzzle con música envolvente.', 13, 2018),
('Among Us', 'Juego multijugador de deducción social en el espacio.', 14, 2018),
('Crash Bandicoot N. Sane Trilogy', 'Remasterización de los tres primeros juegos clásicos de Crash.', 15, 2017),
('Sonic Mania', 'Un regreso a las raíces 2D del erizo azul con niveles clásicos y nuevos.', 16, 2017),
('Pokémon HeartGold', 'Remake mejorado del clásico Pokémon Gold con nuevas funciones.', 17, 2009),
('Celeste', 'Plataformas desafiante con narrativa emocional sobre superación personal.', 18, 2018),
('Hades', 'Roguelike isométrico con combate rápido y mitología griega.', 19, 2020);



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

-- Resident Evil 4
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (11, 1), (11, 10); -- Acción, Survival Horror

-- Gran Turismo 7
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (12, 11); -- Carreras

-- Hollow Knight
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (13, 1), (13, 3), (13, 11); -- Acción, RPG, Metroidvania

-- Tetris Effect
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (14, 9); -- Puzzle

-- Among Us
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (15, 4), (15, 7); -- Estrategia, Simulación
-- Crash Bandicoot
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (16, 1), (16, 2); -- Acción, Aventura

-- Sonic Mania
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (17, 1), (17, 2); -- Acción, Aventura

-- Pokémon HeartGold
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (18, 3), (18, 4); -- RPG, Estrategia

-- Celeste
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (19, 1), (19, 9); -- Acción, Puzzle

-- Hades
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (20, 1), (20, 3); -- Acción, RPG





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


-- Resident Evil 4
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(11, 8),  -- PS2
(11, 10), -- Xbox 360
(11, 11), -- Wii
(11, 15); -- GameCube

-- Gran Turismo 7
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(12, 2), -- PS5
(12, 6); -- PS4

-- Hollow Knight
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(13, 1), -- PC
(13, 2), -- PS5
(13, 4), -- Switch
(13, 14); -- PS Vita

-- Tetris Effect
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(14, 1), -- PC
(14, 6); -- PS4

-- Among Us
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(15, 1),  -- PC
(15, 17), -- Android
(15, 18), -- iOS
(15, 19); -- Browser

-- Crash Bandicoot N. Sane Trilogy
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(16, 1), -- PC
(16, 2), -- PS5
(16, 4); -- Switch

-- Sonic Mania
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(17, 1), -- PC
(17, 2), -- PS5
(17, 4); -- Switch

-- Pokémon HeartGold
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(18, 13); -- Nintendo DS

-- Celeste
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(19, 1), -- PC
(19, 2), -- PS5
(19, 4); -- Switch

-- Hades
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(20, 1), -- PC
(20, 2), -- PS5
(20, 4); -- Switch




-- JuanGamer reviewea Elden Ring 2 veces
INSERT INTO Review (juego_id, usuario_id, reseña, nota, fecha_review) VALUES
(1, 1, 'Una obra maestra. El diseño del mundo es increíble.', 10, '2024-01-05 14:30:00'),
(1, 1, 'Volví a jugarlo y sigo encontrando secretos. Impresionante.', 10, '2024-03-12 18:45:00');

-- LauPlayer comenta Zelda
INSERT INTO Review (juego_id, usuario_id, reseña, nota) VALUES
(2, 2, 'Mejor que BOTW. La exploración es brutal.', 9);

-- LeoRetro en Stardew
INSERT INTO Review (juego_id, usuario_id, reseña, nota) VALUES
(5, 3, 'Mi juego de cabecera. Pura paz.', 10),
(18,3,'Me encanta, sin duda el mejor juego de la saga',9);


INSERT INTO Juego_Usuario_Estado (usuario_id, juego_id, estado) VALUES
(1, 1, 'terminado'),
(1, 2, 'pendiente'),
(2, 2, 'jugando'),
(3, 5, 'favorito'),
(2, 3, 'pendiente');


