# Informe técnico del entorno de ejecución

## 1. Descripción general de la aplicación

**Lead Intelligence Manager** es una aplicación de escritorio/local desarrollada en **Java** con interfaz **Swing**, conectada mediante **JDBC** a una base de datos **MySQL** local.

La finalidad de la aplicación es gestionar leads comerciales de forma centralizada, permitiendo:

- registrar nuevos leads;
- consultar el listado de leads;
- editar su información;
- eliminar leads;
- clasificar cada lead por fuente, estado, empresa y usuario responsable.

La aplicación está pensada para ejecutarse en un entorno local sencillo y realista, adecuado para una pequeña empresa o para un puesto de trabajo de uso interno.

---

## 2. Tipo de sistema donde se ejecuta

La aplicación se ejecuta en un **PC de usuario**.

### Justificación
Se ha elegido este tipo de sistema porque:

- se trata de una aplicación de escritorio desarrollada en Java;
- no requiere un despliegue web ni un servidor remoto para su funcionamiento básico;
- la base de datos puede ejecutarse en el mismo equipo;
- resulta una solución simple, realista y coherente con el nivel del proyecto.

En este escenario, el usuario instala la aplicación y el sistema gestor de base de datos en su propio equipo, y trabaja de forma local con la información.

---

## 3. Requisitos de hardware

### Requisitos mínimos
- **CPU:** procesador de 2 núcleos
- **RAM:** 4 GB
- **Almacenamiento:** 2 GB libres
- **Pantalla:** resolución mínima de 1366x768
- **Periféricos:** teclado y ratón

### Requisitos recomendados
- **CPU:** procesador de 4 núcleos
- **RAM:** 8 GB
- **Almacenamiento:** 5 GB libres
- **Pantalla:** resolución Full HD
- **Periféricos:** teclado y ratón

### Equipo real de desarrollo y ejecución
El proyecto se ha desarrollado y probado en el mismo equipo donde también se ejecuta la aplicación.

Características principales del equipo utilizado:
- **Procesador:** 13th Gen Intel(R) Core(TM) i7-13620H (2.40 GHz)
- **RAM instalada:** 32 GB
- **Arquitectura del sistema:** 64 bits, procesador basado en x64

### Justificación
La aplicación no requiere hardware avanzado, ya que su carga principal consiste en:

- ejecución de una interfaz Swing;
- acceso a base de datos local;
- operaciones CRUD básicas;
- validación y consulta de datos.

Por tanto, puede funcionar correctamente en un equipo de oficina estándar.

---

## 4. Sistema operativo utilizado y recomendado

### Sistema operativo principal
**Windows 11 Pro (versión 25H2)**

### Información del sistema operativo utilizado
- **Edición:** Windows 11 Pro
- **Versión:** 25H2
- **Versión del sistema operativo:** 26200.8037

### Otras opciones posibles
- Windows 10
- distribuciones Linux compatibles con Java y MySQL

### Justificación
Se ha utilizado Windows 11 Pro durante el desarrollo y pruebas del proyecto. Este sistema ofrece compatibilidad correcta con Java, IntelliJ IDEA, MySQL Server y MySQL Workbench, por lo que resulta adecuado para ejecutar la aplicación en un entorno local.

---

## 5. Software necesario

Para desarrollar y ejecutar correctamente el proyecto se ha utilizado el siguiente software:

- **JDK 21**
- **IntelliJ IDEA 2025.2.2 Community Edition**
- **MySQL Community Server 8.4 LTS**
- **MySQL Workbench 8.4**
- **Maven**
- **Git** (opcional, para control de versiones del repositorio)

---

## 6. Instalación del entorno

### 6.1. Instalación de Java
1. Instalar **JDK 21**.
2. Configurar el JDK en IntelliJ IDEA.
3. Verificar que el proyecto usa esa versión de Java.

### 6.2. Instalación de MySQL
1. Instalar **MySQL Community Server 8.4 LTS**.
2. Configurar un usuario administrador, por ejemplo `root`.
3. Definir una contraseña segura.
4. Comprobar que el servicio de MySQL queda iniciado correctamente.

### 6.3. Instalación de MySQL Workbench
1. Instalar **MySQL Workbench 8.4**.
2. Crear o abrir una conexión local.
3. Probar el acceso al servidor MySQL.

### 6.4. Descarga del proyecto
1. Clonar el repositorio o abrir la carpeta local del proyecto.
2. Comprobar que el archivo `pom.xml` está en la raíz del repositorio.
3. Abrir el proyecto con IntelliJ IDEA.

### 6.5. Instalación de dependencias
1. Cargar el proyecto como proyecto Maven.
2. Esperar a que Maven descargue las dependencias necesarias.
3. Verificar que se descarga el conector de MySQL para JDBC.

### 6.6. Creación de la base de datos
1. Abrir `sql/schema.sql` en MySQL Workbench.
2. Ejecutar el script completo para crear la base de datos y las tablas.
3. Abrir `sql/inserts.sql`.
4. Ejecutar el script para insertar datos de ejemplo.

### 6.7. Configuración de la conexión JDBC
1. Abrir la clase `DatabaseConnection.java`.
2. Configurar:
    - URL de conexión;
    - usuario de MySQL;
    - contraseña;
    - nombre de la base de datos.
3. Ejecutar una prueba de conexión.

### 6.8. Entorno unificado
Todo el entorno de desarrollo, la base de datos y la ejecución de la aplicación se realizan en el mismo equipo, sin necesidad de un servidor externo.

