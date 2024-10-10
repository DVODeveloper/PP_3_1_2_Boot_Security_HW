package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UsrService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void createUser(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    User readUser(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateUser(int id, User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteUser(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> getAllUsers();
}
