package org.example.mapper;

import org.example.dto.UserDTO;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNombreUsuario(user.getNombreUsuario());
        dto.setCorreo(user.getCorreo());
        dto.setHashContrasena(user.getHashContrasena());
        dto.setFechaCreacion(user.getFechaCreacion());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setNombreUsuario(dto.getNombreUsuario());
        user.setCorreo(dto.getCorreo());
        user.setHashContrasena(dto.getHashContrasena());
        user.setFechaCreacion(dto.getFechaCreacion());
        return user;
    }
}

