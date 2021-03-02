package com.sportyshoes.controller;

import com.sportyshoes.entity.UserEntity;
import com.sportyshoes.model.UserRole;
import com.sportyshoes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    //    @RolesAllowed({"ROLE-USER, ROLE_ADMIN"})
    @GetMapping("all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll(Model model) {

        model.addAttribute("users", userService.findAll());
        return "all-users";
    }

    @GetMapping("view/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewUser(@PathVariable Integer id, Model model) {

        final UserEntity userEntity = userService.findById(id).get();

        model.addAttribute("user", userEntity);
        return "user-details";
    }

    @GetMapping("sign-up")
    public String signUp(UserEntity userEntity) {

        return "sign-up";
    }

    @PostMapping("sign-up")
    public String signUp(@Valid UserEntity userEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "sign-up";

        if (userEntity.getEmail().equals("j.riyazu@gmail.com"))
            userEntity.setUserRole(UserRole.ROLE_ADMIN);
        else
            userEntity.setUserRole(UserRole.ROLE_USER);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userService.save(userEntity);
        return "redirect:/?sign-up=true";
    }
}
