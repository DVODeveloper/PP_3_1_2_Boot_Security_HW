package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RegistrationUsrService;
import ru.kata.spring.boot_security.demo.util.UsrValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationUsrService registrationUsrService;
    private final UsrValidator usrValidator;

    @Autowired
    public AuthController(RegistrationUsrService registrationUsrService, UsrValidator usrValidator) {
        this.registrationUsrService = registrationUsrService;
        this.usrValidator = usrValidator;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        usrValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }

        registrationUsrService.register(user);

        return "redirect:/auth/login";
    }

}
