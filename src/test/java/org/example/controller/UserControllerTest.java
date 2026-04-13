package org.example.controller;

import org.example.dto.UserDTO;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new UserDTO();
        testUser.setId(1L);
        testUser.setNombreUsuario("test_user");
        testUser.setCorreo("test@example.com");
        testUser.setHashContrasena("password123");
    }

    @Test
    public void testCreateUser() {
        when(userService.createUser(any(UserDTO.class))).thenReturn(testUser);

        ResponseEntity<UserDTO> response = userController.createUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        verify(userService, times(1)).createUser(any(UserDTO.class));
    }

    @Test
    public void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(testUser));

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userService.getUserById(999L)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserById(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(999L);
    }

    @Test
    public void testUpdateUser() {
        when(userService.updateUser(1L, testUser)).thenReturn(testUser);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        verify(userService, times(1)).updateUser(1L, testUser);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }
}

