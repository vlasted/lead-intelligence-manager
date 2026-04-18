# Lead Intelligence Manager

Aplicación Java orientada a la gestión, validación, priorización y seguimiento de leads comerciales.

## Descripción

**Lead Intelligence Manager** es el proyecto intermodular de 1º DAM centrado en una necesidad real de empresa: gestionar de forma organizada los contactos comerciales que llegan desde distintas fuentes, clasificarlos, asignarles una prioridad y registrar su seguimiento.

La aplicación está planteada como una solución real, no como una web estética de portfolio. El objetivo es desarrollar una app coherente con el nivel de primero, integrando base de datos relacional, SQL, XML/XSD, documentación técnica y programación en Java con conexión JDBC.

## Problema que resuelve

Muchas pequeñas empresas y agencias gestionan sus leads de forma desordenada mediante hojas de cálculo, correos o notas dispersas. Esto genera problemas como:

- pérdida de información;
- duplicidad de contactos;
- falta de seguimiento;
- dificultad para saber qué leads son prioritarios;
- poca trazabilidad del proceso comercial.

Este proyecto busca resolver ese problema mediante una aplicación que permita registrar, consultar, clasificar y hacer seguimiento de leads de forma centralizada.

## Objetivos del proyecto

- Diseñar una base de datos relacional coherente con una necesidad empresarial real.
- Construir una aplicación Java capaz de realizar operaciones CRUD reales.
- Integrar la base de datos mediante JDBC.
- Representar datos estructurados en XML y validarlos mediante XSD.
- Documentar el entorno de ejecución, la estructura del proyecto y el trabajo realizado por módulos.
- Mantener un repositorio ordenado y profesional con commits progresivos.

## Tecnologías previstas

- Java
- JDBC
- MySQL
- SQL
- XML / XSD
- GitHub
- VS Code
- IntelliJ IDEA
- Draw.io

## Estructura del repositorio

- `assets/capturas/`  
  Capturas de apoyo del proyecto.

- `docs/bbdd/`  
  Documentación de Bases de Datos: análisis, diagrama E/R, modelo relacional y consultas explicadas.

- `docs/entornos/`  
  Documentación relacionada con la organización del repositorio y el trabajo realizado en Entornos de Desarrollo.

- `docs/mpo/`  
  Documentación de la parte de MPO y decisiones de arquitectura/mejora estructural.

- `docs/sistemas/`  
  Informe técnico del entorno de ejecución, capturas y evidencias del módulo de Sistemas Informáticos.

- `docs/xml/`  
  Explicación del XML/XSD, validación e integración con el proyecto.

- `sql/`  
  Scripts SQL del proyecto:
  - `schema.sql`
  - `inserts.sql`
  - `queries.sql`

- `src/main/java/`  
  Código fuente de la aplicación Java.

- `xml/`  
  Archivos XML y XSD del proyecto.

## Módulos implicados

Este repositorio se utiliza como base común del proyecto intermodular, organizando el trabajo por módulos:

- Bases de Datos
- Entornos de Desarrollo
- Lenguajes de Marcas y Sistemas de Gestión de Información
- Programación
- Sistemas Informáticos
- MPO (Ampliación de Programación)

## Estado actual del proyecto

Actualmente el proyecto tiene completada gran parte de la base de diseño de datos:

- análisis de datos realizado;
- diagrama E/R completado;
- modelo relacional definido;
- script SQL de creación de tablas realizado;
- script de inserción de datos de ejemplo realizado;
- script de consultas SQL realizado;
- documento de consultas explicadas redactado.

Pendiente de desarrollo en siguientes fases:

- XML/XSD y validación;
- informe técnico de Sistemas Informáticos;
- desarrollo de la aplicación Java con JDBC;
- mejora estructural para MPO.

## Enfoque del desarrollo

El proyecto se está desarrollando de forma progresiva y modular:

1. definición de la idea y del problema real;
2. organización del repositorio;
3. diseño de base de datos;
4. creación de scripts SQL;
5. documentación por módulos;
6. desarrollo posterior de la aplicación Java;
7. integración con XML/XSD y cierre técnico del proyecto.

## Acceso de prueba a la aplicación

La aplicación incluye un sistema de login conectado a la base de datos `lead_intelligence_manager`.

Para acceder y probar la aplicación, se pueden utilizar las siguientes credenciales de demostración:

### Usuario administrador
- **Email:** `lenin@leadmanager.com`
- **Contraseña:** `admin123`
- **Rol:** `admin`

### Usuario comercial
- **Email:** `marta@leadmanager.com`
- **Contraseña:** `comercial123`
- **Rol:** `comercial`

## Nota de uso

Antes de iniciar la aplicación, es necesario:

1. tener MySQL Server en ejecución;
2. haber ejecutado previamente los archivos:
    - `sql/schema.sql`
    - `sql/inserts.sql`
3. revisar la configuración de conexión en `DatabaseConnection.java`.

Una vez hecho esto, la aplicación puede ejecutarse desde la clase `Main.java`.

## Autor

Proyecto realizado por Lenin Fonseca como parte del Proyecto Intermodular de 1º DAM.