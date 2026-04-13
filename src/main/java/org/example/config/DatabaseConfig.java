package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * Configuración centralizada de la Base de Datos
 * Lee las propiedades del archivo application.properties
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DatabaseConfig {

    // ===================== CONFIGURACIÓN PRINCIPAL =====================
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    // ===================== CONFIGURACIÓN JPA/HIBERNATE =====================
    private String platform = "org.hibernate.dialect.MySQL8Dialect";
    private String ddlAuto = "update";
    private Boolean showSql = true;
    private Boolean formatSql = true;

    // ===================== CONSTANTES =====================
    public static final String DATABASE_NAME = "booklog_db";
    public static final String MYSQL_PORT = "3306";
    public static final String MYSQL_HOST = "localhost";
    public static final String MYSQL_VERSION = "8.0+";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    // ===================== PARÁMETROS DE CONEXIÓN =====================
    public static final String CHARSET = "utf8mb4";
    public static final String COLLATION = "utf8mb4_unicode_ci";
    public static final String USE_SSL = "false";
    public static final String SERVER_TIMEZONE = "UTC";

    // ===================== URL DE CONEXIÓN =====================
    /**
     * Construye la URL de conexión a MySQL
     * @return URL formateada para MySQL
     */
    public String buildConnectionUrl() {
        return String.format(
            "jdbc:mysql://%s:%s/%s?useSSL=%s&serverTimezone=%s&characterEncoding=%s",
            MYSQL_HOST,
            MYSQL_PORT,
            DATABASE_NAME,
            USE_SSL,
            SERVER_TIMEZONE,
            CHARSET
        );
    }

    // ===================== INFORMACIÓN DE CONEXIÓN =====================
    /**
     * Obtiene la información de conexión formateada
     * @return String con detalles de conexión
     */
    public String getConnectionInfo() {
        return String.format(
            "Database: %s\n" +
            "Host: %s\n" +
            "Port: %s\n" +
            "Username: %s\n" +
            "Driver: %s\n" +
            "Charset: %s\n" +
            "Timezone: %s",
            DATABASE_NAME,
            MYSQL_HOST,
            MYSQL_PORT,
            username,
            driverClassName,
            CHARSET,
            SERVER_TIMEZONE
        );
    }

    // ===================== VALIDACIÓN =====================
    /**
     * Valida que la configuración de base de datos sea correcta
     * @return true si la configuración es válida
     */
    public boolean isValid() {
        return url != null && !url.isEmpty() &&
               username != null && !username.isEmpty() &&
               password != null && !password.isEmpty() &&
               driverClassName != null && !driverClassName.isEmpty();
    }

    /**
     * Verifica si la conexión tiene SSL habilitado
     * @return true si SSL está habilitado
     */
    public boolean isSslEnabled() {
        return url != null && url.contains("useSSL=true");
    }

    /**
     * Obtiene el nombre de la base de datos desde la URL
     * @return nombre de la BD
     */
    public String getDatabaseName() {
        if (url != null && url.contains("/")) {
            String[] parts = url.split("/");
            if (parts.length > 0) {
                String dbPart = parts[parts.length - 1];
                return dbPart.contains("?") ? 
                    dbPart.substring(0, dbPart.indexOf("?")) : 
                    dbPart;
            }
        }
        return DATABASE_NAME;
    }

    /**
     * Obtiene el host desde la URL
     * @return host de la BD
     */
    public String getHost() {
        if (url != null && url.contains("://")) {
            String[] parts = url.split("://")[1].split(":"); 
            return parts[0];
        }
        return MYSQL_HOST;
    }

    /**
     * Obtiene el puerto desde la URL
     * @return puerto de la BD
     */
    public String getPort() {
        if (url != null && url.contains(":")) {
            String[] parts = url.split(":"); 
            if (parts.length > 1) {
                String portPart = parts[parts.length - 1];
                return portPart.split("/")[0];
            }
        }
        return MYSQL_PORT;
    }
}

