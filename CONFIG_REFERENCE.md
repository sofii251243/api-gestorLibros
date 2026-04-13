# 📂 CARPETA CONFIG - Configuración Centralizada

## Descripción

La carpeta `config/` contiene todas las clases de configuración centralizada de la API. Esto permite una mejor organización, mantenimiento y reutilización del código.

---

## 📁 Estructura

```
src/main/java/org/example/config/
├── RoutesConfig.java        ← Definición de rutas REST
└── DatabaseConfig.java      ← Configuración de Base de Datos
```

---

## 📋 Archivos

### 1. RoutesConfig.java

**Propósito:** Centralizar todas las rutas (endpoints) de la API

**Características:**
- 60+ constantes de rutas
- Categorías de recursos (Users, Books, Series, etc.)
- Método auxiliar `getResourcePath()`
- Fácil refactorización

**Ejemplo:**
```java
public static final String USERS_BASE = "/api/users";
public static final String BOOKS_SEARCH_TITULO = "/api/books/search/titulo";
```

**Uso en Controller:**
```java
@RestController
@RequestMapping(RoutesConfig.USERS_BASE)
public class UserController { }
```

---

### 2. DatabaseConfig.java

**Propósito:** Centralizar la configuración de conexión a la BD

**Características:**
- Lee propiedades de `application.properties`
- Valida configuración automáticamente
- Métodos auxiliares para información de conexión
- Constantes de configuración

**Ejemplo:**
```java
@Autowired
private DatabaseConfig databaseConfig;

if (databaseConfig.isValid()) {
    String dbName = databaseConfig.getDatabaseName();
}
```

---

## 🎯 Beneficios de la Carpeta Config

| Beneficio | Descripción |
|-----------|------------|
| **Centralización** | Una ubicación única para configuraciones |
| **Mantenimiento** | Fácil actualizar configuraciones |
| **Reutilización** | Acceder desde cualquier lugar de la app |
| **Testing** | Inyectable en tests |
| **Documentación** | Configuraciones bien documentadas |
| **Validación** | Validación automática de configuraciones |
| **Seguridad** | Centralización de datos sensibles |

---

## 📊 Contenido de Config

### RoutesConfig

```java
// Base
API_BASE_PATH = "/api"

// Usuarios (9 rutas)
USERS_BASE, USERS_BY_ID, USERS_BY_USERNAME, USERS_BY_EMAIL, ...

// Libros (10 rutas)
BOOKS_BASE, BOOKS_BY_ID, BOOKS_SEARCH_TITULO, BOOKS_UPDATE_PROGRESS, ...

// Series, Colecciones, Notas, Citas, L-Colección (35 rutas)
SERIES_*, COLECCIONES_*, NOTES_*, QUOTES_*, LECTURA_COLECCION_*

// Health & Info (2 rutas)
HEALTH, INFO

// TOTAL: 60+ rutas
```

### DatabaseConfig

```java
// Propiedades
url, username, password, driverClassName
platform, ddlAuto, showSql, formatSql

// Constantes
DATABASE_NAME, MYSQL_HOST, MYSQL_PORT, MYSQL_VERSION
DRIVER_CLASS, CHARSET, COLLATION, USE_SSL, SERVER_TIMEZONE

// Métodos
buildConnectionUrl()
getConnectionInfo()
isValid()
isSslEnabled()
getDatabaseName()
getHost()
getPort()
```

---

## 🔗 Cómo Usar Config

### En Controllers

```java
import org.example.config.RoutesConfig;
import org.example.config.DatabaseConfig;

@RestController
@RequestMapping(RoutesConfig.USERS_BASE)
public class UserController {
    
    @Autowired
    private DatabaseConfig dbConfig;
    
    @GetMapping(RoutesConfig.USERS_BY_ID)
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        // Usar RoutesConfig para ruta y DatabaseConfig para info
    }
}
```

### En Services

