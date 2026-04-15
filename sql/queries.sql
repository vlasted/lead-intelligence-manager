-- --------------------------------------------------------------------------------
-- Lead Intelligence Manager
-- Consultas SQL del proyecto
-- --------------------------------------------------------------------------------

USE lead_intelligence_manager;

-- --------------------------------------------------------------------------------
-- 1. Listado general de leads con empresa, fuente, estado y usuario responsable
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    l.email,
    l.telefono,
    e.nombre AS empresa,
    f.nombre_fuente AS fuente,
    es.nombre_estado AS estado,
    u.nombre AS usuario_responsable,
    l.puntuacion,
    l.prioridad,
    l.fecha_registro
FROM leads l
INNER JOIN empresas e ON l.id_empresa = e.id_empresa
INNER JOIN fuentes_lead f ON l.id_fuente = f.id_fuente
INNER JOIN estados_lead es ON l.id_estado = es.id_estado
INNER JOIN usuarios u ON l.id_usuario_responsable = u.id_usuario
ORDER BY l.fecha_registro DESC;

-- --------------------------------------------------------------------------------
-- 2. Leads con prioridad alta
-- --------------------------------------------------------------------------------
SELECT 
    id_lead,
    nombre,
    apellidos,
    email,
    prioridad,
    puntuacion
FROM leads
WHERE prioridad = 'alta'
ORDER BY puntuacion DESC;

-- --------------------------------------------------------------------------------
-- 3. Leads registrados por una fuente concreta
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    f.nombre_fuente
FROM leads l
INNER JOIN fuentes_lead f ON l.id_fuente = f.id_fuente
WHERE f.nombre_fuente = 'Formulario web';

-- --------------------------------------------------------------------------------
-- 4. Leads en estado "En seguimiento"
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    es.nombre_estado,
    l.prioridad
FROM leads l
INNER JOIN estados_lead es ON l.id_estado = es.id_estado
WHERE es.nombre_estado = 'En seguimiento'
ORDER BY l.puntuacion DESC;

-- --------------------------------------------------------------------------------
-- 5. Leads asignados a un usuario concreto
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    u.nombre AS responsable
FROM leads l
INNER JOIN usuarios u ON l.id_usuario_responsable = u.id_usuario
WHERE u.nombre = 'Marta Lopez';

-- --------------------------------------------------------------------------------
-- 6. Buscar leads por nombre o apellidos
-- --------------------------------------------------------------------------------
SELECT 
    id_lead,
    nombre,
    apellidos,
    email
FROM leads
WHERE nombre LIKE '%an%'
   OR apellidos LIKE '%an%';

-- --------------------------------------------------------------------------------
-- 7. Historial de seguimientos de un lead concreto
-- --------------------------------------------------------------------------------
SELECT 
    s.id_seguimiento,
    l.nombre AS nombre_lead,
    l.apellidos AS apellidos_lead,
    s.fecha_seguimiento,
    s.tipo_contacto,
    s.comentario,
    s.resultado,
    s.proxima_accion
FROM seguimientos s
INNER JOIN leads l ON s.id_lead = l.id_lead
WHERE l.id_lead = 1
ORDER BY s.fecha_seguimiento DESC;

-- --------------------------------------------------------------------------------
-- 8. Número de leads por fuente
-- --------------------------------------------------------------------------------
SELECT 
    f.nombre_fuente,
    COUNT(l.id_lead) AS total_leads
FROM fuentes_lead f
LEFT JOIN leads l ON f.id_fuente = l.id_fuente
GROUP BY f.nombre_fuente
ORDER BY total_leads DESC;

-- --------------------------------------------------------------------------------
-- 9. Número de leads por estado
-- --------------------------------------------------------------------------------
SELECT 
    es.nombre_estado,
    COUNT(l.id_lead) AS total_leads
FROM estados_lead es
LEFT JOIN leads l ON es.id_estado = l.id_estado
GROUP BY es.nombre_estado
ORDER BY total_leads DESC;

-- --------------------------------------------------------------------------------
-- 10. Media de puntuación de leads por empresa
-- --------------------------------------------------------------------------------
SELECT 
    e.nombre AS empresa,
    AVG(l.puntuacion) AS media_puntuacion
FROM empresas e
INNER JOIN leads l ON e.id_empresa = l.id_empresa
GROUP BY e.nombre
ORDER BY media_puntuacion DESC;

-- --------------------------------------------------------------------------------
-- 11. Leads sin seguimientos registrados
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    l.email
FROM leads l
LEFT JOIN seguimientos s ON l.id_lead = s.id_lead
WHERE s.id_seguimiento IS NULL;

-- --------------------------------------------------------------------------------
-- 12. Último seguimiento realizado a cada lead
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    MAX(s.fecha_seguimiento) AS ultimo_seguimiento
FROM leads l
LEFT JOIN seguimientos s ON l.id_lead = s.id_lead
GROUP BY l.id_lead, l.nombre, l.apellidos
ORDER BY ultimo_seguimiento DESC;

-- --------------------------------------------------------------------------------
-- 13. Leads convertidos en cliente
-- --------------------------------------------------------------------------------
SELECT 
    l.id_lead,
    l.nombre,
    l.apellidos,
    es.nombre_estado
FROM leads l
INNER JOIN estados_lead es ON l.id_estado = es.id_estado
WHERE es.nombre_estado = 'Convertido';

-- --------------------------------------------------------------------------------
-- 14. Seguimientos realizados por cada usuario
-- --------------------------------------------------------------------------------
SELECT 
    u.nombre AS usuario,
    COUNT(s.id_seguimiento) AS total_seguimientos
FROM usuarios u
LEFT JOIN seguimientos s ON u.id_usuario = s.id_usuario
GROUP BY u.nombre
ORDER BY total_seguimientos DESC;

-- --------------------------------------------------------------------------------
-- 15. Leads registrados entre dos fechas
-- --------------------------------------------------------------------------------
SELECT 
    id_lead,
    nombre,
    apellidos,
    fecha_registro
FROM leads
WHERE fecha_registro BETWEEN '2026-04-10 00:00:00' AND '2026-04-15 23:59:59'
ORDER BY fecha_registro ASC;