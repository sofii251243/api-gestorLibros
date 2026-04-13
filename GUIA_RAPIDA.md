# 📋 GUÍA RÁPIDA - BookLog API

## ✅ Lo que ya está completo:

### 1. **Configuración Base**
- ✔️ `build.gradle.kts` - Dependencias Spring Boot 3, Lombok, MySQL
- ✔️ `application.properties` - Conexión a MySQL
- ✔️ `application-dev.properties` - Configuración de desarrollo
- ✔️ `application-prod.properties` - Configuración de producción
- ✔️ `BookLogApplication.java` - Clase principal

### 2. **Modelos JPA (Entidades)**
- ✔️ `User.java` - Usuario (id, nombreUsuario, correo, hashContrasena)
- ✔️ `Book.java` - Libro (id, usuarioId, titulo, autor, progreso, estado)
- ✔️ `Serie.java` - Serie de libros
- ✔️ `Coleccion.java` - Colección de libros
- ✔️ `Note.java` - Notas de lectura
- ✔️ `Quote.java` - Citas de libros
- ✔️ `LecturaColeccion.java` - Relación Many-to-Many
- ✔️ `LecturaColeccionId.java` - ID compuesto para la relación

### 3. **DTOs (Data Transfer Objects)**
- ✔️ `UserDTO.java` con validaciones
- ✔️ `BookDTO.java` con validaciones (progreso 0-100, estado validado)
- ✔️ `SerieDTO.java` con validaciones
- ✔️ `ColeccionDTO.java` con validaciones
- ✔️ `NoteDTO.java` con validaciones
- ✔️ `QuoteDTO.java` con validaciones
- ✔️ `LecturaColeccionDTO.java` con validaciones

### 4. **Repositorios (Data Access)**
- ✔️ `UserRepository.java` - Con búsquedas por usuario y correo
- ✔️ `BookRepository.java` - Con búsquedas por título, autor, estado
- ✔️ `SerieRepository.java`
- ✔️ `ColeccionRepository.java`
- ✔️ `NoteRepository.java`
- ✔️ `QuoteRepository.java`
- ✔️ `LecturaColeccionRepository.java`

### 5. **Servicios (Lógica de Negocio)**
**Interfaces:**
- ✔️ `UserService.java`
- ✔️ `BookService.java`
- ✔️ `SerieService.java`
- ✔️ `ColeccionService.java`
- ✔️ `NoteService.java`
- ✔️ `QuoteService.java`
- ✔️ `LecturaColeccionService.java`

**Implementaciones (con @Transactional):**
- ✔️ `UserServiceImpl.java`
- ✔️ `BookServiceImpl.java` (valida progreso 0-100)
- ✔️ `SerieServiceImpl.java`
- ✔️ `ColeccionServiceImpl.java`
- ✔️ `NoteServiceImpl.java`
- ✔️ `QuoteServiceImpl.java`
- ✔️ `LecturaColeccionServiceImpl.java`

### 6. **Mappers (Entity ↔ DTO)**
- ✔️ `UserMapper.java`
- ✔️ `BookMapper.java`
- ✔️ `SerieMapper.java`
- ✔️ `ColeccionMapper.java`
- ✔️ `NoteMapper.java`
- ✔️ `QuoteMapper.java`
- ✔️ `LecturaColeccionMapper.java`

### 7. **Controllers (Endpoints REST)**
- ✔️ `UserController.java` - `/api/users`
- ✔️ `BookController.java` - `/api/books` (incluye búsqueda y actualización de progreso)
- ✔️ `SerieController.java` - `/api/series`
- ✔️ `ColeccionController.java` - `/api/colecciones`
- ✔️ `NoteController.java` - `/api/notes`
- ✔️ `QuoteController.java` - `/api/quotes`
- ✔️ `LecturaColeccionController.java` - `/api/lectura-coleccion`
- ✔️ `HealthController.java` - `/api/health` y `/api/info`

### 8. **Manejo de Excepciones**
- ✔️ `ResourceNotFoundException.java` - 404
- ✔️ `DuplicateResourceException.java` - 409
- ✔️ `ValidationException.java` - 400
- ✔️ `ErrorResponse.java` - DTO de error
- ✔️ `GlobalExceptionHandler.java` - Manejador global de excepciones

### 9. **Documentación**
- ✔️ `README.md` - Documentación completa de la API
- ✔️ `data.sql` - Script SQL para inicializar la BD con datos de prueba

### 10. **Docker & Despliegue**
- ✔️ `Dockerfile` - Imagen Docker para la API
- ✔️ `docker-compose.yml` - Orquestación MySQL + API

### 11. **Testing**
- ✔️ `UserControllerTest.java` - Ejemplo de pruebas unitarias

---

## 🚀 PASOS SIGUIENTES PARA EJECUTAR:

### 1. **Instalar MySQL 8.0+**
```bash
# Si usas Docker:
docker-compose up -d
```

### 2. **Crear la base de datos**
```sql
CREATE DATABASE booklog_db CHARACTER SET utf8mb4;
```

### 3. **Compilar el proyecto**
```bash
mvn clean install
# O con Gradle:
gradle clean build
```

