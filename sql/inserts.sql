-- ----------------------------------------------------------------------------------------------
-- Lead Intelligence Manager
-- Script de inserción de datos de ejemplo
-- ----------------------------------------------------------------------------------------------

USE lead_intelligence_manager;

-- ----------------------------------------------------------------------------------------------
-- Tabla: usuarios
-- ----------------------------------------------------------------------------------------------
INSERT INTO usuarios (id_usuario, nombre, email, contrasena, rol) VALUES
(1, 'Lenin Ramirez', 'lenin@leadmanager.com', 'admin123', 'admin'),
(2, 'Marta Lopez', 'marta@leadmanager.com', 'comercial123', 'comercial'),
(3, 'Carlos Ruiz', 'carlos@leadmanager.com', 'comercial456', 'comercial');

-- ----------------------------------------------------------------------------------------------
-- Tabla: empresas
-- ----------------------------------------------------------------------------------------------
INSERT INTO empresas (id_empresa, nombre, sector, ciudad, pais, web) VALUES
(1, 'DataNova Consulting', 'Tecnologia', 'Madrid', 'España', 'https://www.datanova.com'),
(2, 'BlueRetail Group', 'Retail', 'Barcelona', 'España', 'https://www.blueretail.com'),
(3, 'FinCore Solutions', 'Finanzas', 'Valencia', 'España', 'https://www.fincore.com'),
(4, 'GreenLogistics Hub', 'Logistica', 'Sevilla', 'España', 'https://www.greenlogistics.com');

-- ----------------------------------------------------------------------------------------------
-- Tabla: fuentes_lead
-- ----------------------------------------------------------------------------------------------
INSERT INTO fuentes_lead (id_fuente, nombre_fuente, descripcion) VALUES
(1, 'Formulario web', 'Lead captado desde el formulario de contacto de la web'),
(2, 'LinkedIn', 'Lead obtenido a traves de contacto profesional en LinkedIn'),
(3, 'Campaña Ads', 'Lead procedente de campañas publicitarias'),
(4, 'Recomendacion', 'Lead captado por referencia de un cliente o contacto');

-- ----------------------------------------------------------------------------------------------
-- Tabla: estados_lead
-- ----------------------------------------------------------------------------------------------
INSERT INTO estados_lead (id_estado, nombre_estado, descripcion) VALUES
(1, 'Nuevo', 'Lead recien registrado en el sistema'),
(2, 'Contactado', 'Lead que ya ha recibido un primer contacto'),
(3, 'En seguimiento', 'Lead con interes y en proceso comercial activo'),
(4, 'Descartado', 'Lead que no continuara en el proceso comercial'),
(5, 'Convertido', 'Lead que ha pasado a ser cliente');

-- ----------------------------------------------------------------------------------------------
-- Tabla: leads
-- ----------------------------------------------------------------------------------------------
INSERT INTO leads (
    id_lead, nombre, apellidos, email, telefono, cargo, interes, puntuacion,
    prioridad, fecha_registro, observaciones, id_empresa, id_fuente, id_estado, id_usuario_responsable
) VALUES
(1, 'Ana', 'Martinez', 'ana.martinez@datanova.com', '600111222', 'CTO',
 'Solucion de analitica comercial', 85, 'alta', '2026-04-10 09:15:00',
 'Mostro interes en una demo tecnica', 1, 1, 3, 2),

(2, 'Javier', 'Santos', 'javier.santos@blueretail.com', '600222333', 'Responsable de expansion',
 'Herramienta para clasificacion de leads', 72, 'media', '2026-04-11 10:30:00',
 'Pide informacion sobre precios y tiempos de implantacion', 2, 2, 2, 2),

(3, 'Lucia', 'Gomez', 'lucia.gomez@fincore.com', '600333444', 'Directora de operaciones',
 'Sistema de seguimiento comercial', 91, 'alta', '2026-04-12 11:00:00',
 'Lead muy bien perfilado y con necesidad inmediata', 3, 3, 3, 3),

(4, 'Pablo', 'Herrera', 'pablo.herrera@greenlogistics.com', '600444555', 'CEO',
 'Automatizacion del proceso de captacion', 65, 'media', '2026-04-13 12:20:00',
 'Solicito una reunion para la proxima semana', 4, 4, 1, 3),

(5, 'Elena', 'Ruiz', 'elena.ruiz@datanova.com', '600555666', 'Marketing Manager',
 'Mejora de conversion de leads', 58, 'baja', '2026-04-14 08:45:00',
 'Interes inicial, pero aun sin presupuesto definido', 1, 1, 2, 2),

(6, 'Sergio', 'Navarro', 'sergio.navarro@blueretail.com', '600666777', 'COO',
 'Panel de control de oportunidades', 40, 'baja', '2026-04-14 15:10:00',
 'Lead poco maduro y con dudas sobre el proyecto', 2, 3, 4, 3),

(7, 'Marta', 'Delgado', 'marta.delgado@fincore.com', '600777888', 'Head of Sales',
 'Scoring y priorizacion automatica', 88, 'alta', '2026-04-15 09:05:00',
 'Muy interesada en piloto inicial', 3, 2, 5, 2),

(8, 'Raul', 'Ortega', 'raul.ortega@greenlogistics.com', '600888999', 'Director comercial',
 'Gestion centralizada de contactos', 77, 'media', '2026-04-15 13:40:00',
 'Quiere comparar con su solucion actual', 4, 1, 3, 3);

-- ----------------------------------------------------------------------------------------------
-- Tabla: seguimientos
-- ----------------------------------------------------------------------------------------------
INSERT INTO seguimientos (
    id_seguimiento, fecha_seguimiento, tipo_contacto, comentario,
    resultado, proxima_accion, id_lead, id_usuario
) VALUES
(1, '2026-04-10 16:00:00', 'llamada',
 'Se realizo llamada inicial para presentar la solucion',
 'Interesado', 'Enviar propuesta inicial', 1, 2),

(2, '2026-04-11 17:30:00', 'email',
 'Se envio email con informacion comercial y funcionalidades',
 'Pendiente respuesta', 'Esperar confirmacion del cliente', 2, 2),

(3, '2026-04-12 18:15:00', 'reunion',
 'Reunion online con el equipo de operaciones',
 'Muy positivo', 'Preparar demo personalizada', 3, 3),

(4, '2026-04-13 09:45:00', 'mensaje',
 'Se envio mensaje para confirmar disponibilidad de reunion',
 'Pendiente', 'Llamar en 2 dias', 4, 3),

(5, '2026-04-14 10:20:00', 'email',
 'Se envio caso de uso similar al de su sector',
 'Interes moderado', 'Volver a contactar la proxima semana', 5, 2),

(6, '2026-04-14 16:40:00', 'llamada',
 'Se detecto poco interes y falta de presupuesto',
 'Descartado', 'Cerrar oportunidad', 6, 3),

(7, '2026-04-15 11:00:00', 'reunion',
 'Se presento piloto inicial y alcance del proyecto',
 'Aceptado', 'Enviar contrato de prueba', 7, 2),

(8, '2026-04-15 18:00:00', 'email',
 'Se envio resumen de la reunion y propuesta economica',
 'Pendiente revision', 'Seguimiento en 3 dias', 8, 3),

(9, '2026-04-16 09:30:00', 'email',
 'Se envio demo tecnica solicitada',
 'Pendiente respuesta', 'Hacer llamada de seguimiento', 1, 2),

(10, '2026-04-16 12:00:00', 'llamada',
 'Se resolvieron dudas sobre integracion y soporte',
 'Interesado', 'Agendar segunda reunion', 3, 3);