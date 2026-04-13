# 📡 DEFINICIÓN DE RUTAS (Routes)

## Descripción

Este documento define todas las rutas (endpoints) de la API REST de BookLog. Todas las rutas están centralizadas en la clase `RoutesConfig.java` para facilitar el mantenimiento y evitar duplicación de código.

---

## 🏗️ Estructura de Rutas

### Base Path
```
/api
```

---

## 👤 Rutas de USUARIOS

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/users` | Crear usuario | `UserController.createUser()` |
| GET | `/api/users` | Obtener todos | `UserController.getAllUsers()` |
| GET | `/api/users/{id}` | Obtener por ID | `UserController.getUserById()` |
| GET | `/api/users/by-username/{nombreUsuario}` | Buscar por nombre | `UserController.getUserByNombreUsuario()` |
| GET | `/api/users/by-email/{correo}` | Buscar por correo | `UserController.getUserByCorreo()` |
| GET | `/api/users/exists/username/{nombreUsuario}` | Verificar usuario | `UserController.existsByNombreUsuario()` |
| GET | `/api/users/exists/email/{correo}` | Verificar correo | `UserController.existsByCorreo()` |
| PUT | `/api/users/{id}` | Actualizar usuario | `UserController.updateUser()` |
| DELETE | `/api/users/{id}` | Eliminar usuario | `UserController.deleteUser()` |

---

## 📖 Rutas de LIBROS

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/books` | Crear libro | `BookController.createBook()` |
| GET | `/api/books/{id}` | Obtener por ID | `BookController.getBookById()` |
| GET | `/api/books/usuario/{usuarioId}` | Libros del usuario | `BookController.getBooksByUsuarioId()` |
| GET | `/api/books/usuario/{usuarioId}/estado/{estado}` | Libros por estado | `BookController.getBooksByEstado()` |
| GET | `/api/books/serie/{serieId}` | Libros de serie | `BookController.getBooksBySerieId()` |
| GET | `/api/books/search/titulo?usuarioId=X&titulo=Y` | Buscar por título | `BookController.searchByTitulo()` |
| GET | `/api/books/search/autor?usuarioId=X&autor=Y` | Buscar por autor | `BookController.searchByAutor()` |
| PUT | `/api/books/{id}` | Actualizar libro | `BookController.updateBook()` |
| PATCH | `/api/books/{id}/progress?progreso=X` | Actualizar progreso | `BookController.updateBookProgress()` |
| DELETE | `/api/books/{id}` | Eliminar libro | `BookController.deleteBook()` |

---

## 📚 Rutas de SERIES

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/series` | Crear serie | `SerieController.createSerie()` |
| GET | `/api/series/{id}` | Obtener por ID | `SerieController.getSerieById()` |
| GET | `/api/series/usuario/{usuarioId}` | Series del usuario | `SerieController.getSeriesByUsuarioId()` |
| PUT | `/api/series/{id}` | Actualizar serie | `SerieController.updateSerie()` |
| DELETE | `/api/series/{id}` | Eliminar serie | `SerieController.deleteSerie()` |

---

## 🎯 Rutas de COLECCIONES

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/colecciones` | Crear colección | `ColeccionController.createColeccion()` |
| GET | `/api/colecciones/{id}` | Obtener por ID | `ColeccionController.getColeccionById()` |
| GET | `/api/colecciones/usuario/{usuarioId}` | Colecciones del usuario | `ColeccionController.getColeccionesByUsuarioId()` |
| PUT | `/api/colecciones/{id}` | Actualizar colección | `ColeccionController.updateColeccion()` |
| DELETE | `/api/colecciones/{id}` | Eliminar colección | `ColeccionController.deleteColeccion()` |

---

