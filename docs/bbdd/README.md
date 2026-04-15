# Bases de Datos

En esta carpeta se recoge el trabajo correspondiente al módulo de **Bases de Datos** del proyecto intermodular **Lead Intelligence Manager**.

## Objetivo de esta parte del proyecto

Diseñar y construir la base de datos relacional de la aplicación, partiendo de una necesidad real de empresa: gestionar leads comerciales, su estado, su origen, su empresa asociada y el seguimiento realizado por los usuarios del sistema.

El diseño de la base de datos se ha realizado de forma progresiva, siguiendo las fases habituales del modelado de datos:

1. análisis del problema y de la información necesaria;
2. diseño conceptual mediante diagrama entidad-relación;
3. transformación al modelo relacional;
4. creación de tablas en SQL;
5. inserción de datos de ejemplo;
6. elaboración de consultas útiles y coherentes con la aplicación.

## Archivos incluidos

### `analisis-datos.md`
Documento inicial donde se explica:
- el problema real que resuelve la aplicación;
- los usuarios del sistema;
- las funcionalidades principales;
- la información que maneja el proyecto;
- las entidades y relaciones detectadas.

### `diagrama-er.png`
Diagrama entidad-relación del proyecto.

Representa las entidades principales del sistema:
- Usuario
- Empresa
- FuenteLead
- EstadoLead
- Lead
- Seguimiento

También muestra sus relaciones y cardinalidades.

### `modelo-relacional.md`
Transformación del diagrama E/R al modelo relacional.

Aquí se definen:
- las tablas del sistema;
- sus campos;
- claves primarias;
- claves foráneas;
- restricciones básicas.

### `consultas-explicadas.md`
Documento donde se explica el objetivo y la utilidad de las consultas principales del proyecto.

### `README.md`
Resumen general de la parte de Bases de Datos y organización del trabajo realizado.

## Relación con los scripts SQL

La implementación física del modelo se encuentra en la carpeta `sql/` del repositorio:

- `schema.sql`  
  Contiene la creación de la base de datos y de las tablas.

- `inserts.sql`  
  Contiene datos de ejemplo para probar el sistema.

- `queries.sql`  
  Contiene consultas SQL útiles para la gestión y análisis de leads.

## Diseño realizado

La entidad central del proyecto es **Lead**, ya que representa cada oportunidad comercial registrada en el sistema.

A su alrededor se estructuran las demás entidades:

- **Usuario**: personas que gestionan leads o registran seguimientos.
- **Empresa**: organización a la que pertenece o con la que se relaciona el lead.
- **FuenteLead**: canal de procedencia del lead.
- **EstadoLead**: situación actual del lead dentro del proceso comercial.
- **Seguimiento**: acciones realizadas sobre cada lead.

Este diseño permite una estructura clara, normalizada y coherente con una aplicación real.

## Finalidad de la base de datos

La base de datos está pensada para soportar funcionalidades como:

- alta, baja y modificación de leads;
- consulta y filtrado por estado, fuente o prioridad;
- asignación de leads a usuarios responsables;
- registro de seguimientos comerciales;
- análisis básico mediante consultas SQL;
- futura integración con la aplicación Java mediante JDBC.

## Estado actual

Actualmente la parte de Bases de Datos incluye:

- análisis de datos completado;
- diagrama E/R completado;
- modelo relacional definido;
- script SQL de creación de tablas realizado;
- script de inserción de datos realizado;
- script de consultas realizado;
- explicación de consultas redactada.

## Conclusión

La carpeta de Bases de Datos recoge la base estructural del proyecto **Lead Intelligence Manager**. Esta parte resulta clave, ya que servirá como soporte para el resto del desarrollo: programación en Java, integración mediante JDBC, XML/XSD y funcionamiento general de la aplicación.