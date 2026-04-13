package org.example.service.impl;

import org.example.dto.UserDTO;
import org.example.exception.DuplicateResourceException;
import org.example.exception.ResourceNotFoundException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByNombreUsuario(userDTO.getNombreUsuario())) {
            throw new DuplicateResourceException("El nombre de usuario ya existe: " + userDTO.getNombreUsuario());
        }
        if (userRepository.existsByCorreo(userDTO.getCorreo())) {
            throw new DuplicateResourceException("El correo ya existe: " + userDTO.getCorreo());
        }
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (!user.getNombreUsuario().equals(userDTO.getNombreUsuario()) &&
                userRepository.existsByNombreUsuario(userDTO.getNombreUsuario())) {
            throw new DuplicateResourceException("El nombre de usuario ya existe: " + userDTO.getNombreUsuario());
        }

        if (!user.getCorreo().equals(userDTO.getCorreo()) &&
                userRepository.existsByCorreo(userDTO.getCorreo())) {
            throw new DuplicateResourceException("El correo ya existe: " + userDTO.getCorreo());
        }

        user.setNombreUsuario(userDTO.getNombreUsuario());
        user.setCorreo(userDTO.getCorreo());
        user.setHashContrasena(userDTO.getHashContrasena());

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByNombreUsuario(String nombreUsuario) {
        return userRepository.findByNombreUsuario(nombreUsuario)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + nombreUsuario));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserByCorreo(String correo) {
        return userRepository.findByCorreo(correo).map(userMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return userRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCorreo(String correo) {
        return userRepository.existsByCorreo(correo);
    }
}

