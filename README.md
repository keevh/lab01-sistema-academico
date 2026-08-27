# Lab 01 - Sistema Academico

Proyecto de Ingeniería de Software III. Implementación del módulo de estudiantes usando arquitectura por capas con Spring Boot y base de datos H2 en memoria.

## Tecnologías

- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- H2 (en memoria)
- Maven

## Estructura

```
co.edu.demoacademico
├── controller   (capa de presentación)
├── service      (capa de lógica de negocio)
├── repository   (capa de acceso a datos)
└── model        (entidades JPA)
```

## Endpoints

| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /api/estudiantes | Crear estudiante |
| GET | /api/estudiantes | Listar todos |
| GET | /api/estudiantes/buscar?email= | Buscar por email |

## Cómo ejecutar

```bash
./mvnw spring-boot:run
```

La aplicación corre en `http://localhost:8080`.

La consola H2 está disponible en `http://localhost:8080/h2-console` con la URL `jdbc:h2:mem:demoacademico`, usuario `sa` y contraseña vacía.
