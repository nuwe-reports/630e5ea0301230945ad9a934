package com.easy_job_search.service;

import com.easy_job_search.entity.Address;
import com.easy_job_search.entity.User;
import com.easy_job_search.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Address address = new Address();
        address.setId(1);
        address.setStreet("planeta azul");
        address.setNumber(1);
        address.setFloor("bajo");
        address.setDoor("1");
        address.setCity("azulea");
        address.setCountry("azul resplandeciente");
        address.setPlanet("Tierra");

        user = new User();
        user.setId(1);
        user.setEmail("user@email.com");
        user.setFullName("Jose Perez");
        user.setPassword("prueba1234");
        user.setAddress(address);

    }

    @Test
    void findAllUser() {
        when(userRepo.findAll()).thenReturn(Arrays.asList(user));
        assertEquals(userService.findAllUser(), Arrays.asList(user));
    }

    @Test
    void findUserById() {
        when(userRepo.findUserById(user.getId())).thenReturn(Optional.of(user));
        assertEquals(userService.findUserById(1), user);
    }

    @Test
    void findUserByEmail() {
        when(userRepo.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(userService.findUserByEmail("user@email.com"), user);
    }
}