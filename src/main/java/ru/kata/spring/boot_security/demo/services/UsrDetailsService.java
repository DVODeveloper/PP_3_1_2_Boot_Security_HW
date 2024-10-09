package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsrRepository;
import ru.kata.spring.boot_security.demo.security.UsrDetails;

import java.util.Optional;

@Service
public class UsrDetailsService implements UserDetailsService {

    private final UsrRepository usrRepository;

    @Autowired
    public UsrDetailsService(UsrRepository usrRepository) {
        this.usrRepository = usrRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usrRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UsrDetails(user.get());
    }
}