---

## 7. Ejecución de la aplicación

Para ejecutar la aplicación:

1. Abrir el proyecto en IntelliJ IDEA.
2. Asegurarse de que MySQL Server está en ejecución.
3. Confirmar que la base de datos `lead_intelligence_manager` existe y contiene las tablas.
4. Ejecutar la clase principal `Main.java`.

Al hacerlo, se abre la ventana principal de la aplicación, desde la que se puede:

- visualizar leads;
- añadir nuevos leads;
- editar leads existentes;
- eliminar leads.

---

## 8. Usuarios, permisos y estructura de carpetas

### 8.1. Usuarios del sistema
Dentro de la aplicación se manejan dos roles funcionales:

- **admin**
- **comercial**

Estos roles se almacenan en la base de datos y se relacionan con los leads y seguimientos.

### 8.2. Usuario del sistema operativo
La aplicación está pensada para ejecutarse con un **usuario normal del sistema**, no como administrador.

### Justificación
Esto reduce riesgos y resulta más coherente con una política básica de seguridad.

### 8.3. Estructura de carpetas del proyecto

#### Carpeta raíz
Contiene:
- `README.md`
- `.gitignore`
- `pom.xml`

#### `docs/`
Documentación por módulos:
- `docs/bbdd`
- `docs/xml`
- `docs/sistemas`
- `docs/mpo`
- `docs/entornos`

#### `sql/`
Scripts SQL:
- `schema.sql`
- `inserts.sql`
- `queries.sql`

#### `xml/`
Archivos XML y XSD del proyecto:
- `leads.xml`
- `leads.xsd`
- `leads_invalido.xml`

#### `src/main/java/`
Código fuente Java organizado por paquetes:
- `app`
- `dao`
- `model`
- `util`
- `view`

#### `assets/capturas/`
Capturas de apoyo del proyecto.

### 8.4. Ubicación de los datos
Los datos de la aplicación se almacenan en la base de datos local **lead_intelligence_manager** dentro de MySQL.

### 8.5. Copias de seguridad
Las copias de seguridad se pueden guardar en una carpeta local específica, por ejemplo:

`C:\backups\lead-intelligence-manager\`

o cualquier ruta equivalente definida por el usuario.

---

## 9. Mantenimiento básico

Para mantener la aplicación en buen estado se recomienda:

### 9.1. Revisiones periódicas
- comprobar que MySQL Server está activo;
- verificar que la conexión JDBC sigue funcionando;
- revisar que el proyecto compila sin errores;
- comprobar el correcto funcionamiento de las operaciones CRUD.

### 9.2. Actualizaciones
- actualizar Java si fuera necesario;
- actualizar IntelliJ IDEA o MySQL Workbench solo si no rompe compatibilidad;
- mantener el proyecto sincronizado con GitHub.

### 9.3. Copias de seguridad
- exportar periódicamente la base de datos;
- guardar scripts SQL y documentación;
- mantener una copia del repositorio del proyecto.

### 9.4. Qué hacer si falla
Si la aplicación deja de funcionar correctamente:

1. comprobar si MySQL está iniciado;
2. revisar usuario, contraseña y URL de conexión JDBC;
3. revisar que la base de datos existe;
4. ejecutar de nuevo `schema.sql` e `inserts.sql` en caso de entorno nuevo;
5. revisar mensajes de error en IntelliJ;
6. restaurar una copia de seguridad si fuera necesario.

---

## 10. Protección mínima del sistema

Las medidas mínimas de protección propuestas para este proyecto son:

- uso de contraseña para el usuario de MySQL;
- ejecución con usuario normal del sistema, sin privilegios de administrador;
- separación entre aplicación y base de datos;
- control básico de roles en la tabla de usuarios;
- mantenimiento de copias de seguridad;
- no guardar información especialmente sensible en texto plano fuera del entorno de pruebas.

Aunque se trata de un proyecto académico, estas medidas permiten justificar una base mínima de seguridad y un entorno de ejecución razonable.

---

## 11. Evidencias de funcionamiento

Las evidencias del proyecto se incluirán en la carpeta:

`docs/sistemas/capturas/`

Capturas recomendadas:

- proyecto abierto en IntelliJ IDEA;
- estructura del proyecto y paquetes Java;
- conexión local a MySQL en Workbench;
- ejecución correcta de `schema.sql`;
- ejecución correcta de `inserts.sql`;
- ventana principal de la aplicación;
- listado de leads cargado desde la base de datos;
- formulario de alta de lead;
- edición de un lead;
- eliminación de un lead;
- validación del XML y errores del XML inválido.

Estas evidencias servirán para demostrar que:

- el entorno está correctamente configurado;
- la aplicación arranca;
- la base de datos funciona;
- el proyecto realiza operaciones reales.

---

## 12. Conclusión

El entorno de ejecución propuesto para **Lead Intelligence Manager** es coherente con el tipo de aplicación desarrollada. Se trata de una aplicación de escritorio/local, ejecutada en un PC de usuario, utilizando Java con Swing, conexión JDBC y base de datos MySQL local.

Además, el desarrollo y la ejecución se han realizado en un entorno real con Windows 11 Pro, JDK 21, IntelliJ IDEA 2025.2.2 Community Edition, MySQL Community Server 8.4 LTS y MySQL Workbench 8.4, todo ello en el mismo equipo. Esto permite justificar de forma práctica la viabilidad y funcionamiento del proyecto.