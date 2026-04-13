package org.example.controller;

import org.example.config.DatabaseConfig;
import org.example.config.RoutesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(RoutesConfig.API_BASE_PATH)
public class HealthController {

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping(RoutesConfig.HEALTH_REL)
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("message", "BookLog API is running");
        response.put("timestamp", System.currentTimeMillis());
        response.put("database", databaseConfig.getDatabaseName());
        return ResponseEntity.ok(response);
    }

    @GetMapping(RoutesConfig.INFO_REL)
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("app", "BookLog API");
        response.put("version", "2.0.0");
        response.put("description", "API REST para gestionar libros, colecciones, notas y citas");
        response.put("framework", "Spring Boot 3.2.0");
        response.put("database", "MySQL 8.0+");
        response.put("java", "17+");
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para verificar la configuración de la base de datos
     * @return información de conexión
     */
    @GetMapping(RoutesConfig.DATABASE_CONFIG_REL)
    public ResponseEntity<Map<String, Object>> databaseConfig() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("valid", databaseConfig.isValid());
        response.put("database", databaseConfig.getDatabaseName());
        response.put("host", databaseConfig.getHost());
        response.put("port", databaseConfig.getPort());
        response.put("username", databaseConfig.getUsername());
        response.put("sslEnabled", databaseConfig.isSslEnabled());
        response.put("charset", DatabaseConfig.CHARSET);
        response.put("timezone", DatabaseConfig.SERVER_TIMEZONE);
        return ResponseEntity.ok(response);
    }
}



