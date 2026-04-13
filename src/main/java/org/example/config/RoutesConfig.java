package org.example.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada de rutas REST
 * Define todas las rutas base de la API
 */
@Configuration
public class RoutesConfig {

    // ===================== RUTAS BASE =====================
    public static final String API_BASE_PATH = "/api";

    // ===================== USUARIOS =====================
    public static final String USERS_BASE = API_BASE_PATH + "/users";
    public static final String USERS_BY_ID_REL = "/{id}";
    public static final String USERS_BY_USERNAME_REL = "/by-username/{nombreUsuario}";
    public static final String USERS_BY_EMAIL_REL = "/by-email/{correo}";
    public static final String USERS_EXISTS_USERNAME_REL = "/exists/username/{nombreUsuario}";
    public static final String USERS_EXISTS_EMAIL_REL = "/exists/email/{correo}";
    public static final String USERS_BY_ID = USERS_BASE + "/{id}";
    public static final String USERS_BY_USERNAME = USERS_BASE + "/by-username/{nombreUsuario}";
    public static final String USERS_BY_EMAIL = USERS_BASE + "/by-email/{correo}";
    public static final String USERS_EXISTS_USERNAME = USERS_BASE + "/exists/username/{nombreUsuario}";
    public static final String USERS_EXISTS_EMAIL = USERS_BASE + "/exists/email/{correo}";

    // ===================== LIBROS =====================
    public static final String BOOKS_BASE = API_BASE_PATH + "/books";
    public static final String BOOKS_BY_ID_REL = "/{id}";
    public static final String BOOKS_BY_USUARIO_REL = "/usuario/{usuarioId}";
    public static final String BOOKS_BY_USUARIO_ESTADO_REL = "/usuario/{usuarioId}/estado/{estado}";
    public static final String BOOKS_BY_SERIE_REL = "/serie/{serieId}";
    public static final String BOOKS_SEARCH_TITULO_REL = "/search/titulo";
    public static final String BOOKS_SEARCH_AUTOR_REL = "/search/autor";
    public static final String BOOKS_UPDATE_PROGRESS_REL = "/{id}/progress";
    public static final String BOOKS_BY_ID = BOOKS_BASE + "/{id}";
    public static final String BOOKS_BY_USUARIO = BOOKS_BASE + "/usuario/{usuarioId}";
    public static final String BOOKS_BY_USUARIO_ESTADO = BOOKS_BASE + "/usuario/{usuarioId}/estado/{estado}";
    public static final String BOOKS_BY_SERIE = BOOKS_BASE + "/serie/{serieId}";
    public static final String BOOKS_SEARCH_TITULO = BOOKS_BASE + "/search/titulo";
    public static final String BOOKS_SEARCH_AUTOR = BOOKS_BASE + "/search/autor";
    public static final String BOOKS_UPDATE_PROGRESS = BOOKS_BASE + "/{id}/progress";

    // ===================== SERIES =====================
    public static final String SERIES_BASE = API_BASE_PATH + "/series";
    public static final String SERIES_BY_ID_REL = "/{id}";
    public static final String SERIES_BY_USUARIO_REL = "/usuario/{usuarioId}";
    public static final String SERIES_BY_ID = SERIES_BASE + "/{id}";
    public static final String SERIES_BY_USUARIO = SERIES_BASE + "/usuario/{usuarioId}";

    // ===================== COLECCIONES =====================
    public static final String COLECCIONES_BASE = API_BASE_PATH + "/colecciones";
    public static final String COLECCIONES_BY_ID_REL = "/{id}";
    public static final String COLECCIONES_BY_USUARIO_REL = "/usuario/{usuarioId}";
    public static final String COLECCIONES_BY_ID = COLECCIONES_BASE + "/{id}";
    public static final String COLECCIONES_BY_USUARIO = COLECCIONES_BASE + "/usuario/{usuarioId}";

    // ===================== NOTAS =====================
    public static final String NOTES_BASE = API_BASE_PATH + "/notes";
    public static final String NOTES_BY_ID_REL = "/{id}";
    public static final String NOTES_BY_LECTURA_REL = "/lectura/{lecturaId}";
    public static final String NOTES_BY_ID = NOTES_BASE + "/{id}";
    public static final String NOTES_BY_LECTURA = NOTES_BASE + "/lectura/{lecturaId}";

    // ===================== CITAS =====================
    public static final String QUOTES_BASE = API_BASE_PATH + "/quotes";
    public static final String QUOTES_BY_ID_REL = "/{id}";
    public static final String QUOTES_BY_LECTURA_REL = "/lectura/{lecturaId}";
    public static final String QUOTES_BY_ID = QUOTES_BASE + "/{id}";
    public static final String QUOTES_BY_LECTURA = QUOTES_BASE + "/lectura/{lecturaId}";

    // ===================== LECTURA-COLECCIÓN =====================
    public static final String LECTURA_COLECCION_BASE = API_BASE_PATH + "/lectura-coleccion";
    public static final String LECTURA_COLECCION_BY_COLECCION_REL = "/coleccion/{coleccionId}";
    public static final String LECTURA_COLECCION_BY_LECTURA_REL = "/lectura/{lecturaId}";
    public static final String LECTURA_COLECCION_DELETE_REL = "/{lecturaId}/{coleccionId}";
    public static final String LECTURA_COLECCION_BY_COLECCION = LECTURA_COLECCION_BASE + "/coleccion/{coleccionId}";
    public static final String LECTURA_COLECCION_BY_LECTURA = LECTURA_COLECCION_BASE + "/lectura/{lecturaId}";
    public static final String LECTURA_COLECCION_DELETE = LECTURA_COLECCION_BASE + "/{lecturaId}/{coleccionId}";

    // ===================== HEALTH & INFO =====================
    public static final String HEALTH_REL = "/health";
    public static final String INFO_REL = "/info";
    public static final String DATABASE_CONFIG_REL = "/config/database";
    public static final String HEALTH = API_BASE_PATH + "/health";
    public static final String INFO = API_BASE_PATH + "/info";

    /**
     * Método para obtener la ruta completa de un endpoint
     * @param basePath ruta base
     * @param resourceId ID del recurso
     * @return ruta completa
     */
    public static String getResourcePath(String basePath, String resourceId) {
        return basePath + "/" + resourceId;
    }
}


