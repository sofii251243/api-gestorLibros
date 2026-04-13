-- Crear base de datos
CREATE DATABASE IF NOT EXISTS booklog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE booklog_db;

-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) UNIQUE NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    hash_contrasena VARCHAR(255) NOT NULL,
    fecha_creacion BIGINT NOT NULL,
    INDEX idx_nombre_usuario (nombre_usuario),
    INDEX idx_correo (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Series
CREATE TABLE IF NOT EXISTS series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fecha_creacion BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_usuario_id (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Lecturas (Libros)
CREATE TABLE IF NOT EXISTS lecturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    ruta_archivo VARCHAR(500) NOT NULL,
    nombre_archivo VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    formato VARCHAR(50) NOT NULL,
    autor VARCHAR(255),
    serie_id BIGINT,
    progreso FLOAT NOT NULL DEFAULT 0,
    estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    cover_path VARCHAR(500),
    fecha_creacion BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_usuario_id (usuario_id),
    INDEX idx_titulo (titulo),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Colecciones
CREATE TABLE IF NOT EXISTS colecciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fecha_creacion BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_usuario_id (usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Lectura-Colección (Relación Many-to-Many)
CREATE TABLE IF NOT EXISTS lecturas_colecciones (
    lectura_id BIGINT NOT NULL,
    coleccion_id BIGINT NOT NULL,
    fecha_agregado BIGINT NOT NULL,
    PRIMARY KEY (lectura_id, coleccion_id),
    FOREIGN KEY (lectura_id) REFERENCES lecturas(id) ON DELETE CASCADE,
    FOREIGN KEY (coleccion_id) REFERENCES colecciones(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Notas
CREATE TABLE IF NOT EXISTS notas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lectura_id BIGINT NOT NULL,
    contenido LONGTEXT NOT NULL,
    referencia_pagina VARCHAR(100),
    fecha_creacion BIGINT NOT NULL,
    FOREIGN KEY (lectura_id) REFERENCES lecturas(id) ON DELETE CASCADE,
    INDEX idx_lectura_id (lectura_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla Citas
CREATE TABLE IF NOT EXISTS citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lectura_id BIGINT NOT NULL,
    texto_citado LONGTEXT NOT NULL,
    referencia_pagina VARCHAR(100),
    comentario LONGTEXT,
    fecha_creacion BIGINT NOT NULL,
    FOREIGN KEY (lectura_id) REFERENCES lecturas(id) ON DELETE CASCADE,
    INDEX idx_lectura_id (lectura_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar usuario de prueba
INSERT INTO usuarios (nombre_usuario, correo, hash_contrasena, fecha_creacion)
VALUES ('usuario_demo', 'demo@example.com', 'password123', UNIX_TIMESTAMP() * 1000);

-- Insertar serie de prueba
INSERT INTO series (usuario_id, nombre, fecha_creacion)
VALUES (1, 'Harry Potter', UNIX_TIMESTAMP() * 1000);

-- Insertar libro de prueba
INSERT INTO lecturas (usuario_id, ruta_archivo, nombre_archivo, titulo, formato, autor, serie_id, progreso, estado, fecha_creacion)
VALUES (1, '/libros/harry_potter_1.pdf', 'harry_potter_1.pdf', 'Harry Potter y la Piedra Filosofal', 'PDF', 'J.K. Rowling', 1, 0, 'PENDIENTE', UNIX_TIMESTAMP() * 1000);

-- Insertar colección de prueba
INSERT INTO colecciones (usuario_id, nombre, fecha_creacion)
VALUES (1, 'Mi Colección Favorita', UNIX_TIMESTAMP() * 1000);

-- Insertar relación lectura-colección
INSERT INTO lecturas_colecciones (lectura_id, coleccion_id, fecha_agregado)
VALUES (1, 1, UNIX_TIMESTAMP() * 1000);

-- Insertar nota de prueba
INSERT INTO notas (lectura_id, contenido, referencia_pagina, fecha_creacion)
VALUES (1, 'Excelente comienzo de la saga', 'pag. 50', UNIX_TIMESTAMP() * 1000);

-- Insertar cita de prueba
INSERT INTO citas (lectura_id, texto_citado, referencia_pagina, comentario, fecha_creacion)
VALUES (1, 'La magia existe, solo hay que saber verla', 'pag. 75', 'Frase inspiradora', UNIX_TIMESTAMP() * 1000);

