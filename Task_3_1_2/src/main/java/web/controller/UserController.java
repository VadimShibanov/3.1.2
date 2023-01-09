package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/people")
    public String index(ModelMap model) {
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "people/index";
    }

    @GetMapping("/people/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "people/show";
    }

    @GetMapping("/people/new")
    public String NewUser(@ModelAttribute("user") User user) {
        return "people/new";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "people/edit";
    }

    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Integer id) {
        userService.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/people";
    }
}