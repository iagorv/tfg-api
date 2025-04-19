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
    fecha_alta DATE NOT NULL,
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
