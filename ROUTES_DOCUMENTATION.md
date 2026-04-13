# 📂 CARPETA ROUTES - Configuración de Endpoints

## Descripción

La carpeta `routes/config/` contiene la centralización de todas las definiciones de rutas (endpoints) de la API REST. Esto facilita el mantenimiento, evita duplicación de rutas en diferentes controllers y proporciona una referencia única para toda la API.

---

## 📁 Estructura

```
src/main/java/org/example/
└── config/
    └── RoutesConfig.java          ← Definición centralizada de todas las rutas
```

---

## 🎯 Archivos

### `RoutesConfig.java`

Clase de configuración que define todas las rutas como constantes públicas estáticas.

**Ventajas:**
- ✅ Única fuente de verdad para todas las rutas
- ✅ Fácil de mantener y actualizar
- ✅ Evita duplicación
- ✅ Refactorización automática en IDEs
- ✅ Documentación centralizada

---

## 📋 Estructura de Rutas

### Base Path
```java
public static final String API_BASE_PATH = "/api";
```

### Categorías de Rutas

#### Usuarios
```java
public static final String USERS_BASE = API_BASE_PATH + "/users";
public static final String USERS_BY_ID = USERS_BASE + "/{id}";
public static final String USERS_BY_USERNAME = USERS_BASE + "/by-username/{nombreUsuario}";
public static final String USERS_BY_EMAIL = USERS_BASE + "/by-email/{correo}";
public static final String USERS_EXISTS_USERNAME = USERS_BASE + "/exists/username/{nombreUsuario}";
public static final String USERS_EXISTS_EMAIL = USERS_BASE + "/exists/email/{correo}";
```

#### Libros
```java
public static final String BOOKS_BASE = API_BASE_PATH + "/books";
public static final String BOOKS_BY_ID = BOOKS_BASE + "/{id}";
public static final String BOOKS_BY_USUARIO = BOOKS_BASE + "/usuario/{usuarioId}";
public static final String BOOKS_BY_USUARIO_ESTADO = BOOKS_BASE + "/usuario/{usuarioId}/estado/{estado}";
public static final String BOOKS_BY_SERIE = BOOKS_BASE + "/serie/{serieId}";
public static final String BOOKS_SEARCH_TITULO = BOOKS_BASE + "/search/titulo";
public static final String BOOKS_SEARCH_AUTOR = BOOKS_BASE + "/search/autor";
public static final String BOOKS_UPDATE_PROGRESS = BOOKS_BASE + "/{id}/progress";
```

#### Series, Colecciones, Notas, Citas
```java
public static final String SERIES_BASE = API_BASE_PATH + "/series";
public static final String COLECCIONES_BASE = API_BASE_PATH + "/colecciones";
public static final String NOTES_BASE = API_BASE_PATH + "/notes";
public static final String QUOTES_BASE = API_BASE_PATH + "/quotes";
```

#### Relaciones Many-to-Many
```java
public static final String LECTURA_COLECCION_BASE = API_BASE_PATH + "/lectura-coleccion";
public static final String LECTURA_COLECCION_BY_COLECCION = LECTURA_COLECCION_BASE + "/coleccion/{coleccionId}";
public static final String LECTURA_COLECCION_BY_LECTURA = LECTURA_COLECCION_BASE + "/lectura/{lecturaId}";
```

#### Health & Info
```java
public static final String HEALTH = API_BASE_PATH + "/health";
public static final String INFO = API_BASE_PATH + "/info";
```

---

## 🔗 Uso en Controllers

### Ejemplo: UserController

```java
import org.example.config.RoutesConfig;

@RestController
@RequestMapping(RoutesConfig.USERS_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        // POST /api/users
    }

    @GetMapping(RoutesConfig.USERS_BY_ID)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // GET /api/users/{id}
    }

    @GetMapping(RoutesConfig.USERS_BY_USERNAME)
    public ResponseEntity<UserDTO> getUserByNombreUsuario(@PathVariable String nombreUsuario) {
        // GET /api/users/by-username/{nombreUsuario}
    }

    @PutMapping(RoutesConfig.USERS_BY_ID)
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        // PUT /api/users/{id}
    }

    @DeleteMapping(RoutesConfig.USERS_BY_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // DELETE /api/users/{id}
    }
}
```

