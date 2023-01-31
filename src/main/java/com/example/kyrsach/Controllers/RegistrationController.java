package com.example.kyrsach.Controllers;

import com.example.kyrsach.Models.Role;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String reg() {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(User user,
                      Model model) {

        if(userRepos.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepos.save(user);

        return ("redirect:/login");
    }
}
