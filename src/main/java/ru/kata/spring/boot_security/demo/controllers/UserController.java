package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UsrService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UsrService usrService;

    @Autowired
    public UserController(UsrService usrService) {
        this.usrService = usrService;
    }


    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("users", usrService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/view")
    public String getUserById(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", usrService.readUser(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        usrService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", usrService.readUser(id));
        return "users/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        usrService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        usrService.deleteUser(id);
        return "redirect:/users";
    }
}
