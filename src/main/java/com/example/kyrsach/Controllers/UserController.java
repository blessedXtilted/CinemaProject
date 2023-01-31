package com.example.kyrsach.Controllers;


import com.example.kyrsach.Models.Role;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    UserRepos userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/ADD/user")
    public String UserAddView(User user, Model model)
    {
        model.addAttribute("users",user);
        return ("User/UserAdd");}

    @PostMapping("/ADD/user")
    public String UserAdd(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()){
            return ("User/UserAdd");}

        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return ("redirect:/Edit/user");
    }

    @GetMapping("/Delete/User/{id}")
    public String UserDelete(@PathVariable long id) {

        userRepository.deleteById(id);
        return("redirect:/Edit/user");
    }


    @GetMapping("Edit/User/{id}")
    public String UserEdit(User user, Model model, @PathVariable long id) {

        model.addAttribute("User", userRepository.findById(id).orElseThrow());
        return("User/UserEdit");

    }
    @PostMapping("Edit/User/{id}")
    public String UserEdit(@Valid User user,
                               BindingResult bindingResult,
                               Model model,
                               @PathVariable long id
    ) {
        if (bindingResult.hasErrors()){
            return ("User/User-edit");}
        userRepository.save(user);
        return ("redirect:/Edit/user");
    }


//    @GetMapping("/user/admin")
//    public String AdminPanel(Model model) {
//
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute(("users"), users);
//        return ("employee/adminPanel");
//    }
//
//    @GetMapping("/user/admin/Edit-role/{id}")
//    public String userRoleEdit(Model model,
//                                   @PathVariable long id) {
//
//        User user = userRepository.findById(id).orElseThrow();
//        model.addAttribute("users", user);
//        model.addAttribute("Roles", Role.values());
//        return("/employee/Edit-role");
//    }
//
//    @PostMapping("/user/admin/Edit-role/{id}")
//    public String EmployeeRoleEdit(@PathVariable long id,
//                                   @RequestParam String[] roles) {
//
//        User user = userRepository.findById(id).orElseThrow();
//        user.getRoles().clear();
//
//        for(String role: roles){
//            user.getRoles().add(Role.valueOf(role));
//        }
//
//        userRepository.save(user);
//
//        return("redirect:/employee/admin");
//    }
}
