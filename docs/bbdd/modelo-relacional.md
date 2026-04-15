# Modelo relacional

A partir del diagrama entidad-relación del proyecto **Lead Intelligence Manager**, se obtiene el siguiente modelo relacional.

---

## 1. Tabla: usuarios

**USUARIOS**
- id_usuario INT PRIMARY KEY
- nombre VARCHAR(100) NOT NULL
- email VARCHAR(150) NOT NULL UNIQUE
- contrasena VARCHAR(255) NOT NULL
- rol VARCHAR(30) NOT NULL

### Descripción
Esta tabla almacena los usuarios que acceden al sistema. Cada usuario puede gestionar varios leads y registrar varios seguimientos.

### Restricciones
- La clave primaria es `id_usuario`.
- El campo `email` no puede repetirse.
- El campo `rol` será obligatorio.

---

## 2. Tabla: empresas

**EMPRESAS**
- id_empresa INT PRIMARY KEY
- nombre VARCHAR(150) NOT NULL
- sector VARCHAR(100) NOT NULL
- ciudad VARCHAR(100)
- pais VARCHAR(100)
- web VARCHAR(200)

### Descripción
Esta tabla almacena la información de las empresas asociadas a los leads.

### Restricciones
- La clave primaria es `id_empresa`.
- El nombre de la empresa será obligatorio.

---

## 3. Tabla: fuentes_lead

**FUENTES_LEAD**
- id_fuente INT PRIMARY KEY
- nombre_fuente VARCHAR(100) NOT NULL
- descripcion VARCHAR(255)

### Descripción
Esta tabla almacena el origen del lead, por ejemplo formulario web, campaña, red social o recomendación.

### Restricciones
- La clave primaria es `id_fuente`.
- El campo `nombre_fuente` será obligatorio.

---

## 4. Tabla: estados_lead

**ESTADOS_LEAD**
- id_estado INT PRIMARY KEY
- nombre_estado VARCHAR(100) NOT NULL
- descripcion VARCHAR(255)

### Descripción
Esta tabla almacena los posibles estados del lead dentro del proceso comercial.

### Restricciones
- La clave primaria es `id_estado`.
- El campo `nombre_estado` será obligatorio.

---

## 5. Tabla: leads

**LEADS**
- id_lead INT PRIMARY KEY
- nombre VARCHAR(100) NOT NULL
- apellidos VARCHAR(150)
- email VARCHAR(150) NOT NULL
- telefono VARCHAR(30)
- cargo VARCHAR(100)
- interes VARCHAR(255)
- puntuacion INT DEFAULT 0
- prioridad VARCHAR(30) NOT NULL
- fecha_registro DATETIME NOT NULL
- observaciones VARCHAR(500)
- id_empresa INT NOT NULL
- id_fuente INT NOT NULL
- id_estado INT NOT NULL
- id_usuario_responsable INT NOT NULL

### Claves foráneas
- id_empresa REFERENCES empresas(id_empresa)
- id_fuente REFERENCES fuentes_lead(id_fuente)
- id_estado REFERENCES estados_lead(id_estado)
- id_usuario_responsable REFERENCES usuarios(id_usuario)

### Descripción
Esta es la tabla principal del sistema. Almacena la información de cada lead comercial y su relación con la empresa, la fuente, el estado y el usuario responsable.

### Restricciones
- La clave primaria es `id_lead`.
- El campo `email` será obligatorio.
- El campo `prioridad` será obligatorio.
- La fecha de registro será obligatoria.
- Todo lead debe estar asociado a una empresa.
- Todo lead debe tener una fuente.
- Todo lead debe tener un estado.
- Todo lead debe tener un usuario responsable.
- La puntuación no podrá ser negativa.

---

## 6. Tabla: seguimientos

**SEGUIMIENTOS**
- id_seguimiento INT PRIMARY KEY
- fecha_seguimiento DATETIME NOT NULL
- tipo_contacto VARCHAR(50) NOT NULL
- comentario VARCHAR(500)
- resultado VARCHAR(255)
- proxima_accion VARCHAR(255)
- id_lead INT NOT NULL
- id_usuario INT NOT NULL

### Claves foráneas
- id_lead REFERENCES leads(id_lead)
- id_usuario REFERENCES usuarios(id_usuario)

### Descripción
Esta tabla registra cada interacción o acción comercial realizada sobre un lead, como llamadas, correos o reuniones.

### Restricciones
- La clave primaria es `id_seguimiento`.
- La fecha del seguimiento será obligatoria.
- El tipo de contacto será obligatorio.
- Todo seguimiento debe estar asociado a un lead.
- Todo seguimiento debe estar asociado a un usuario.

---

# Resumen de relaciones del modelo relacional

- Un **usuario** puede gestionar varios **leads**.
- Un **usuario** puede registrar varios **seguimientos**.
- Una **empresa** puede tener varios **leads**.
- Una **fuente de lead** puede estar asociada a varios **leads**.
- Un **estado de lead** puede estar asociado a varios **leads**.
- Un **lead** puede tener varios **seguimientos**.

---

# Esquema resumido del modelo relacional

- **usuarios**(`id_usuario`, nombre, email, contrasena, rol)
- **empresas**(`id_empresa`, nombre, sector, ciudad, pais, web)
- **fuentes_lead**(`id_fuente`, nombre_fuente, descripcion)
- **estados_lead**(`id_estado`, nombre_estado, descripcion)
- **leads**(`id_lead`, nombre, apellidos, email, telefono, cargo, interes, puntuacion, prioridad, fecha_registro, observaciones, `id_empresa`, `id_fuente`, `id_estado`, `id_usuario_responsable`)
- **seguimientos**(`id_seguimiento`, fecha_seguimiento, tipo_contacto, comentario, resultado, proxima_accion, `id_lead`, `id_usuario`)

---

# Justificación del modelo relacional

Se ha diseñado un modelo relacional sencillo, coherente y alineado con la funcionalidad real de la aplicación. La tabla central es **leads**, ya que representa el núcleo de la gestión comercial. A su alrededor se relacionan tablas auxiliares que permiten clasificar, organizar y dar seguimiento a la información.

Este modelo permite:
- registrar leads reales;
- relacionarlos con empresas, fuentes y estados;
- asignarlos a usuarios responsables;
- guardar un historial de seguimientos;
- facilitar búsquedas, filtros, consultas y operaciones CRUD;
- integrar posteriormente la base de datos con la aplicación Java mediante JDBC.

El diseño busca un equilibrio entre realismo empresarial y viabilidad técnica dentro del nivel de primero de DAM.