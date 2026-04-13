package org.example.service;

import org.example.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    Optional<UserDTO> getUserById(Long id);
    UserDTO getUserByNombreUsuario(String nombreUsuario);
    Optional<UserDTO> getUserByCorreo(String correo);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByCorreo(String correo);
}

