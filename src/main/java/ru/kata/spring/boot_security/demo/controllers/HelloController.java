package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.security.UsrDetails;
import ru.kata.spring.boot_security.demo.services.UsrService;

@Controller
public class HelloController {

    private final UsrService usrService;

    @Autowired
    public HelloController(UsrService usrService) {
        this.usrService = usrService;
    }

    @GetMapping("/hello")
    public String showUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsrDetails usrDetails = (UsrDetails) authentication.getPrincipal();
        model.addAttribute("usrDetails", usrService.readUser(usrDetails.getUser().getId()));
        System.out.println(usrDetails.getUser());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

}
