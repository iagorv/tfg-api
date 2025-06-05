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
    año_salida INT,
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
		nota double CHECK (nota BETWEEN 0 AND 10),
		fecha_review DATE  DEFAULT (CURDATE()),

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

CREATE TABLE Bitacora_Juego (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    juego_id BIGINT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    entrada TEXT NOT NULL,                         -- descripción libre
    horas_jugadas DECIMAL(5,2),                    -- ej: 1.5 horas
      

    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (juego_id) REFERENCES Juego(id) ON DELETE CASCADE
);

INSERT INTO Usuario (nombre, email, contraseña, fecha_nacimiento) VALUES ('JuanGamer', 'juan@gamer.com', '1234_hash_falso', '1989-05-09'),
('LauPlayer', 'laura@juegos.net', 'abcd_hash_falso', '2009-05-05'),
 ('LeoRetro', 'leo@retrofan.org', 'retro_hash_falso', '2002-10-18');


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
('Supergiant Games'),
('Askiisoft');





INSERT INTO Juego (nombre, descripcion, desarrollador_id, año_salida) VALUES 
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
('Tetris Effect', 'Una versión moderna del clásico puzzle con música envolvente.', 13, 2018), /*Cambiar  */
('Among Us', 'Juego multijugador de deducción social en el espacio.', 14, 2018),
('Crash Bandicoot N. Sane Trilogy', 'Remasterización de los tres primeros juegos clásicos de Crash.', 15, 2017),
('Sonic Mania', 'Un regreso a las raíces 2D del erizo azul con niveles clásicos y nuevos.', 16, 2017),
('Pokémon HeartGold', 'Remake mejorado del clásico Pokémon Gold con nuevas funciones.', 17, 2009),
('Celeste', 'Plataformas desafiante con narrativa emocional sobre superación personal.', 18, 2018),
('Hades', 'Roguelike isométrico con combate rápido y mitología griega.', 19, 2020),
('God of War Ragnarok', 'La épica conclusión de la saga de Kratos y Atreus.', 7, 2022),
('Metroid Dread', 'La nueva aventura de Samus en una exploración 2D intensa.', 2, 2021),
('Katana Zero', 'Juego de acción y plataformas con una narrativa noir y combate rápido.', 20, 2019),
('Animal Crossing: New Horizons', 'Simulador social y de vida en una isla paradisíaca.', 2, 2020),
('Cyberpunk 2077', 'RPG futurista en un mundo abierto distópico.', 13, 2020),
('Super Smash Bros. Ultimate', 'Juego de lucha con personajes de múltiples franquicias.', 2, 2018),
('The Witcher 3: Wild Hunt', 'RPG de mundo abierto con una historia profunda.', 20, 2015),
('Minecraft', 'Sandbox creativo y de supervivencia con bloques.', 5, 2011),
('Inside', 'Juego de plataformas y puzles con atmósfera oscura y narrativa implícita.', 6, 2016),
('Dark Souls III', 'RPG de acción desafiante con mundo oscuro y misterioso.', 1, 2016),
('Animal Crossing: New Leaf', 'Simulador social con muchas personalizaciones.', 2, 2013),
('The Legend of Zelda: Breath of the Wild', 'Revolucionario juego de aventura en mundo abierto.', 2, 2017),
('Overwatch', 'Shooter en equipo con héroes únicos.', 8, 2016),
('Forza Horizon 5', 'Juego de carreras en mundo abierto en México.', 11, 2021),
('Persona 5 Royal', 'RPG japonés con narrativa profunda y estilo único.', 19, 2020),
('It Takes Two', 'Aventura cooperativa con mecánicas únicas para dos jugadores.', 6, 2021),
('Dead Space', 'Survival horror en una estación espacial.', 10, 2008),
('The Sims 4', 'Simulación de vida con muchas opciones de personalización.', 3, 2014),
('Mario Kart 8 Deluxe', 'Carreras de karts con personajes icónicos de Nintendo.', 2, 2017),
('Hollow Knight: Silksong', 'Secuela de Hollow Knight con nuevo protagonista.', 12, 2025);







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


-- 21 God of War Ragnarok
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (21, 1), (21, 2);
-- 22 Metroid Dread
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (22, 1), (22, 11);
-- 23 Katana zero
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (23, 1), (23, 9); -- Acción, Puzzle
-- 24 Animal Crossing: New Horizons
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (24, 7);
-- 25 Cyberpunk 2077
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (25, 1), (25, 3);
-- 26 Super Smash Bros. Ultimate
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (26, 1);
-- 27 The Witcher 3: Wild Hunt
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (27, 3), (27, 2);
-- 28 Minecraft
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (28, 7), (28, 3);
-- 29 Inside
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (29, 1), (29, 9);

-- 30 Dark Souls III
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (30, 1), (30, 3);
-- 31 Animal Crossing: New Leaf
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (31, 7);
-- 32 Zelda Breath of the Wild
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (32, 2), (32, 3);
-- 33 Overwatch
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (33, 5);
-- 34 Forza Horizon 5
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (34, 11);
-- 35 Persona 5 Royal
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (35, 3), (35, 2);
-- 36 Celeste (repetido)
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (36, 2), (36, 4);
-- 37 Dead Space
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (37, 1), (37, 8);
-- 38 The Sims 4
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (38, 7);
-- 39 Mario Kart 8 Deluxe
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (39, 11);
-- 40 Hollow Knight: Silksong
INSERT INTO Juego_Genero (juego_id, genero_id) VALUES (40, 1), (40, 3), (40, 11);


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

