# BookLog API - Documentación Completa

## 📚 Descripción

API REST desarrollada con **Spring Boot 3** y **MySQL** para gestionar libros, colecciones, notas y citas literarias. Implementa patrones empresariales como SOLID principles, validación de datos, manejo de excepciones globales y transacciones.

## 🛠️ Requisitos Previos

- **Java 17+**
- **MySQL 8.0+**
- **Maven o Gradle**
- **Git** (opcional)

## 📋 Características

✅ **CRUD completo** para todas las entidades
✅ **Validación de datos** con Spring Validation
✅ **Manejo de excepciones global** con `GlobalExceptionHandler`
✅ **DTOs** para separar modelos del controlador
✅ **Transacciones** con `@Transactional`
✅ **Relaciones JPA** (OneToMany, ManyToMany)
✅ **Búsquedas avanzadas** (por título, autor, estado)
✅ **CORS** habilitado
✅ **Logs** configurados
✅ **Estructura modular** (Controllers, Services, Repositories, Mappers)

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <tu-repositorio>
cd api-movil-gestor-libros
```

### 2. Configurar la base de datos

Crear la base de datos en MySQL:
```sql
CREATE DATABASE booklog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar `application.properties`

El archivo ya está configurado en `src/main/resources/application.properties`. Modificar si es necesario:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/booklog_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# Hibernate
spring.jpa.hibernate.ddl-auto=update
```

### 4. Instalar dependencias y ejecutar

**Con Maven:**
```bash
mvn clean install
mvn spring-boot:run
```

**Con Gradle:**
```bash
gradle clean build
gradle bootRun
```

La API estará disponible en: `http://localhost:8080`

## 📡 Endpoints de la API

### 🔗 Health Check
- `GET /api/health` - Verificar estado de la API
- `GET /api/info` - Información de la aplicación

### 👤 Usuarios (`/api/users`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear usuario |
| PUT | `/{id}` | Actualizar usuario |
| GET | `/{id}` | Obtener usuario por ID |
| GET | `/by-username/{nombreUsuario}` | Obtener usuario por nombre |
| GET | `/by-email/{correo}` | Obtener usuario por correo |
| GET | `/` | Obtener todos los usuarios |
| DELETE | `/{id}` | Eliminar usuario |
| GET | `/exists/username/{nombreUsuario}` | Verificar si existe el usuario |
| GET | `/exists/email/{correo}` | Verificar si existe el correo |

**Ejemplo POST:**
```json
{
  "nombreUsuario": "juan_perez",
  "correo": "juan@example.com",
  "hashContrasena": "password123"
}
```

### 📖 Libros (`/api/books`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear libro |
| PUT | `/{id}` | Actualizar libro |
| GET | `/{id}` | Obtener libro por ID |
| GET | `/usuario/{usuarioId}` | Obtener libros del usuario |
| GET | `/usuario/{usuarioId}/estado/{estado}` | Obtener libros por estado |
| GET | `/serie/{serieId}` | Obtener libros de una serie |
| GET | `/search/titulo?usuarioId=1&titulo=Harry` | Buscar por título |
| GET | `/search/autor?usuarioId=1&autor=Rowling` | Buscar por autor |
| PATCH | `/{id}/progress?progreso=50` | Actualizar progreso de lectura |
| DELETE | `/{id}` | Eliminar libro |

**Ejemplo POST:**
```json
{
  "usuarioId": 1,
  "rutaArchivo": "/ruta/archivo.pdf",
  "nombreArchivo": "harry_potter.pdf",
  "titulo": "Harry Potter y la Piedra Filosofal",
  "formato": "PDF",
  "autor": "J.K. Rowling",
  "progreso": 45.5,
  "estado": "LEYENDO"
}
```

### 📚 Series (`/api/series`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear serie |
| PUT | `/{id}` | Actualizar serie |
| GET | `/{id}` | Obtener serie por ID |
| GET | `/usuario/{usuarioId}` | Obtener series del usuario |
| DELETE | `/{id}` | Eliminar serie |

**Ejemplo POST:**
```json
{
  "usuarioId": 1,
  "nombre": "Harry Potter"
}
```

### 🎯 Colecciones (`/api/colecciones`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear colección |
| PUT | `/{id}` | Actualizar colección |
| GET | `/{id}` | Obtener colección por ID |
| GET | `/usuario/{usuarioId}` | Obtener colecciones del usuario |
| DELETE | `/{id}` | Eliminar colección |

### 📝 Notas (`/api/notes`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear nota |
| PUT | `/{id}` | Actualizar nota |
| GET | `/{id}` | Obtener nota por ID |
| GET | `/lectura/{lecturaId}` | Obtener notas de un libro |
| DELETE | `/{id}` | Eliminar nota |

**Ejemplo POST:**
```json
{
  "lecturaId": 1,
  "contenido": "Excelente descripción del mundo mágico",
  "referenciaPagina": "pag. 45"
}
```

### 💬 Citas (`/api/quotes`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Crear cita |
| PUT | `/{id}` | Actualizar cita |
| GET | `/{id}` | Obtener cita por ID |
| GET | `/lectura/{lecturaId}` | Obtener citas de un libro |
| DELETE | `/{id}` | Eliminar cita |

**Ejemplo POST:**
```json
{
  "lecturaId": 1,
  "textoCitado": "La magia existe, solo hay que saber verla",
  "referenciaPagina": "pag. 72",
  "comentario": "Frase inspiradora"
}
```

