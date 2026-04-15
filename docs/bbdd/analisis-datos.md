# Análisis de datos

## 1. Problema real que resuelve la aplicación

Muchas pequeñas empresas y agencias comerciales reciben leads desde distintas fuentes, como formularios web, campañas publicitarias, redes sociales, recomendaciones o bases de datos externas. En muchos casos, estos leads se gestionan de forma desordenada mediante hojas de cálculo, notas sueltas o herramientas poco centralizadas.

Esto provoca varios problemas:
- pérdida de información importante;
- duplicidad de contactos;
- dificultad para saber qué leads son más valiosos;
- falta de seguimiento de llamadas, correos o acciones comerciales;
- mala priorización del trabajo del equipo comercial.

La aplicación **Lead Intelligence Manager** busca resolver este problema mediante un sistema que permita registrar, consultar, clasificar, priorizar y hacer seguimiento de leads comerciales de forma organizada.

Además de servir como herramienta de gestión, la aplicación incorpora una pequeña capa de lógica de datos, ya que permite evaluar la calidad de la información de un lead y asignarle una prioridad o puntuación básica según determinados criterios.

---

## 2. Usuarios del sistema

En una primera versión del proyecto, la aplicación contará con dos tipos de usuarios principales:

### Administrador
Es el usuario con mayor nivel de control dentro del sistema. Sus funciones principales son:
- gestionar usuarios;
- revisar la información general del sistema;
- administrar tablas auxiliares como estados o fuentes;
- consultar todos los leads registrados;
- supervisar la calidad y organización de los datos.

### Comercial
Es el usuario que trabaja directamente con los leads en el día a día. Sus funciones principales son:
- registrar nuevos leads;
- consultar leads existentes;
- actualizar información de contacto;
- cambiar el estado de un lead;
- registrar seguimientos o intentos de contacto;
- consultar qué leads tienen mayor prioridad.

---

## 3. Funcionalidades principales

La aplicación debe permitir realizar acciones reales relacionadas con la gestión comercial de leads. Las funcionalidades principales previstas son las siguientes:

### Gestión de leads
- dar de alta nuevos leads;
- modificar la información de un lead;
- eliminar leads;
- consultar el listado completo de leads;
- buscar leads por nombre, email, empresa o teléfono.

### Clasificación y priorización
- asignar una puntuación básica al lead;
- marcar una prioridad según la calidad o el interés comercial;
- clasificar los leads según su estado;
- distinguir el origen del lead mediante su fuente.

### Seguimiento comercial
- registrar llamadas, correos o contactos realizados;
- guardar observaciones sobre cada lead;
- consultar el historial de seguimiento de un lead;
- identificar leads sin seguimiento reciente.

### Consulta y filtrado
- filtrar leads por estado;
- filtrar leads por fuente;
- filtrar leads por prioridad;
- listar leads pendientes de contacto;
- mostrar leads con mayor puntuación.

### Integración con datos estructurados
- exportar información de leads a XML;
- validar la estructura del XML mediante XSD;
- utilizar el XML como formato de intercambio o reporte de datos.

---

## 4. Información que maneja la aplicación

La aplicación trabajará con información relacionada con la captación y gestión de contactos comerciales. Los datos principales que se manejarán serán los siguientes:

- datos identificativos del lead;
- datos de contacto;
- empresa asociada al lead;
- fuente de origen del lead;
- estado actual dentro del proceso comercial;
- prioridad o puntuación;
- historial de seguimientos realizados;
- usuario responsable de la gestión.

---

## 5. Entidades iniciales del proyecto

A partir del análisis anterior, se identifican las siguientes entidades principales:

### Usuario
Representa a las personas que acceden al sistema.

Datos principales:
- id_usuario
- nombre
- email
- contraseña
- rol

Esta entidad permite distinguir entre administradores y comerciales, y controlar quién realiza determinadas acciones en la aplicación.

### Empresa
Representa la empresa a la que pertenece un lead o con la que está relacionado.

