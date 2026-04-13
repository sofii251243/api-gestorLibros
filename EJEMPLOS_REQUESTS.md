# 📡 EJEMPLOS DE REQUESTS - BookLog API

## 🔧 Configuración Base

```
Base URL: http://localhost:8080/api
Content-Type: application/json
```

---

## 👤 USUARIOS (Users)

### 1. Crear Usuario
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "correo": "juan@example.com",
    "hashContrasena": "password123"
  }'
```

**Respuesta Esperada (201):**
```json
{
  "id": 1,
  "nombreUsuario": "juan_perez",
  "correo": "juan@example.com",
  "hashContrasena": "password123",
  "fechaCreacion": 1712950245000
}
```

### 2. Obtener Todos los Usuarios
```bash
curl -X GET http://localhost:8080/api/users
```

**Respuesta (200):**
```json
[
  {
    "id": 1,
    "nombreUsuario": "juan_perez",
    "correo": "juan@example.com",
    "hashContrasena": "password123",
    "fechaCreacion": 1712950245000
  }
]
```

### 3. Obtener Usuario por ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

### 4. Obtener Usuario por Nombre
```bash
curl -X GET http://localhost:8080/api/users/by-username/juan_perez
```

### 5. Obtener Usuario por Correo
```bash
curl -X GET http://localhost:8080/api/users/by-email/juan@example.com
```

### 6. Actualizar Usuario
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez_v2",
    "correo": "juanv2@example.com",
    "hashContrasena": "newpassword456"
  }'
```

### 7. Verificar Existencia de Usuario
```bash
curl -X GET http://localhost:8080/api/users/exists/username/juan_perez
```

**Respuesta:** `true` o `false`

### 8. Eliminar Usuario
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

---

## 📖 LIBROS (Books)

### 1. Crear Libro
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "rutaArchivo": "/libros/harry_potter_1.pdf",
    "nombreArchivo": "harry_potter_1.pdf",
    "titulo": "Harry Potter y la Piedra Filosofal",
    "formato": "PDF",
    "autor": "J.K. Rowling",
    "serieId": 1,
    "progreso": 25.5,
    "estado": "LEYENDO",
    "coverPath": "/covers/hp1.jpg"
  }'
```

**Validaciones:**
- `progreso`: 0-100
- `estado`: PENDIENTE, LEYENDO, COMPLETADO, PAUSADO
- `usuarioId`: Debe existir

### 2. Obtener Todos los Libros del Usuario
```bash
curl -X GET http://localhost:8080/api/books/usuario/1
```

### 3. Obtener Libros por Estado
```bash
curl -X GET http://localhost:8080/api/books/usuario/1/estado/LEYENDO
```

### 4. Obtener Libros de una Serie
```bash
curl -X GET http://localhost:8080/api/books/serie/1
```

### 5. Buscar Libros por Título
```bash
curl -X GET "http://localhost:8080/api/books/search/titulo?usuarioId=1&titulo=Harry"
```

### 6. Buscar Libros por Autor
```bash
curl -X GET "http://localhost:8080/api/books/search/autor?usuarioId=1&autor=Rowling"
```

### 7. Actualizar Progreso de Lectura
```bash
curl -X PATCH "http://localhost:8080/api/books/1/progress?progreso=50"
```

**Validación:** progreso entre 0-100

### 8. Actualizar Libro Completo
```bash
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "rutaArchivo": "/libros/hp1_updated.pdf",
    "nombreArchivo": "hp1_updated.pdf",
    "titulo": "Harry Potter 1 - Actualizado",
    "formato": "PDF",
    "autor": "J.K. Rowling",
    "serieId": 1,
    "progreso": 75,
    "estado": "COMPLETADO",
    "coverPath": "/covers/hp1_v2.jpg"
  }'
```

### 9. Obtener Libro por ID
```bash
curl -X GET http://localhost:8080/api/books/1
```

### 10. Eliminar Libro
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

---

## 📚 SERIES (Series)

### 1. Crear Serie
```bash
curl -X POST http://localhost:8080/api/series \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "nombre": "Harry Potter"
  }'