### Ejemplo: BookController

```java
import org.example.config.RoutesConfig;

@RestController
@RequestMapping(RoutesConfig.BOOKS_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        // POST /api/books
    }

    @GetMapping(RoutesConfig.BOOKS_BY_ID)
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        // GET /api/books/{id}
    }

    @GetMapping(RoutesConfig.BOOKS_BY_USUARIO)
    public ResponseEntity<List<BookDTO>> getBooksByUsuarioId(@PathVariable Long usuarioId) {
        // GET /api/books/usuario/{usuarioId}
    }

    @GetMapping(RoutesConfig.BOOKS_SEARCH_TITULO)
    public ResponseEntity<List<BookDTO>> searchByTitulo(
            @RequestParam Long usuarioId,
            @RequestParam String titulo) {
        // GET /api/books/search/titulo?usuarioId=1&titulo=Harry
    }

    @PatchMapping(RoutesConfig.BOOKS_UPDATE_PROGRESS)
    public ResponseEntity<BookDTO> updateBookProgress(
            @PathVariable Long id,
            @RequestParam Float progreso) {
        // PATCH /api/books/{id}/progress?progreso=50
    }
}
```

---

## 📊 Resumen de Rutas

| Sección | Base | Total Rutas |
|---------|------|------------|
| Usuarios | `/api/users` | 9 |
| Libros | `/api/books` | 10 |
| Series | `/api/series` | 5 |
| Colecciones | `/api/colecciones` | 5 |
| Notas | `/api/notes` | 5 |
| Citas | `/api/quotes` | 5 |
| L-Colección | `/api/lectura-coleccion` | 4 |
| Health/Info | `/api` | 2 |
| **TOTAL** | | **60** |

---

## 🔐 Beneficios de esta Estructura

1. **Centralización**: Una única fuente de verdad para todas las rutas
2. **Mantenimiento**: Cambiar una ruta se actualiza en todos los places
3. **Consistencia**: Evita errores de tipeo en rutas
4. **Refactorización**: IDEs pueden refactorizar automáticamente
5. **Documentación**: Las rutas están documentadas en un único lugar
6. **Testing**: Fácil hacer referencias en tests
7. **Performance**: No hay duplicación de strings

---

## 💡 Método Auxiliar

La clase RoutesConfig también proporciona un método auxiliar:

```java
public static String getResourcePath(String basePath, String resourceId) {
    return basePath + "/" + resourceId;
}
```

**Uso:**
```java
String userPath = RoutesConfig.getResourcePath(RoutesConfig.USERS_BASE, "123");
// Resultado: "/api/users/123"
```

---

## 🔄 Cómo Agregar una Nueva Ruta

1. Abre `RoutesConfig.java`
2. Agrega una constante en la sección correspondiente:
   ```java
   public static final String NUEVA_RUTA = API_BASE_PATH + "/nueva-ruta";
   ```
3. Usa la constante en el controller:
   ```java
   @GetMapping(RoutesConfig.NUEVA_RUTA)
   public ResponseEntity<?> metodo() { ... }
   ```

---

## 📝 Convenciones

- **Base paths**: plurales (`/users`, `/books`)
- **Nombres**: snake_case en URLs, camelCase en constantes Java
- **IDs en ruta**: para acceso directo (`/{id}`)
- **Parámetros en query**: para filtros (`?usuarioId=1&titulo=X`)
- **Acciones especiales**: PATCH para actualizaciones parciales
- **Relaciones**: estructura jerárquica (`/usuario/{id}/estado/{estado}`)

---

## 🧪 Uso en Tests

```java
@Test
public void testCreateUser() {
    // Usa constante de RoutesConfig
    mockMvc.perform(post(RoutesConfig.USERS_BASE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isCreated());
}
```

---

## 📄 Referencia Completa

Ver [RUTAS.md](../RUTAS.md) para la documentación completa de todas las rutas con detalles de métodos HTTP, parámetros y controladores.

---

**Última actualización:** 12/04/2026
**Versión:** 1.0.0