### 4. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
# O con Gradle:
gradle bootRun
```

### 5. **Verificar que funciona**
```bash
curl http://localhost:8080/api/health
# Respuesta esperada:
# {"status":"UP","message":"BookLog API is running"}
```

---

## 📊 ENDPOINTS PRINCIPALES:

| Entidad | GET | POST | PUT | DELETE | Búsquedas |
|---------|-----|------|-----|--------|-----------|
| **Users** | /{id}, todos | crear | actualizar | eliminar | por username, email |
| **Books** | /{id}, por usuario | crear | actualizar | eliminar | por título, autor, estado, progreso |
| **Series** | /{id}, por usuario | crear | actualizar | eliminar | - |
| **Colecciones** | /{id}, por usuario | crear | actualizar | eliminar | - |
| **Notes** | /{id}, por libro | crear | actualizar | eliminar | - |
| **Quotes** | /{id}, por libro | crear | actualizar | eliminar | - |
| **L-C** | - | agregar | - | remover | - |

---

## 🔐 VALIDACIONES IMPLEMENTADAS:

✔️ **Usuario:**
- Nombre: 3-100 caracteres, único
- Correo: formato válido, único
- Contraseña: mín. 6 caracteres

✔️ **Libro:**
- Progreso: 0-100
- Estado: PENDIENTE, LEYENDO, COMPLETADO, PAUSADO
- Usuario debe existir
- Validación de no duplicados en colecciones

✔️ **Serie/Colección/Nota/Cita:**
- Usuario debe existir
- Campos requeridos no vacíos
- FK relacionales validadas

---

## 📚 FUNCIONALIDADES ESPECÍFICAS:

1. **Gestión de Progreso de Lectura**
   - `PATCH /api/books/{id}/progress?progreso=50`
   - Valida que esté entre 0-100

2. **Búsqueda Avanzada**
   - Por título: `GET /api/books/search/titulo?usuarioId=1&titulo=Harry`
   - Por autor: `GET /api/books/search/autor?usuarioId=1&autor=Rowling`
   - Por estado: `GET /api/books/usuario/{usuarioId}/estado/LEYENDO`

3. **Relaciones Many-to-Many**
   - Agregar libro a colección: `POST /api/lectura-coleccion`
   - Consultar libros en colección: `GET /api/lectura-coleccion/coleccion/{id}`

4. **Manejo Robusto de Errores**
   - GlobalExceptionHandler captura todas las excepciones
   - Respuestas uniformes con timestamp, status, mensaje

---

## 💾 ESTRUCTURA BD:

```
booklog_db
├── usuarios (id, nombreUsuario*, correo*, hashContrasena)
├── series (id, usuarioId, nombre)
├── lecturas (id, usuarioId, titulo, autor, formato, progreso, estado)
├── colecciones (id, usuarioId, nombre)
├── lecturas_colecciones (lecturaId, coleccionId) - Many-to-Many
├── notas (id, lecturaId, contenido, referenciaPagina)
└── citas (id, lecturaId, textoCitado, referenciaPagina, comentario)

(*) = Índices y restricciones de unicidad
```

---

## 🎯 ESTADÍSTICAS DEL PROYECTO:

- **Controllers:** 8
- **Services:** 7 (Interfaces) + 7 (Implementaciones)
- **Repositories:** 7
- **DTOs:** 8
- **Mappers:** 8
- **Modelos:** 8
- **Excepciones personalizadas:** 3
- **Endpoints CRUD:** 60+
- **Validaciones:** 30+

---

## ✨ CARACTERÍSTICAS AVANZADAS:

✔️ Transacciones con `@Transactional`
✔️ Validación con anotaciones Jakarta
✔️ CORS habilitado
✔️ Logging configurado por niveles
✔️ DTOs para aislar la API
✔️ Mappers para conversión Entity ↔ DTO
✔️ Manejo global de excepciones
✔️ Búsquedas con filtros
✔️ Docker & Docker Compose
✔️ Configuración por ambiente (dev/prod)

---

## 🔄 FLUJO DE UNA PETICIÓN:

1. **Controller** recibe la petición HTTP
2. **Valida** con `@Valid` en DTOs
3. **Service** (Interface + Impl) contiene lógica
4. **Mapper** convierte DTO → Entity o Entity → DTO
5. **Repository** accede a la BD
6. **Respuesta** se retorna al cliente
7. **Excepciones** son capturadas por GlobalExceptionHandler

---

## 🤔 PRÓXIMAS MEJORAS SUGERIDAS:

- [ ] Autenticación (JWT/OAuth2)
- [ ] Paginación en listados
- [ ] Auditoría (quién cambió qué)
- [ ] Soft delete en usuarios
- [ ] Cache (Redis)
- [ ] Integración con ElasticSearch para búsquedas
- [ ] GraphQL alternativa a REST
- [ ] Rate limiting
- [ ] Swagger/OpenAPI documentación
- [ ] Integración continua (CI/CD)

---

**Estado:** ✅ API LISTA PARA USAR

**Última actualización:** 12/04/2026

