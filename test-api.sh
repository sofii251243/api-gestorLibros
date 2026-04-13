#!/bin/bash

# Script de Pruebas - BookLog API
# ================================

BASE_URL="http://localhost:8080/api"

# Colores para la terminal
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}================================${NC}"
echo -e "${BLUE}BookLog API - Test Script${NC}"
echo -e "${BLUE}================================${NC}\n"

# 1. Health Check
echo -e "${YELLOW}1. Verificar estado de la API...${NC}"
curl -s -X GET "$BASE_URL/health" | python3 -m json.tool
echo -e "\n"

# 2. Info de la API
echo -e "${YELLOW}2. Información de la API...${NC}"
curl -s -X GET "$BASE_URL/info" | python3 -m json.tool
echo -e "\n"

# 3. Crear un usuario
echo -e "${YELLOW}3. Crear usuario...${NC}"
USER_RESPONSE=$(curl -s -X POST "$BASE_URL/users" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez",
    "correo": "juan@example.com",
    "hashContrasena": "password123"
  }')
echo "$USER_RESPONSE" | python3 -m json.tool
USER_ID=$(echo "$USER_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])" 2>/dev/null || echo "1")
echo -e "\n"

# 4. Obtener todos los usuarios
echo -e "${YELLOW}4. Obtener todos los usuarios...${NC}"
curl -s -X GET "$BASE_URL/users" | python3 -m json.tool
echo -e "\n"

# 5. Obtener usuario por ID
echo -e "${YELLOW}5. Obtener usuario por ID...${NC}"
curl -s -X GET "$BASE_URL/users/$USER_ID" | python3 -m json.tool
echo -e "\n"

# 6. Crear una serie
echo -e "${YELLOW}6. Crear serie...${NC}"
SERIE_RESPONSE=$(curl -s -X POST "$BASE_URL/series" \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"nombre\": \"Harry Potter\"
  }")
echo "$SERIE_RESPONSE" | python3 -m json.tool
SERIE_ID=$(echo "$SERIE_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])" 2>/dev/null || echo "1")
echo -e "\n"

# 7. Crear un libro
echo -e "${YELLOW}7. Crear libro...${NC}"
BOOK_RESPONSE=$(curl -s -X POST "$BASE_URL/books" \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"rutaArchivo\": \"/libros/harry_potter_1.pdf\",
    \"nombreArchivo\": \"harry_potter_1.pdf\",
    \"titulo\": \"Harry Potter y la Piedra Filosofal\",
    \"formato\": \"PDF\",
    \"autor\": \"J.K. Rowling\",
    \"serieId\": $SERIE_ID,
    \"progreso\": 25,
    \"estado\": \"LEYENDO\"
  }")
echo "$BOOK_RESPONSE" | python3 -m json.tool
BOOK_ID=$(echo "$BOOK_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])" 2>/dev/null || echo "1")
echo -e "\n"

# 8. Obtener libros del usuario
echo -e "${YELLOW}8. Obtener libros del usuario...${NC}"
curl -s -X GET "$BASE_URL/books/usuario/$USER_ID" | python3 -m json.tool
echo -e "\n"

# 9. Buscar libros por título
echo -e "${YELLOW}9. Buscar libros por título...${NC}"
curl -s -X GET "$BASE_URL/books/search/titulo?usuarioId=$USER_ID&titulo=Harry" | python3 -m json.tool
echo -e "\n"

# 10. Actualizar progreso del libro
echo -e "${YELLOW}10. Actualizar progreso de lectura...${NC}"
curl -s -X PATCH "$BASE_URL/books/$BOOK_ID/progress?progreso=50" | python3 -m json.tool
echo -e "\n"

# 11. Crear una colección
echo -e "${YELLOW}11. Crear colección...${NC}"
COLECCION_RESPONSE=$(curl -s -X POST "$BASE_URL/colecciones" \
  -H "Content-Type: application/json" \
  -d "{
    \"usuarioId\": $USER_ID,
    \"nombre\": \"Mi Colección Favorita\"
  }")
echo "$COLECCION_RESPONSE" | python3 -m json.tool
COLECCION_ID=$(echo "$COLECCION_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])" 2>/dev/null || echo "1")
echo -e "\n"

# 12. Agregar libro a colección
echo -e "${YELLOW}12. Agregar libro a colección...${NC}"
curl -s -X POST "$BASE_URL/lectura-coleccion" \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"coleccionId\": $COLECCION_ID
  }" | python3 -m json.tool
echo -e "\n"

# 13. Obtener libros de una colección
echo -e "${YELLOW}13. Obtener libros de una colección...${NC}"
curl -s -X GET "$BASE_URL/lectura-coleccion/coleccion/$COLECCION_ID" | python3 -m json.tool
echo -e "\n"

# 14. Crear una nota
echo -e "${YELLOW}14. Crear nota...${NC}"
NOTE_RESPONSE=$(curl -s -X POST "$BASE_URL/notes" \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"contenido\": \"Excelente descripción del mundo mágico\",
    \"referenciaPagina\": \"pag. 45\"
  }")
echo "$NOTE_RESPONSE" | python3 -m json.tool
NOTE_ID=$(echo "$NOTE_RESPONSE" | python3 -c "import sys, json; print(json.load(sys.stdin)['id'])" 2>/dev/null || echo "1")
echo -e "\n"

# 15. Obtener notas de un libro
echo -e "${YELLOW}15. Obtener notas de un libro...${NC}"
curl -s -X GET "$BASE_URL/notes/lectura/$BOOK_ID" | python3 -m json.tool
echo -e "\n"

# 16. Crear una cita
echo -e "${YELLOW}16. Crear cita...${NC}"
QUOTE_RESPONSE=$(curl -s -X POST "$BASE_URL/quotes" \
  -H "Content-Type: application/json" \
  -d "{
    \"lecturaId\": $BOOK_ID,
    \"textoCitado\": \"La magia existe, solo hay que saber verla\",
    \"referenciaPagina\": \"pag. 72\",
    \"comentario\": \"Frase inspiradora\"
  }")
echo "$QUOTE_RESPONSE" | python3 -m json.tool
echo -e "\n"

# 17. Obtener citas de un libro
echo -e "${YELLOW}17. Obtener citas de un libro...${NC}"
curl -s -X GET "$BASE_URL/quotes/lectura/$BOOK_ID" | python3 -m json.tool
echo -e "\n"

# 18. Actualizar usuario
echo -e "${YELLOW}18. Actualizar usuario...${NC}"
curl -s -X PUT "$BASE_URL/users/$USER_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juan_perez_updated",
    "correo": "juanupdated@example.com",
    "hashContrasena": "newpassword456"
  }' | python3 -m json.tool
echo -e "\n"

echo -e "${GREEN}✅ Pruebas completadas${NC}"
echo -e "\n"
echo -e "${YELLOW}IDs generados para referencia:${NC}"
echo -e "USER_ID: $USER_ID"
echo -e "SERIE_ID: $SERIE_ID"
echo -e "BOOK_ID: $BOOK_ID"
echo -e "COLECCION_ID: $COLECCION_ID"
echo -e "NOTE_ID: $NOTE_ID"

