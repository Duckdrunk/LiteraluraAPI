# 📚 LiterAlura

Proyecto desarrollado en **Java con Spring Boot** que permite gestionar libros y autores, realizar búsquedas por distintos criterios y consultar información histórica de autores y obras almacenadas en una base de datos relacional.

El proyecto aplica **buenas prácticas de arquitectura**, usando DTOs, mappers y repositorios con Spring Data JPA.

---

## 🚀 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Hibernate
- JDBC

---

## 🧠 Funcionalidades principales

- 📖 Listar todos los libros registrados
- 🔎 Buscar libros por título (insensible a mayúsculas/minúsculas)
- 🌍 Buscar libros por idioma
- ✍️ Consultar autores vivos en un año determinado
- 📚 Obtener los libros asociados a un autor
- 🧩 Uso de DTOs para desacoplar entidades de la capa de presentación
- 🔄 Conversión Entity ↔ DTO mediante Mappers

---

## ⚙️ Configuración del proyecto

### 1️⃣ Clonar el repositorio
```bash
    git clone https://github.com/Duckdrunk/LiteraluraAPI.git
    
    cd literalura
```
### 2️⃣ Configurar la base de datos
- Crear la base de datos
- Ejecutar en pgAdmin 4 el archivo db/schema.sql
- Configurar application.properties

### 3️⃣ Ejecutar el proyecto
Con Maven:
```bash
    mvn spring-boot:run
```