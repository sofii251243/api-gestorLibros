# 🗄️ CONFIGURACIÓN DE BASE DE DATOS

## Descripción

La configuración de conexión a la base de datos está centralizada en la clase `DatabaseConfig.java` ubicada en la carpeta `config/`. Esto facilita el mantenimiento, validación y acceso a las propiedades de conexión desde cualquier parte de la aplicación.

---

## 📁 Ubicación

```
src/main/java/org/example/config/
└── DatabaseConfig.java
```

---

## 📋 Estructura de DatabaseConfig

### Propiedades de Conexión

```java
private String url;           // URL de conexión MySQL
private String username;      // Usuario de BD
private String password;      // Contraseña de BD
private String driverClassName; // Driver JDBC
```

### Propiedades de JPA/Hibernate

```java
private String platform = "org.hibernate.dialect.MySQL8Dialect";
private String ddlAuto = "update";
private Boolean showSql = true;
private Boolean formatSql = true;
```

### Constantes

```java
public static final String DATABASE_NAME = "booklog_db";
public static final String MYSQL_HOST = "localhost";
public static final String MYSQL_PORT = "3306";
public static final String MYSQL_VERSION = "8.0+";
public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
public static final String CHARSET = "utf8mb4";
public static final String USE_SSL = "false";
public static final String SERVER_TIMEZONE = "UTC";
```

---

## ⚙️ Configuración en application.properties

Las propiedades se leen desde `application.properties`:

```ini
# Database Configuration - MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/booklog_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=251243UP
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🔧 Métodos de DatabaseConfig

### buildConnectionUrl()
Construye la URL de conexión a MySQL:

```java
String url = databaseConfig.buildConnectionUrl();
// Resultado: jdbc:mysql://localhost:3306/booklog_db?useSSL=false&serverTimezone=UTC&characterEncoding=utf8mb4
```

### getConnectionInfo()
Obtiene información formateada de la conexión:

```java
String info = databaseConfig.getConnectionInfo();
// Retorna:
// Database: booklog_db
// Host: localhost
// Port: 3306
// Username: root
// Driver: com.mysql.cj.jdbc.Driver
// Charset: utf8mb4
// Timezone: UTC
```

### isValid()
Valida que la configuración sea correcta:

```java
if (databaseConfig.isValid()) {
    // Configuración es válida
}
```

### isSslEnabled()
Verifica si SSL está habilitado:

```java
if (databaseConfig.isSslEnabled()) {
    // SSL habilitado
}
```

### getDatabaseName()
Extrae el nombre de la BD desde la URL:

```java
String dbName = databaseConfig.getDatabaseName();
// Retorna: "booklog_db"
```

### getHost()
Extrae el host desde la URL:

```java
String host = databaseConfig.getHost();
// Retorna: "localhost"
```

### getPort()
Extrae el puerto desde la URL:

```java
String port = databaseConfig.getPort();
// Retorna: "3306"
```

---

## 🚀 Uso en la Aplicación

### En Controllers

```java
@RestController
public class MyController {
    
    @Autowired
    private DatabaseConfig databaseConfig;
    
    @GetMapping("/db-info")
    public ResponseEntity<?> dbInfo() {
        return ResponseEntity.ok(databaseConfig.getConnectionInfo());
    }
}
```

### En Services

```java
@Service
public class MyService {
    
    @Autowired
    private DatabaseConfig databaseConfig;
    
    public void doSomething() {
        if (databaseConfig.isValid()) {
            // Proceder
        }
    }
}
```

### En el HealthController

```java
@GetMapping("/api/config/database")
public ResponseEntity<Map<String, Object>> databaseConfig() {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("valid", databaseConfig.isValid());
    response.put("database", databaseConfig.getDatabaseName());
    response.put("host", databaseConfig.getHost());
    response.put("port", databaseConfig.getPort());
    response.put("username", databaseConfig.getUsername());
    response.put("sslEnabled", databaseConfig.isSslEnabled());
    return ResponseEntity.ok(response);
}
```

---

## 🔗 Endpoints de Configuración

### Health Check
```bash
GET /api/health
```

**Respuesta:**
```json
{
  "status": "UP",
  "message": "BookLog API is running",
  "timestamp": 1712950245000,
  "database": "booklog_db"
}
```

### Información de la API
```bash
GET /api/info
```

**Respuesta:**
```json
{
  "app": "BookLog API",
  "version": "2.0.0",
  "description": "API REST para gestionar libros, colecciones, notas y citas",
  "framework": "Spring Boot 3.2.0",
  "database": "MySQL 8.0+",
  "java": "17+"
}
```

### Configuración de Base de Datos
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

## 📊 Parámetros de Conexión

### Character Set
- **Valor:** `utf8mb4`
- **Razón:** Soporte completo de caracteres Unicode

### Timezone
- **Valor:** `UTC`
- **Razón:** Consistencia en zonas horarias

### SSL
- **Valor:** `false` (desarrollo)
- **Recomendación:** `true` (producción)

### Driver
- **Valor:** `com.mysql.cj.jdbc.Driver`
- **Versión:** MySQL Connector/J 8.0+

---

## 🔐 Configuración para Producción

### Cambiar a SSL
```ini
spring.datasource.url=jdbc:mysql://tu-host:3306/booklog_db?useSSL=true&serverTimezone=UTC
```

### Usar variables de entorno
```ini
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

---

## ✅ Checklist de Configuración

- [ ] MySQL 8.0+ instalado
- [ ] Base de datos `booklog_db` creada
- [ ] Usuario root con contraseña configurada
- [ ] `application.properties` actualizado
- [ ] Validar: `GET /api/config/database`
- [ ] Health OK: `GET /api/health`

---

## 🧪 Pruebas de Conexión

### Con cURL
```bash
# Verificar health
curl http://localhost:8080/api/health

# Verificar configuración
curl http://localhost:8080/api/config/database

# Verificar info
curl http://localhost:8080/api/info
```

### Con Logs
```properties
# En application.properties para ver logs de conexión
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## 🔄 Cambiar BD sin Código

Para cambiar la base de datos sin tocar el código, solo actualiza:

```ini
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/otra_base_de_datos?useSSL=false
spring.datasource.username=otro_usuario
spring.datasource.password=otra_contraseña
```

---

## 📝 Validación Automática

La clase DatabaseConfig valida automáticamente:

```java
// En el constructor o al iniciar Spring
if (!databaseConfig.isValid()) {
    throw new IllegalArgumentException("Configuración de BD inválida");
}
```

---

## 💡 Ventajas de Centralizar Configuración

✅ Una única fuente de verdad
✅ Fácil validación
✅ Métodos auxiliares reutilizables
✅ Documentación centralizada
✅ Fácil cambiar configuración
✅ Testing simplificado
✅ Seguridad (sin hardcoding)

---

**Última actualización:** 12/04/2026
**Versión:** 2.0

