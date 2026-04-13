package org.example.controller;

import org.example.config.RoutesConfig;
import org.example.dto.UserDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RoutesConfig.USERS_BASE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping(RoutesConfig.USERS_BY_ID_REL)
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping(RoutesConfig.USERS_BY_ID_REL)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(RoutesConfig.USERS_BY_USERNAME_REL)
    public ResponseEntity<UserDTO> getUserByNombreUsuario(@PathVariable String nombreUsuario) {
        UserDTO user = userService.getUserByNombreUsuario(nombreUsuario);
        return ResponseEntity.ok(user);
    }

    @GetMapping(RoutesConfig.USERS_BY_EMAIL_REL)
    public ResponseEntity<UserDTO> getUserByCorreo(@PathVariable String correo) {
        return userService.getUserByCorreo(correo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping(RoutesConfig.USERS_BY_ID_REL)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(RoutesConfig.USERS_EXISTS_USERNAME_REL)
    public ResponseEntity<Boolean> existsByNombreUsuario(@PathVariable String nombreUsuario) {
        boolean exists = userService.existsByNombreUsuario(nombreUsuario);
        return ResponseEntity.ok(exists);
    }

    @GetMapping(RoutesConfig.USERS_EXISTS_EMAIL_REL)
    public ResponseEntity<Boolean> existsByCorreo(@PathVariable String correo) {
        boolean exists = userService.existsByCorreo(correo);
        return ResponseEntity.ok(exists);
    }
}