```

### 2. Obtener Series del Usuario
```bash
curl -X GET http://localhost:8080/api/series/usuario/1
```

### 3. Actualizar Serie
```bash
curl -X PUT http://localhost:8080/api/series/1 \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "nombre": "Harry Potter - Saga Completa"
  }'
```

### 4. Obtener Serie por ID
```bash
curl -X GET http://localhost:8080/api/series/1
```

### 5. Eliminar Serie
```bash
curl -X DELETE http://localhost:8080/api/series/1
```

---

## 🎯 COLECCIONES (Colecciones)

### 1. Crear Colección
```bash
curl -X POST http://localhost:8080/api/colecciones \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "nombre": "Mi Colección Favorita"
  }'
```

### 2. Obtener Colecciones del Usuario
```bash
curl -X GET http://localhost:8080/api/colecciones/usuario/1
```

### 3. Actualizar Colección
```bash
curl -X PUT http://localhost:8080/api/colecciones/1 \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "nombre": "Mis Libros Favoritos 2024"
  }'
```

### 4. Eliminar Colección
```bash
curl -X DELETE http://localhost:8080/api/colecciones/1
```

---

## 📝 NOTAS (Notes)

### 1. Crear Nota
```bash
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d '{
    "lecturaId": 1,
    "contenido": "Excelente descripción del mundo mágico y del personaje de Harry",
    "referenciaPagina": "pag. 45"
  }'
```

### 2. Obtener Notas de un Libro
```bash
curl -X GET http://localhost:8080/api/notes/lectura/1
```

### 3. Actualizar Nota
```bash
curl -X PUT http://localhost:8080/api/notes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "lecturaId": 1,
    "contenido": "Nota actualizada: Muy buen libro",
    "referenciaPagina": "pag. 50"
  }'
```

### 4. Eliminar Nota
```bash
curl -X DELETE http://localhost:8080/api/notes/1
```

---

## 💬 CITAS (Quotes)

### 1. Crear Cita
```bash
curl -X POST http://localhost:8080/api/quotes \
  -H "Content-Type: application/json" \
  -d '{
    "lecturaId": 1,
    "textoCitado": "La magia existe, solo hay que saber verla",
    "referenciaPagina": "pag. 72",
    "comentario": "Frase muy inspiradora del libro"
  }'
```

### 2. Obtener Citas de un Libro
```bash
curl -X GET http://localhost:8080/api/quotes/lectura/1
```

### 3. Actualizar Cita
```bash
curl -X PUT http://localhost:8080/api/quotes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "lecturaId": 1,
    "textoCitado": "El verdadero poder está en creer en ti mismo",
    "referenciaPagina": "pag. 75",
    "comentario": "Mensaje de esperanza"
  }'
```

### 4. Eliminar Cita
```bash
curl -X DELETE http://localhost:8080/api/quotes/1
```

---

## 🔗 LECTURA-COLECCIÓN (Book-Collection Relationships)

### 1. Agregar Libro a Colección
```bash
curl -X POST http://localhost:8080/api/lectura-coleccion \
  -H "Content-Type: application/json" \
  -d '{
    "lecturaId": 1,
    "coleccionId": 1
  }'