## 📝 Rutas de NOTAS

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/notes` | Crear nota | `NoteController.createNote()` |
| GET | `/api/notes/{id}` | Obtener por ID | `NoteController.getNoteById()` |
| GET | `/api/notes/lectura/{lecturaId}` | Notas de libro | `NoteController.getNotesByLecturaId()` |
| PUT | `/api/notes/{id}` | Actualizar nota | `NoteController.updateNote()` |
| DELETE | `/api/notes/{id}` | Eliminar nota | `NoteController.deleteNote()` |

---

## 💬 Rutas de CITAS

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/quotes` | Crear cita | `QuoteController.createQuote()` |
| GET | `/api/quotes/{id}` | Obtener por ID | `QuoteController.getQuoteById()` |
| GET | `/api/quotes/lectura/{lecturaId}` | Citas de libro | `QuoteController.getQuotesByLecturaId()` |
| PUT | `/api/quotes/{id}` | Actualizar cita | `QuoteController.updateQuote()` |
| DELETE | `/api/quotes/{id}` | Eliminar cita | `QuoteController.deleteQuote()` |

---

## 🔗 Rutas de LECTURA-COLECCIÓN

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| POST | `/api/lectura-coleccion` | Agregar libro a colección | `LecturaColeccionController.addBookToColeccion()` |
| GET | `/api/lectura-coleccion/coleccion/{coleccionId}` | Libros en colección | `LecturaColeccionController.getBooksByColeccion()` |
| GET | `/api/lectura-coleccion/lectura/{lecturaId}` | Colecciones de libro | `LecturaColeccionController.getColeccionesByBook()` |
| DELETE | `/api/lectura-coleccion/{lecturaId}/{coleccionId}` | Remover libro | `LecturaColeccionController.removeBookFromColeccion()` |

---

## ✅ Rutas de HEALTH & INFO

| Método | Ruta | Descripción | Controlador |
|--------|------|-------------|------------|
| GET | `/api/health` | Estado de la API | `HealthController.health()` |
| GET | `/api/info` | Información de la API | `HealthController.info()` |

---

## 📋 Referencia en Código

Todas las rutas están definidas como constantes en `RoutesConfig.java`:

```java
// Ejemplo de uso en Controllers
@RestController
@RequestMapping(RoutesConfig.USERS_BASE)
public class UserController {
    
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        // Ruta: POST /api/users
    }
    
    @GetMapping(RoutesConfig.USERS_BY_ID)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // Ruta: GET /api/users/{id}
    }
}
```

---

## 🎯 Patrones de Rutas

### Crear Recurso
```
POST /api/{resource}
```

### Obtener Recurso
```
GET /api/{resource}/{id}
```

### Actualizar Recurso
```
PUT /api/{resource}/{id}
```

### Actualizar Parcialmente
```
PATCH /api/{resource}/{id}/{action}?params
```

### Eliminar Recurso
```
DELETE /api/{resource}/{id}
```

### Búsquedas
```
GET /api/{resource}/search/{criterio}?filtros
GET /api/{resource}/{relacion}/{relacionId}
```

---

## 📊 Estadísticas de Rutas

| Recurso | Rutas CRUD | Rutas Especiales | Total |
|---------|-----------|-----------------|-------|
| Usuarios | 5 | 4 | 9 |
| Libros | 5 | 5 | 10 |
| Series | 5 | 0 | 5 |
| Colecciones | 5 | 0 | 5 |
| Notas | 5 | 0 | 5 |
| Citas | 5 | 0 | 5 |
| L-Colección | 2 | 2 | 4 |
| Health/Info | 0 | 2 | 2 |
| **TOTAL** | **32** | **13** | **60** |

---

## 🔐 Seguridad en Rutas

- ✅ Validación de entrada con `@Valid`
- ✅ CORS habilitado en todos los controllers
- ✅ Métodos HTTP apropiados (REST)
- ✅ IDs en ruta vs parámetros en query según corresponda
- ✅ Nombres de rutas consistentes

---

## 📝 Convenciones

1. **Base paths**: plurales (`/users`, `/books`)
2. **IDs en ruta**: para acceso directo (`/{id}`)
3. **Parámetros en query**: para filtros (`?usuarioId=1&titulo=X`)
4. **Acciones especiales**: PATCH para actualizaciones parciales
5. **Relaciones**: estructura de árbol (`/usuario/{id}/estado/{estado}`)

---

**Última actualización:** 12/04/2026
**Versión:** 1.0.0

