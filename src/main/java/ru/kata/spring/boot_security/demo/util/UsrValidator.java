package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UsrDetailsService;

@Component
public class UsrValidator implements Validator {

    private final UsrDetailsService usrDetailsService;

    @Autowired
    public UsrValidator(UsrDetailsService usrDetailsService) {
        this.usrDetailsService = usrDetailsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        try {
            usrDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("username", "", "User с таким именем уже существует");
    }
}
