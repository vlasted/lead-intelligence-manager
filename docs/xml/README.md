# XML y XSD

En esta carpeta se recoge el trabajo correspondiente al módulo de **Lenguajes de Marcas y Sistemas de Gestión de Información** dentro del proyecto intermodular **Lead Intelligence Manager**.

## Objetivo

El objetivo de esta parte del proyecto es representar información del sistema en formato XML y validarla mediante un esquema XSD.

Para ello se ha tomado como referencia una parte real del proyecto: los **leads comerciales** gestionados por la aplicación.

## Archivos incluidos

### `leads.xml`
Archivo XML válido que representa una colección de leads del sistema.

Cada lead incluye información como:
- nombre;
- apellidos;
- email;
- teléfono;
- cargo;
- interés;
- puntuación;
- prioridad;
- fecha de registro;
- observaciones;
- empresa;
- fuente;
- estado;
- usuario responsable.

### `leads.xsd`
Esquema XSD que define la estructura y restricciones que debe cumplir el XML.

Entre las validaciones aplicadas se encuentran:
- atributo `id` obligatorio en cada `lead`;
- elementos obligatorios;
- prioridad limitada a `alta`, `media` o `baja`;
- puntuación entre 0 y 100;
- teléfono con 9 dígitos;
- email con formato válido;
- fecha con formato `dateTime`.

### `leads_invalido.xml`
Archivo XML creado a propósito con errores para demostrar que el esquema detecta datos inválidos.

### `validacion.md`
Documento donde se explica el proceso de validación y el resultado obtenido con el XML válido y el XML inválido.

## Relación con el proyecto

Esta parte se integra con la lógica general de **Lead Intelligence Manager**, ya que los datos usados en XML representan la misma información que aparece en la base de datos y en la aplicación Java.

De este modo, XML no se utiliza como algo aislado, sino como una representación estructurada de los leads del sistema.

## Finalidad práctica

El uso de XML y XSD permite:

- representar datos de forma estructurada;
- intercambiar información entre sistemas;
- validar que los datos cumplen unas reglas;
- detectar errores de formato o de contenido antes de procesarlos.

## Estado actual

Actualmente esta parte incluye:

- XML válido del proyecto;
- XSD de validación;
- XML inválido de prueba;
- documento explicativo de validación.

## Conclusión

La carpeta XML recoge una representación estructurada y validada de parte de la información del proyecto. Esto refuerza la coherencia del intermodular y demuestra la aplicación práctica de XML/XSD sobre un caso real relacionado con la app.