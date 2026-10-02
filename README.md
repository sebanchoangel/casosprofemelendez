# Caso 3 — Motor flexible de asignación de descuentos

Refactorización del módulo de cálculo de descuentos de la plataforma
**TurismoCundinamarca**, aplicando el patrón de diseño **Strategy** (GoF —
categoría de Comportamiento), como parte de la actividad **R1-A2-S7**
(Patrones GoF como soporte al diseño interno de componentes).

## Problema original

La clase `CalculadorDescuento` resolvía el descuento aplicable mediante una
cadena de condicionales `if / else if` sobre el tipo de descuento (texto),
con los porcentajes codificados directamente en el método. Mercadeo anunció
que las políticas de descuento cambiarán con frecuencia, lo que hacía que
cada nueva regla obligara a modificar y recompilar la única clase del
sistema.

## Solución aplicada

Se extrajo cada regla de descuento a su propia clase (`ConcreteStrategy`),
todas implementando la interfaz `PoliticaDescuento` (`Strategy`).
`CalculadorDescuento` pasó a actuar como `Context`: mantiene un registro
(`Map<String, PoliticaDescuento>`) de políticas y delega el cálculo, sin
conocer ninguna regla concreta. Se agregaron los métodos
`registrarPolitica(tipo, politica)` y `eliminarPolitica(tipo)` para poder
activar, reemplazar o incorporar políticas en tiempo de ejecución, sin tocar
el componente que procesa la compra.

## Estructura del proyecto

```
src/
├── main/java/com/turismocundinamarca/descuentos/
│   ├── PoliticaDescuento.java            (interfaz Strategy)
│   ├── CalculadorDescuento.java          (Context refactorizado)
│   ├── DescuentoFrecuente.java           (10 % — regla existente)
│   ├── DescuentoTemporadaBaja.java       (15 % — regla existente)
│   ├── DescuentoConvenio.java            (20 % — regla existente)
│   ├── DescuentoAniversario.java         (25 % — regla nueva)
│   ├── DescuentoRegional.java            (12 % — regla nueva)
│   ├── DescuentoMunicipio.java           ( 8 % — regla nueva)
│   └── DescuentoCajaCompensacion.java    (18 % — regla nueva)
└── test/java/com/turismocundinamarca/descuentos/
    └── CalculadorDescuentoTest.java      (JUnit 5: línea base + nuevo requisito de cambio)
```

## Cómo ejecutar las pruebas

Proyecto Maven (agregar si no existe un `pom.xml`):

```bash
mvn test
```

O con Gradle:

```bash
gradle test
```

Se requiere la dependencia `junit-jupiter` (JUnit 5) en el classpath de
pruebas.

## Historial de cambios (commits)

El historial de commits de este repositorio documenta paso a paso la ruta de
aprendizaje seguida (código problemático → code smells → causa del
acoplamiento → principio de diseño → comparar alternativas → aplicar patrón
→ refactorizar → ejecutar pruebas), de acuerdo con la guía R1-A2-S7.

## Documento técnico

El análisis completo (code smells, principios de diseño, comparación de
alternativas, diagrama UML, evaluación de impacto y conexión arquitectónica)
está documentado en `Expediente_Tecnico_Caso3_APA7.docx`.