```java
import org.example.config.DatabaseConfig;

@Service
public class UserService {
    
    @Autowired
    private DatabaseConfig dbConfig;
    
    public void doSomething() {
        if (dbConfig.isValid()) {
            String dbName = dbConfig.getDatabaseName();
            // Proceder
        }
    }
}
```

### En Tests

```java
@SpringBootTest
public class UserControllerTest {
    
    @Autowired
    private RoutesConfig routesConfig;
    
    @Test
    public void testGetUser() {
        mockMvc.perform(get(routesConfig.USERS_BASE))
            .andExpect(status().isOk());
    }
}
```

---

## 📝 Endpoints de Verificación

### Health Check
```bash
GET /api/health
```

**Respuesta:**
```json
{
  "status": "UP",
  "message": "BookLog API is running",
  "database": "booklog_db"
}
```

### Configuración de BD
```bash
GET /api/config/database
```

**Respuesta:**
```json
{
  "valid": true,
  "database": "booklog_db",
  "host": "localhost",
  "port": "3306",
  "username": "root",
  "sslEnabled": false,
  "charset": "utf8mb4",
  "timezone": "UTC"
}
```

---

## 🔐 Seguridad

✅ Contraseña no expuesta en logs
✅ Configuración centralizada
✅ Validación automática
✅ Uso de properties en lugar de hardcoding
✅ Fácil cambiar en producción

---

## 🎯 Cómo Extender Config

### Agregar Nueva Configuración

1. Crear nueva clase en `config/`
```java
@Configuration
@ConfigurationProperties(prefix = "nueva.propiedad")
@Data
public class NuevaConfig {
    private String property1;
    private String property2;
}
```

2. Agregar en `application.properties`
```ini
nueva.propiedad.property1=valor1
nueva.propiedad.property2=valor2
```

3. Usar en la app
```java
@Autowired
private NuevaConfig config;
```

---

## 📊 Estructura Completa del Proyecto

```
src/main/java/org/example/
├── BookLogApplication.java
├── config/                      ← Configuraciones centralizadas
│   ├── RoutesConfig.java       ← Rutas REST
│   └── DatabaseConfig.java     ← Base de Datos
├── controller/                  ← Controllers REST (8)
├── service/                     ← Servicios (14)
├── repository/                  ← Repositorios (7)
├── model/                       ← Entidades (8)
├── dto/                         ← DTOs (8)
├── mapper/                      ← Mappers (8)
└── exception/                   ← Manejo de errores (5)
```

---

## ✨ Convenciones

✅ Una clase de config por tema
✅ Nombres descriptivos
✅ Constantes en UPPER_CASE
✅ Métodos auxiliares reutilizables
✅ Documentación en Javadoc
✅ Uso de `@Configuration` y `@ConfigurationProperties`
✅ Inyección con `@Autowired`

---

## 🧪 Testing de Config

```java
@SpringBootTest
public class ConfigTest {
    
    @Autowired
    private RoutesConfig routesConfig;
    
    @Autowired
    private DatabaseConfig dbConfig;
    
    @Test
    public void testRoutesNotNull() {
        assertNotNull(routesConfig.USERS_BASE);
        assertNotNull(routesConfig.BOOKS_BASE);
    }
    
    @Test
    public void testDatabaseConfigValid() {
        assertTrue(dbConfig.isValid());
    }
}
```

---

## 📄 Documentación

- **RUTAS.md** - Documentación de RoutesConfig
- **ROUTES_DOCUMENTATION.md** - Guía de uso de RoutesConfig
- **DATABASE_CONFIG.md** - Documentación de DatabaseConfig
- **CONFIG_REFERENCE.md** - Este documento

---

## 🚀 Ventajas Finales

✅ **Mantenimiento:** Cambios en un solo lugar
✅ **Reutilización:** Acceso desde cualquier clase
✅ **Testing:** Fácil de mockear y testear
✅ **Documentación:** Centralizada y clara
✅ **Escalabilidad:** Fácil agregar nuevas configs
✅ **Performance:** Sin duplicación de configuración
✅ **Seguridad:** Centralización de datos sensibles

---

**Última actualización:** 12/04/2026
**Versión:** 2.0

