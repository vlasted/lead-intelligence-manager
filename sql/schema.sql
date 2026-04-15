-- ----------------------------------------------------------------------
-- Lead Intelligence Manager
-- Script de creación de base de datos
-- ----------------------------------------------------------------------

DROP DATABASE IF EXISTS lead_intelligence_manager;
CREATE DATABASE lead_intelligence_manager;
USE lead_intelligence_manager;

-- ----------------------------------------------------------------------
-- Tabla: usuarios
-- ----------------------------------------------------------------------
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    CONSTRAINT chk_rol
        CHECK (rol IN ('admin', 'comercial'))
);

-- ----------------------------------------------------------------------
-- Tabla: empresas
-- ----------------------------------------------------------------------
CREATE TABLE empresas (
    id_empresa INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    sector VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100),
    pais VARCHAR(100),
    web VARCHAR(200)
);

-- ----------------------------------------------------------------------
-- Tabla: fuentes_lead
-- ----------------------------------------------------------------------
CREATE TABLE fuentes_lead (
    id_fuente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_fuente VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- ----------------------------------------------------------------------
-- Tabla: estados_lead
-- ----------------------------------------------------------------------
CREATE TABLE estados_lead (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- ----------------------------------------------------------------------
-- Tabla: leads
-- ----------------------------------------------------------------------
CREATE TABLE leads (
    id_lead INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150),
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(30),
    cargo VARCHAR(100),
    interes VARCHAR(255),
    puntuacion INT NOT NULL DEFAULT 0,
    prioridad VARCHAR(20) NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observaciones VARCHAR(500),
    id_empresa INT NOT NULL,
    id_fuente INT NOT NULL,
    id_estado INT NOT NULL,
    id_usuario_responsable INT NOT NULL,

    CONSTRAINT chk_puntuacion
        CHECK (puntuacion >= 0 AND puntuacion <= 100),

    CONSTRAINT chk_prioridad
        CHECK (prioridad IN ('baja', 'media', 'alta')),

    CONSTRAINT fk_lead_empresa
        FOREIGN KEY (id_empresa) REFERENCES empresas(id_empresa)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_lead_fuente
        FOREIGN KEY (id_fuente) REFERENCES fuentes_lead(id_fuente)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_lead_estado
        FOREIGN KEY (id_estado) REFERENCES estados_lead(id_estado)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_lead_usuario
        FOREIGN KEY (id_usuario_responsable) REFERENCES usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- ----------------------------------------------------------------------
-- Tabla: seguimientos
-- ----------------------------------------------------------------------
CREATE TABLE seguimientos (
    id_seguimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha_seguimiento DATETIME NOT NULL,
    tipo_contacto VARCHAR(50) NOT NULL,
    comentario VARCHAR(500),
    resultado VARCHAR(255),
    proxima_accion VARCHAR(255),
    id_lead INT NOT NULL,
    id_usuario INT NOT NULL,

    CONSTRAINT chk_tipo_contacto
        CHECK (tipo_contacto IN ('llamada', 'email', 'reunion', 'mensaje')),

    CONSTRAINT fk_seguimiento_lead
        FOREIGN KEY (id_lead) REFERENCES leads(id_lead)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_seguimiento_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- ----------------------------------------------------------------------
-- Índices útiles
-- ----------------------------------------------------------------------
CREATE INDEX idx_leads_email ON leads(email);
CREATE INDEX idx_leads_prioridad ON leads(prioridad);
CREATE INDEX idx_leads_fecha_registro ON leads(fecha_registro);
CREATE INDEX idx_seguimientos_fecha ON seguimientos(fecha_seguimiento);