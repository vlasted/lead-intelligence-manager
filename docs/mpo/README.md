# MPO - Mejora estructural del código

En esta carpeta se recoge la mejora realizada en la parte de programación como trabajo de **MPO** dentro del proyecto intermodular **Lead Intelligence Manager**.

## Objetivo de la mejora

El objetivo de esta mejora ha sido reducir duplicación de código y mejorar la organización interna de la interfaz gráfica del proyecto.

Durante el desarrollo de la aplicación se detectó que las ventanas de:

- alta de leads (`AddLeadFrame`)
- edición de leads (`EditLeadFrame`)

tenían una estructura muy parecida:

- mismos campos de formulario;
- mismos combos;
- mismo layout;
- lógica muy similar para cargar y recoger datos.

Esto generaba código repetido, más difícil de mantener y más propenso a errores.

## Solución aplicada

Se creó una clase reutilizable llamada:

`LeadFormPanel`

Esta clase concentra en un único componente:

- los campos del formulario;
- los combos de selección;
- la estructura visual del formulario;
- la carga de datos desde un objeto `Lead`;
- la lectura de datos del formulario hacia un objeto `Lead`.

De esta forma:

- `AddLeadFrame` reutiliza `LeadFormPanel` para crear nuevos leads;
- `EditLeadFrame` reutiliza el mismo panel para editar leads existentes.

## Ventajas obtenidas

La refactorización aporta varias mejoras:

- menos código duplicado;
- mejor mantenimiento;
- mayor claridad estructural;
- reutilización real de componentes;
- menor riesgo de inconsistencias entre formularios.

## Antes de la mejora

Antes de esta refactorización:

- cada ventana tenía sus propios campos y combos;
- la lógica del formulario estaba repetida;
- cualquier cambio obligaba a modificar varias clases.

## Después de la mejora

Después de la refactorización:

- existe un único panel reutilizable;
- la lógica del formulario está centralizada;
- las ventanas de alta y edición son más limpias y más simples.

## Diagrama simple de la mejora

```mermaid
flowchart TD
    AddLeadFrame --> LeadFormPanel
    EditLeadFrame --> LeadFormPanel
    LeadFormPanel --> Lead
    AddLeadFrame --> LeadDAO
    EditLeadFrame --> LeadDAO