### 🔗 Lectura-Colección (`/api/lectura-coleccion`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/` | Agregar libro a colección |
| DELETE | `/{lecturaId}/{coleccionId}` | Remover libro de colección |
| GET | `/coleccion/{coleccionId}` | Obtener libros de una colección |
| GET | `/lectura/{lecturaId}` | Obtener colecciones de un libro |

**Ejemplo POST:**
```json
{
  "lecturaId": 1,
  "coleccionId": 2
}
```

## 🔒 Validaciones

### Estados válidos para libros:
- `PENDIENTE` (por defecto)
- `LEYENDO`
- `COMPLETADO`
- `PAUSADO`

### Validaciones de datos:
- Progreso: 0-100
- Nombres de usuario: 3-100 caracteres
- Correo: formato válido de email
- Contraseña: mínimo 6 caracteres

## 📊 Estructura de la Base de Datos

```sql
-- Tabla Usuarios
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(100) UNIQUE NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    hashContrasena VARCHAR(255) NOT NULL,
    fechaCreacion BIGINT NOT NULL
);

-- Tabla Series
CREATE TABLE series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuarioId BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fechaCreacion BIGINT NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla Lecturas (Libros)
CREATE TABLE lecturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuarioId BIGINT NOT NULL,
    rutaArchivo VARCHAR(500) NOT NULL,
    nombreArchivo VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    formato VARCHAR(50) NOT NULL,
    autor VARCHAR(255),
    serieId BIGINT,
    progreso FLOAT NOT NULL DEFAULT 0,
    estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    coverPath VARCHAR(500),
    fechaCreacion BIGINT NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla Colecciones
CREATE TABLE colecciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuarioId BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fechaCreacion BIGINT NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla Lectura-Colección (Relación Many-to-Many)
CREATE TABLE lecturas_colecciones (
    lecturaId BIGINT NOT NULL,
    coleccionId BIGINT NOT NULL,
    fechaAgregado BIGINT NOT NULL,
    PRIMARY KEY (lecturaId, coleccionId),
    FOREIGN KEY (lecturaId) REFERENCES lecturas(id) ON DELETE CASCADE,
    FOREIGN KEY (coleccionId) REFERENCES colecciones(id) ON DELETE CASCADE
);

-- Tabla Notas
CREATE TABLE notas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecturaId BIGINT NOT NULL,
    contenido TEXT NOT NULL,
    referenciaPagina VARCHAR(100),
    fechaCreacion BIGINT NOT NULL,
    FOREIGN KEY (lecturaId) REFERENCES lecturas(id) ON DELETE CASCADE
);

-- Tabla Citas
CREATE TABLE citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecturaId BIGINT NOT NULL,
    textoCitado TEXT NOT NULL,
    referenciaPagina VARCHAR(100),
    comentario TEXT,
    fechaCreacion BIGINT NOT NULL,
    FOREIGN KEY (lecturaId) REFERENCES lecturas(id) ON DELETE CASCADE
);
```

## 🔧 Manejo de Errores

Todos los errores devuelven un JSON con la siguiente estructura:

```json
{
  "timestamp": "2026-04-12T10:30:45",
  "status": 404,
  "error": "Resource Not Found",
  "message": "Usuario no encontrado con ID: 999",
  "path": "/api/users/999"
}
```

### Códigos de error:
- **400**: Bad Request (validación fallida)
- **404**: Not Found (recurso no encontrado)
- **409**: Conflict (recurso duplicado)
- **500**: Internal Server Error

## 🧪 Pruebas con cURL

### Crear usuario:
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "correo": "juan@example.com",
    "hashContrasena": "password123"
  }'
```

### Obtener todos los usuarios:
```bash
curl http://localhost:8080/api/users
```

### Crear libro:
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "rutaArchivo": "/ruta/libro.pdf",
    "nombreArchivo": "libro.pdf",
    "titulo": "Mi Libro",
    "formato": "PDF",
    "autor": "Autor",
    "progreso": 0,
    "estado": "PENDIENTE"
  }'
```

## 📦 Dependencias Principales

```gradle
- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-validation
- com.mysql:mysql-connector-j
- org.projectlombok:lombok
```

## 🏗️ Arquitectura

```
api-movil-gestor-libros/
├── src/main/java/org/example/
│   ├── BookLogApplication.java          (Aplicación principal)
│   ├── controller/                      (Endpoints REST)
│   ├── service/                         (Lógica de negocio)
│   │   ├── *Service.java               (Interfaces)
│   │   └── impl/                       (Implementaciones)
│   ├── repository/                     (Acceso a datos)
│   ├── model/                          (Entidades JPA)
│   ├── dto/                            (Data Transfer Objects)
│   ├── mapper/                         (Conversión Entity <-> DTO)
│   └── exception/                      (Manejo de errores)
└── resources/
    └── application.properties           (Configuración)
```

## 📝 Notas Importantes

1. **Seguridad**: En producción, no guardar contraseñas en texto plano. Usar BCrypt u otro algoritmo.
2. **Logs**: Configurar niveles de logs según necesidad.
3. **CORS**: Actualmente acepta todas las fuentes. Restringir en producción.
4. **Validación**: Todos los DTOs incluyen validaciones con anotaciones de Jakarta.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Abrir un issue o pull request con mejoras.

## 📄 Licencia

Este proyecto está bajo licencia MIT.

## 👨‍💻 Autor

Desarrollado como API REST para BookLog.

---

**Última actualización:** 12/04/2026