```

### 2. Obtener Libros de una Colección
```bash
curl -X GET http://localhost:8080/api/lectura-coleccion/coleccion/1
```

### 3. Obtener Colecciones de un Libro
```bash
curl -X GET http://localhost:8080/api/lectura-coleccion/lectura/1
```

### 4. Remover Libro de Colección
```bash
curl -X DELETE http://localhost:8080/api/lectura-coleccion/1/1
```

Parámetros: `{lecturaId}/{coleccionId}`

---

## ✅ HEALTH & INFO

### 1. Verificar Estado API
```bash
curl -X GET http://localhost:8080/api/health
```

**Respuesta:**
```json
{
  "status": "UP",
  "message": "BookLog API is running"
}
```

### 2. Información de la API
```bash
curl -X GET http://localhost:8080/api/info
```

**Respuesta:**
```json
{
  "app": "BookLog API",
  "version": "1.0.0",
  "description": "API REST para gestionar libros, colecciones, notas y citas"
}
```

---

## 🚨 ERRORES COMUNES

### 400 - Bad Request
```json
{
  "timestamp": "2026-04-12T10:30:45",
  "status": 400,
  "error": "Validation Failed",
  "message": "Field validation error: ...",
  "path": "/api/books"
}
```

**Causas:**
- Progreso fuera de rango (no 0-100)
- Estado inválido
- Email inválido
- Campo requerido vacío

### 404 - Not Found
```json
{
  "timestamp": "2026-04-12T10:30:45",
  "status": 404,
  "error": "Resource Not Found",
  "message": "Usuario no encontrado con ID: 999",
  "path": "/api/users/999"
}
```

### 409 - Conflict
```json
{
  "timestamp": "2026-04-12T10:30:45",
  "status": 409,
  "error": "Duplicate Resource",
  "message": "El nombre de usuario ya existe: juan_perez",
  "path": "/api/users"
}
```

---

## 🧪 FLUJO COMPLETO DE PRUEBA

### 1. Crear usuario
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "demo_user",
    "correo": "demo@example.com",
    "hashContrasena": "demo123"
  }' | python3 -m json.tool
```

Guardar el `id` retornado: `$USER_ID`

### 2. Crear serie
```bash
curl -X POST http://localhost:8080/api/series \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"nombre\": \"Mi Serie Favorita\"
  }" | python3 -m json.tool
```

Guardar el `id`: `$SERIE_ID`

### 3. Crear libro
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"rutaArchivo\": \"/libros/demo.pdf\",
    \"nombreArchivo\": \"demo.pdf\",
    \"titulo\": \"Mi Libro Demo\",
    \"formato\": \"PDF\",
    \"autor\": \"Autor Demo\",
    \"serieId\": $SERIE_ID,
    \"progreso\": 0,
    \"estado\": \"PENDIENTE\"
  }" | python3 -m json.tool
```

Guardar el `id`: `$BOOK_ID`

### 4. Actualizar progreso
```bash
curl -X PATCH "http://localhost:8080/api/books/$BOOK_ID/progress?progreso=50"
```

### 5. Crear colección
```bash
curl -X POST http://localhost:8080/api/colecciones \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"nombre\": \"Mi Colección\"
  }" | python3 -m json.tool
```

Guardar el `id`: `$COLECCION_ID`

### 6. Agregar libro a colección
```bash
curl -X POST http://localhost:8080/api/lectura-coleccion \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"coleccionId\": $COLECCION_ID
  }"
```

### 7. Crear nota
```bash
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"contenido\": \"Esto es una nota de prueba\",
    \"referenciaPagina\": \"pag. 10\"
  }" | python3 -m json.tool
```

### 8. Crear cita
```bash
curl -X POST http://localhost:8080/api/quotes \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"textoCitado\": \"Una frase memorable\",
    \"referenciaPagina\": \"pag. 15\",
    \"comentario\": \"Muy inspiradora\"
  }" | python3 -m json.tool
```

### 9. Verificar datos
```bash
curl http://localhost:8080/api/books/usuario/$USER_ID | python3 -m json.tool
curl http://localhost:8080/api/notes/lectura/$BOOK_ID | python3 -m json.tool
curl http://localhost:8080/api/quotes/lectura/$BOOK_ID | python3 -m json.tool
```

---

## 💡 TIPS

1. **Guardar IDs en variables (Bash):**
```bash
USER_ID=$(curl -s -X POST ... | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])")
```

2. **Formatear salida JSON:**
```bash
curl ... | python3 -m json.tool
```

3. **Ver headers de respuesta:**
```bash
curl -i http://localhost:8080/api/health
```

4. **Ver solo código de estado:**
```bash
curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/health
```

5. **Guardar respuesta en archivo:**
```bash
curl http://localhost:8080/api/users > users.json
```

---

**Última actualización:** 12/04/2026