Datos principales:
- id_empresa
- nombre
- sector
- ciudad
- pais
- web

Esta entidad permite agrupar leads por empresa y conservar información adicional útil para el análisis comercial.

### FuenteLead
Representa el origen por el que un lead llegó al sistema.

Datos principales:
- id_fuente
- nombre_fuente
- descripcion

Ejemplos de fuente:
- formulario web
- campaña publicitaria
- recomendación
- llamada entrante
- red social

Esta entidad es importante porque permite analizar qué fuentes generan mejores oportunidades.

### EstadoLead
Representa la fase actual del lead dentro del proceso comercial.

Datos principales:
- id_estado
- nombre_estado
- descripcion

Ejemplos de estado:
- nuevo
- contactado
- en seguimiento
- convertido
- descartado

Esta entidad permite clasificar el avance de cada lead dentro del flujo de trabajo.

### Lead
Es la entidad principal del proyecto. Representa cada contacto comercial gestionado en la aplicación.

Datos principales:
- id_lead
- nombre
- apellidos
- email
- telefono
- cargo
- interes
- puntuacion
- prioridad
- fecha_registro
- observaciones
- id_empresa
- id_fuente
- id_estado
- id_usuario_responsable

Cada lead se relaciona con una empresa, una fuente, un estado y un usuario responsable.

### Seguimiento
Representa cada acción comercial realizada sobre un lead.

Datos principales:
- id_seguimiento
- fecha_seguimiento
- tipo_contacto
- comentario
- resultado
- proxima_accion
- id_lead
- id_usuario

Ejemplos de tipo de contacto:
- llamada
- email
- reunión
- mensaje

Esta entidad permite guardar un historial de interacciones para saber qué se ha hecho con cada lead y qué acciones quedan pendientes.

---

## 6. Relaciones principales entre entidades

A partir de las entidades identificadas, se plantean las siguientes relaciones principales:

- Un **usuario** puede gestionar varios **leads**.
- Un **usuario** puede registrar varios **seguimientos**.
- Una **empresa** puede estar asociada a varios **leads**.
- Una **fuente de lead** puede estar asociada a varios **leads**.
- Un **estado de lead** puede estar asociado a varios **leads**.
- Un **lead** puede tener varios **seguimientos**.
- Cada **seguimiento** pertenece a un único **lead**.
- Cada **lead** pertenece a una única **empresa**.
- Cada **lead** tiene una única **fuente**.
- Cada **lead** tiene un único **estado**.
- Cada **lead** tiene un único **usuario responsable**.

Estas relaciones servirán como base para construir el diagrama entidad-relación y posteriormente el modelo relacional de la base de datos.

---

## 7. Justificación del diseño inicial

Se ha optado por un modelo de datos centrado en la entidad **Lead**, ya que es el núcleo funcional de la aplicación. Alrededor de esta entidad se organizan el resto de componentes necesarios para una gestión comercial básica y coherente.

La estructura inicial propuesta permite:
- registrar información real y útil;
- mantener separación entre datos principales y tablas auxiliares;
- evitar duplicidad innecesaria;
- facilitar búsquedas, filtros y consultas;
- permitir la integración posterior con la aplicación Java mediante JDBC;
- dejar preparada la base para futuras ampliaciones sin complicar en exceso el proyecto.

Este diseño busca un equilibrio entre realismo empresarial y viabilidad técnica dentro del nivel de primero de DAM.

---

## 8. Conclusión

La aplicación **Lead Intelligence Manager** gestionará información estructurada relacionada con leads comerciales, usuarios, empresas, fuentes, estados y seguimientos. El análisis realizado permite identificar de forma clara las entidades y relaciones que necesita el sistema, sirviendo como base para las siguientes fases del proyecto:

- elaboración del diagrama entidad-relación;
- transformación al modelo relacional;
- creación de tablas en SQL;
- inserción de datos de ejemplo;
- desarrollo de consultas útiles;
- integración con la aplicación Java.