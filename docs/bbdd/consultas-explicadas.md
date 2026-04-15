# Consultas explicadas

En este documento se recogen las principales consultas SQL del proyecto **Lead Intelligence Manager**, explicando qué hace cada una y por qué resulta útil dentro de la aplicación.

La finalidad de estas consultas es demostrar que la base de datos no solo está creada correctamente, sino que también responde a necesidades reales de gestión comercial, búsqueda de información, seguimiento y análisis básico de leads.

---

## 1. Listado general de leads con empresa, fuente, estado y usuario responsable

### Objetivo
Mostrar una visión completa de cada lead, incluyendo información relacionada de otras tablas.

### Qué hace
Esta consulta utiliza varios `INNER JOIN` para unir la tabla `leads` con:
- `empresas`
- `fuentes_lead`
- `estados_lead`
- `usuarios`

De esta forma, en lugar de mostrar solo identificadores, permite ver datos más útiles para el usuario, como el nombre de la empresa, la fuente del lead, el estado actual y el usuario responsable.

### Utilidad en la aplicación
Es una consulta básica para construir el listado principal de leads dentro de la app.

---

## 2. Leads con prioridad alta

### Objetivo
Obtener rápidamente los leads más importantes.

### Qué hace
Filtra los registros de la tabla `leads` donde la columna `prioridad` es igual a `'alta'` y los ordena por `puntuacion` de mayor a menor.

### Utilidad en la aplicación
Permite al equipo comercial centrarse primero en los contactos con mayor valor potencial.

---

## 3. Leads registrados por una fuente concreta

### Objetivo
Consultar qué leads han llegado desde una fuente determinada.

### Qué hace
Relaciona `leads` con `fuentes_lead` y filtra por el nombre de la fuente, por ejemplo `'Formulario web'`.

### Utilidad en la aplicación
Sirve para analizar qué canales de captación generan más contactos.

---

## 4. Leads en estado "En seguimiento"

### Objetivo
Mostrar los leads que están en una fase activa del proceso comercial.

### Qué hace
Une `leads` con `estados_lead` y filtra por `nombre_estado = 'En seguimiento'`.

### Utilidad en la aplicación
Es útil para que el equipo vea qué oportunidades están abiertas y requieren atención.

---

## 5. Leads asignados a un usuario concreto

### Objetivo
Consultar qué leads gestiona un determinado usuario.

### Qué hace
Relaciona `leads` con `usuarios` mediante el campo `id_usuario_responsable` y filtra por un nombre concreto, por ejemplo `'Marta Lopez'`.

### Utilidad en la aplicación
Permite ver la carga de trabajo de cada usuario comercial o responsable.

---

## 6. Buscar leads por nombre o apellidos

### Objetivo
Facilitar la localización de leads concretos mediante texto parcial.

### Qué hace
Realiza una búsqueda con `LIKE` sobre los campos `nombre` y `apellidos`.

### Utilidad en la aplicación
Simula la funcionalidad de búsqueda rápida dentro del sistema.

---

## 7. Historial de seguimientos de un lead concreto

### Objetivo
Consultar todas las acciones comerciales realizadas sobre un lead.

### Qué hace
Relaciona `seguimientos` con `leads` y filtra por un `id_lead` concreto. Después ordena por `fecha_seguimiento` de más reciente a más antigua.

### Utilidad en la aplicación
Permite revisar el historial de llamadas, emails, reuniones y otras acciones asociadas a un lead.

---

## 8. Número de leads por fuente

### Objetivo
Contar cuántos leads provienen de cada fuente de captación.

### Qué hace
Usa `LEFT JOIN` entre `fuentes_lead` y `leads`, junto con `COUNT`, para obtener el total por cada fuente.

### Utilidad en la aplicación
Sirve para medir el rendimiento de los canales de captación.

---

## 9. Número de leads por estado

### Objetivo
Saber cuántos leads hay en cada fase del proceso comercial.

### Qué hace
Relaciona `estados_lead` con `leads` y agrupa por el nombre del estado.

### Utilidad en la aplicación
Ayuda a obtener una visión general del embudo comercial.

---

## 10. Media de puntuación de leads por empresa

### Objetivo
Analizar la calidad media de los leads asociados a cada empresa.

### Qué hace
Relaciona `empresas` con `leads`, agrupa por empresa y calcula la media de `puntuacion` usando `AVG`.

### Utilidad en la aplicación
Permite detectar qué empresas están asociadas a oportunidades más interesantes.

---

## 11. Leads sin seguimientos registrados

### Objetivo
Encontrar leads que todavía no han recibido ninguna acción comercial.

### Qué hace
Utiliza `LEFT JOIN` entre `leads` y `seguimientos`, y luego filtra los casos donde `id_seguimiento IS NULL`.

### Utilidad en la aplicación
Es una consulta muy útil para detectar leads olvidados o pendientes de primer contacto.

---

## 12. Último seguimiento realizado a cada lead

### Objetivo
Consultar la fecha de la última interacción con cada lead.

### Qué hace
Relaciona `leads` con `seguimientos`, agrupa por lead y obtiene la fecha máxima mediante `MAX(fecha_seguimiento)`.

### Utilidad en la aplicación
Sirve para controlar la actividad comercial reciente y detectar leads inactivos.

---

## 13. Leads convertidos en cliente

### Objetivo
Mostrar los leads que han alcanzado el estado final de conversión.

### Qué hace
Relaciona `leads` con `estados_lead` y filtra por `nombre_estado = 'Convertido'`.

### Utilidad en la aplicación
Permite identificar los leads exitosos y medir resultados comerciales.

---

## 14. Seguimientos realizados por cada usuario

### Objetivo
Contar cuántas acciones comerciales ha registrado cada usuario.

### Qué hace
Relaciona `usuarios` con `seguimientos`, agrupa por usuario y utiliza `COUNT`.

### Utilidad en la aplicación
Sirve para medir actividad comercial básica por responsable.

---

## 15. Leads registrados entre dos fechas

### Objetivo
Consultar los leads creados en un periodo concreto.

### Qué hace
Filtra la tabla `leads` mediante `BETWEEN` sobre el campo `fecha_registro`.

### Utilidad en la aplicación
Permite analizar la captación de leads en intervalos temporales específicos.

---

# Conclusión

Las consultas diseñadas para este proyecto cubren necesidades reales de una aplicación de gestión de leads:
- consulta general de información;
- búsquedas y filtros;
- análisis por fuente y estado;
- seguimiento comercial;
- métricas básicas de actividad.

Gracias a estas consultas, la base de datos no solo almacena información, sino que permite transformarla en datos útiles para la toma de decisiones dentro del proceso comercial.