-- 21 God of War Ragnarok
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (21, 2), (21, 6);
-- 22 Metroid Dread
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (22, 4);
-- 23 Katana zero
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(23, 1), -- PC
(23, 2), -- PS5
(23, 4); -- Nintendo Switch
-- 24 Animal Crossing: New Horizons
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (24, 4);
-- 25 Cyberpunk 2077
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (25, 1), (25, 2), (25, 3);
-- 26 Super Smash Bros. Ultimate
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (26, 4);
-- 27 The Witcher 3: Wild Hunt
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (27, 1), (27, 2), (27, 3);
-- 28 Minecraft
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (28, 1), (28, 4), (28, 16), (28, 17);
-- Inside
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(29, 1), -- PC
(29, 2), -- PS5
(29, 4); -- Switch
-- 30 Dark Souls III
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (30, 1), (30, 2), (30, 6);
-- 31 Animal Crossing: New Leaf
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (31, 13);
-- 32 Zelda Breath of the Wild
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (32, 4), (32, 15);
-- 33 Overwatch
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (33, 1), (33, 2), (33, 3);
-- 34 Forza Horizon 5
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (34, 1), (34, 3), (34, 9);
-- 35 Persona 5 Royal
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (35, 2), (35, 6);

-- It Takes Two
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES 
(36, 1), -- PC
(36, 2), -- PS5
(36, 3); -- Xbox Series X

-- 37 Dead Space
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (37, 6), (37, 7);
-- 38 The Sims 4
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (38, 1), (38, 2), (38, 3);
-- 39 Mario Kart 8 Deluxe
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (39, 4), (39, 11);
-- 40 Hollow Knight: Silksong
INSERT INTO Juego_Plataforma (juego_id, plataforma_id) VALUES (40, 1), (40, 4);


-- JuanGamer reviewea Elden Ring 2 veces
INSERT INTO Review (juego_id, usuario_id, reseña, nota, fecha_review) VALUES
(1, 1, 'Una obra maestra. El diseño del mundo es increíble.', 10, '2024-01-05 14:30:00'),
(1, 1, 'Volví a jugarlo y sigo encontrando secretos. Impresionante.', 10, '2024-03-12 18:45:00');

-- LauPlayer comenta Zelda
INSERT INTO Review (juego_id, usuario_id, reseña, nota) VALUES
(2, 2, 'Mejor que BOTW. La exploración es brutal.', 9.5);

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

INSERT INTO Usuario (nombre, email, contraseña, fecha_nacimiento) VALUES
('AnaPixel', 'ana@pixellove.com', 'pass_hash_falso1', '1995-08-12'),
('GamerX99', 'gamerx99@hotspot.com', 'hash_g99', '1987-11-03'),
('RetroQueen', 'retroqueen@oldschool.net', 'oldschool_hash', '1990-04-21'),
('SwitchMaster', 'switch@hybrids.com', 'ninty_hash', '2000-06-22'),
('IndieFan87', 'indiefan87@dreammail.org', 'indie_hash', '1985-02-14');

-- AnaPixel reviews
INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(4, 1, 'Un mundo brutalmente hermoso. Difícil pero adictivo.', 9.5, '2023-03-22'),
(4, 2, 'Zelda nunca decepciona. Gran secuela.', 9.8, '2023-06-15'),
(4, 24, 'Ideal para relajarse. Me encantan mis vecinos.', 8.5, '2024-01-10');

-- GamerX99 reviews
INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(5, 9, 'El mejor Call of Duty de la saga moderna.', 9.0, '2020-09-18'),
(5, 25, 'Mucho mejor tras los parches, gran historia y ambiente.', 8.0, '2024-05-02'),
(5, 20, 'Acción intensa, personajes geniales. Una joya indie.', 9.4, '2022-10-10');

-- RetroQueen reviews
INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(6, 8, 'Una obra maestra de Nintendo. Gravedad espectacular.', 10.0, '2021-11-03'),
(6, 17, 'Gran homenaje a los clásicos. Sonic está de vuelta.', 8.8, '2020-07-12'),
(6, 11, 'Una joya de survival horror. Sigue dando miedo.', 9.3, '2019-10-28');

-- SwitchMaster reviews
INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(7, 4, 'Shooter sólido, pero esperaba más del modo historia.', 7.5, '2022-04-06'),
(7, 22, 'Exploración tensa y fluida. Samus brilla.', 9.0, '2023-01-19'),
(7, 39, 'Carreras muy divertidas con amigos. Siempre vuelve.', 9.1, '2024-04-05');

-- IndieFan87 reviews
INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(8, 13, 'Arte precioso y combate desafiante. Obra de culto.', 9.7, '2021-12-07'),
(8, 19, 'Increíble. Historia conmovedora y controles precisos.', 9.6, '2022-02-13'),
(8, 36, 'Rejugable al 100%. Celeste es un 10 emocional.', 10.0, '2023-09-23');

INSERT INTO Review (usuario_id, juego_id, reseña, nota, fecha_review) VALUES
(2, 16, 'Directo a la infancia', 10, '2025-05-23'),
(4, 16, 'Directo a la infancia', 8.6, '2025-05-23'),
(3, 16, 'Directo a la infancia', 8.25, '2025-01-23'),
(4, 16, 'Directo a la infancia', 5, '2025-05-07'),
(4, 16, 'Directo a la infancia', 6, '2025-05-23'),
(6, 16, 'Directo a la infancia', 7.5, '2025-05-23'),
(6, 16, 'Directo a la infancia', 6.37, '2025-05-23'),
(1, 16, 'Directo a la infancia', 8.5, '2025-05-23');




