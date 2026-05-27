# Proyecto de Automatización E2E - Sistema Bancario Parabank 

Este repositorio contiene la suite de pruebas automatizadas de extremo a extremo (E2E) para los flujos críticos de negocio de la plataforma bancaria de pruebas **Parabank**. El objetivo principal es garantizar la calidad, robustez y repetibilidad del software mediante la validación de sus componentes de interfaz y lógica transaccional.

La arquitectura del proyecto está diseñada bajo los más altos estándares de Aseguramiento de la Calidad de Software (SQA), implementando el patrón de diseño **Screenplay**.

---

## Tecnologías y Frameworks Utilizados

* **Java 17**: Lenguaje de programación robusto y tipado para la infraestructura de pruebas.
* **Gradle**: Gestor de dependencias y motor de construcción del proyecto.
* **Serenity BDD**: Framework de automatización que gestiona el estado de las pruebas y genera reportes vivos enriquecidos.
* **Selenium WebDriver**: Motor para la interacción y control del navegador web (Chrome).
* **Cucumber / Gherkin**: Lenguaje DSL (Domain Specific Language) para la definición de escenarios de prueba orientados al negocio.

---

## Arquitectura del Proyecto (Pattern Screenplay)

El proyecto adopta el patrón Screenplay debido a su alta mantenibilidad, legibilidad y reutilización de código frente al patrón tradicional Page Object Model (POM).

```text
 └── src
     ├── main/java/.../moduloIngreso
     │    ├── userinterfaces/   # Mapeo exclusivo de localizadores HTML (XPaths)
     │    ├── tasks/            # Acciones de alto nivel ejecutadas por los actores
     │    └── questions/        # Verificaciones y aserciones basadas en la interfaz
     └── test/
          ├── java/.../stepdefinitions/  # Conectores entre Gherkin y código Java
          └── resources/features/         # Escenarios de negocio escritos en Gherkin
```

## Cómo Ejecutar las Pruebas Localmente

Para limpiar el proyecto, compilar y correr la suite completa de pruebas ejecutando el navegador en segundo plano, abre una terminal en la raíz del proyecto y usa el siguiente comando:

```bash
gradle clean test