# Validación XML

En esta carpeta se incluye la parte correspondiente a **Lenguajes de Marcas**, utilizando un archivo XML con datos del proyecto y un esquema XSD para validar su estructura.

## Archivos utilizados

- `leads.xml`  
  Archivo XML válido que representa varios leads del sistema.

- `leads.xsd`  
  Esquema XSD que define la estructura, tipos de datos y restricciones que debe cumplir el XML.

- `leads_invalido.xml`  
  Archivo XML creado a propósito con errores, para demostrar que el esquema detecta datos no válidos.

## Objetivo de la validación

El objetivo es comprobar que los datos exportados en formato XML cumplen unas reglas estructurales y de contenido.

En este proyecto, el XSD valida aspectos como:

- que cada `lead` tenga un atributo `id`;
- que existan los elementos obligatorios;
- que `prioridad` solo pueda ser `alta`, `media` o `baja`;
- que `puntuacion` esté entre 0 y 100;
- que `telefono` tenga 9 dígitos;
- que `email` siga un formato válido;
- que `fechaRegistro` tenga formato `dateTime`.

## Resultado de la validación

### XML válido
El archivo `leads.xml` cumple correctamente la estructura definida en `leads.xsd`, por lo que se considera **válido**.

### XML inválido
El archivo `leads_invalido.xml` presenta varios errores intencionados, por ejemplo:

- email con formato incorrecto;
- teléfono demasiado corto;
- puntuación fuera del rango permitido;
- prioridad no admitida por el esquema;
- fecha con formato incorrecto.

Gracias a ello, el editor muestra errores de validación, lo que demuestra que el XSD está funcionando correctamente.

## Conclusión

La validación mediante XSD permite asegurar que los datos XML del proyecto mantienen una estructura coherente y unos valores correctos. Esto resulta útil para intercambiar información entre sistemas, evitar errores y reforzar la consistencia de los datos del proyecto.