# 🚀 GUÍA DE INSTALACIÓN - BookLog API

## 📋 Requisitos

- **Java 17** o superior
- **MySQL 8.0** o superior
- **Maven 3.6+** o **Gradle 7+**
- **Git** (opcional)
- **Docker & Docker Compose** (opcional, para instalación simplificada)

---

## 🔧 Instalación Opción 1: Local con Maven (Recomendado)

### Paso 1: Instalar Java 17

**Windows:**
- Descargar de: https://adoptium.net/
- Instalar y configurar JAVA_HOME

**Mac:**
```bash
brew install java17
```

**Linux:**
```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk
```

### Paso 2: Instalar MySQL

**Windows:**
- Descargar de: https://dev.mysql.com/downloads/mysql/
- Instalar con MySQL Installer

**Mac:**
```bash
brew install mysql
brew services start mysql
```

**Linux:**
```bash
sudo apt-get update
sudo apt-get install mysql-server
sudo mysql_secure_installation
```

### Paso 3: Crear la base de datos

```bash
mysql -u root -p
```

Ingresar contraseña y luego ejecutar:
```sql
CREATE DATABASE booklog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Paso 4: Clonar el proyecto
```bash
git clone <url-repositorio>
cd api-movil-gestor-libros
```

### Paso 5: Configurar application.properties

Editar: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/booklog_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
```

### Paso 6: Compilar el proyecto
```bash
mvn clean install
```

### Paso 7: Ejecutar la aplicación
```bash
mvn spring-boot:run
```

O compilar primero:
```bash
mvn clean package
java -jar target/api-movil-gestor-libros-1.0-SNAPSHOT.jar
```

### Paso 8: Verificar que está funcionando
```bash
curl http://localhost:8080/api/health
```

---

## 🔧 Instalación Opción 2: Local con Gradle

### Paso 1-3: Igual a Maven

### Paso 4: Clonar el proyecto
```bash
git clone <url-repositorio>
cd api-movil-gestor-libros
```

### Paso 5: Configurar application.properties
Igual a Maven

### Paso 6: Compilar el proyecto
```bash
gradle clean build
```

### Paso 7: Ejecutar la aplicación
```bash
gradle bootRun
```

### Paso 8: Verificar
```bash
curl http://localhost:8080/api/health
```

---

## ⚙️ Configuración

### Cambiar Puerto
Editar: `src/main/resources/application.properties`
```properties
server.port=9000  # Cambiar de 8080 a 9000
```

### Cambiar Base de Datos
```properties
spring.datasource.url=jdbc:mysql://otra-host:3306/otra_db
spring.datasource.username=otro_usuario
spring.datasource.password=otra_contraseña
```

### Usar Perfil de Desarrollo
```bash
# Maven
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# Gradle
gradle bootRun --args='--spring.profiles.active=dev'
```

### Usar Perfil de Producción
```bash
# Maven
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

# Gradle
gradle bootRun --args='--spring.profiles.active=prod'
```

---

## 📊 Verificar Instalación

### 1. Health Check
```bash
curl http://localhost:8080/api/health
```

### 2. Info de la API
```bash
curl http://localhost:8080/api/info
```

### 3. Crear un usuario de prueba
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "test_user",
    "correo": "test@example.com",
    "hashContrasena": "password123"
  }'
```

### 4. Obtener todos los usuarios
```bash
curl http://localhost:8080/api/users
```

Si recibiste datos, ¡la API está funcionando correctamente! ✅

---

## 🧪 Ejecutar Pruebas

### Con Maven
```bash
mvn test
```

### Con Gradle
```bash
gradle test
```

### Con Script de Pruebas
```bash
bash test-api.sh
```

---

## 🐛 Solución de Problemas

### Problema: `Port 8080 already in use`
**Solución:** Cambiar puerto en application.properties
```properties
server.port=8081
```

### Problema: `Connection refused` en MySQL
**Solución:** Verificar que MySQL está corriendo
```bash
# Windows
net start MySQL80

# Mac
brew services start mysql

# Linux
sudo systemctl start mysql
```

### Problema: `Access denied for user 'root'@'localhost'`
**Solución:** Verificar credenciales en application.properties

### Problema: `Exception - Class 'org.example.BookLogApplication' not found`
**Solución:** Compilar primero
```bash
mvn clean package
```

### Problema: `Failed to create database connection`
**Solución:**
1. Verificar que MySQL está corriendo
2. Verificar credenciales
3. Ejecutar: `CREATE DATABASE booklog_db;`

---

## 📱 Recursos Útiles

### URLs Principales
```
API: http://localhost:8080
Swagger (si está agregado): http://localhost:8080/swagger-ui.html
```

### Endpoints Importantes
```
GET    /api/health          - Estado de la API
GET    /api/info            - Información de la API
POST   /api/users           - Crear usuario
GET    /api/users           - Obtener usuarios
POST   /api/books           - Crear libro
GET    /api/books/{id}      - Obtener libro
```

### Documentación
- `README.md` - Documentación completa
- `GUIA_RAPIDA.md` - Guía rápida
- `EJEMPLOS_REQUESTS.md` - Ejemplos de peticiones
- `CHECKLIST.md` - Checklist de implementación

---

## 🚀 Próximos Pasos

1. **Crear usuario:**
   ```bash
   curl -X POST http://localhost:8080/api/users \
     -H "Content-Type: application/json" \
     -d '{"nombreUsuario":"tu_usuario","correo":"tu@correo.com","hashContrasena":"pass123"}'
   ```

2. **Crear libro:**
   ```bash
   curl -X POST http://localhost:8080/api/books \
     -H "Content-Type: application/json" \
     -d '{"usuarioId":1,"titulo":"Mi Libro","autor":"Autor","formato":"PDF","rutaArchivo":"/lib","nombreArchivo":"lib.pdf","progreso":0,"estado":"PENDIENTE"}'
   ```

3. **Consultar datos:**
   ```bash
   curl http://localhost:8080/api/books/usuario/1
   ```

4. **Leer documentación:**
   - Ver `README.md` para API completa
   - Ver `EJEMPLOS_REQUESTS.md` para más ejemplos

---

## 📞 Soporte

Si tienes problemas:

1. **Verifica los logs:**
   ```bash
   # Docker
   docker-compose logs booklog-api
   
   # Local
   # Los logs aparecen en consola
   ```

2. **Consulta la documentación:**
   - README.md
   - GUIA_RAPIDA.md
   - CHECKLIST.md

3. **Verifica que todo está corriendo:**
   ```bash
   curl http://localhost:8080/api/health
   ```

---

## ✅ Checklist de Instalación

- [ ] Java 17+ instalado
- [ ] MySQL 8.0+ instalado
- [ ] Base de datos `booklog_db` creada
- [ ] application.properties configurado
- [ ] Proyecto compilado sin errores
- [ ] API ejecutándose en puerto 8080
- [ ] curl http://localhost:8080/api/health retorna 200
- [ ] Puedo crear un usuario
- [ ] Puedo crear un libro
- [ ] Documentación leída

---

## 🎉 ¡Instalación Completada!

Tu API BookLog está lista para usar. Próximos pasos:

1. Leer `README.md` para documentación
2. Ejecutar `test-api.sh` para pruebas
3. Consultar `EJEMPLOS_REQUESTS.md` para más ejemplos
4. ¡Comenzar a usar la API!

---

**Última actualización:** 12/04/2026
**Versión:** 1.0.0



