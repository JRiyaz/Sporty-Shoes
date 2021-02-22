package com.sportyshoes.controller;

import com.sportyshoes.entity.UserEntity;
import com.sportyshoes.model.UserRole;
import com.sportyshoes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public String findAll(Model model) {

        model.addAttribute("users", userService.findAll());
        return "all-users";
    }

    @GetMapping("sign-up")
    public String signUp(UserEntity userEntity) {

        return "sign-up";
    }

    @PostMapping("sign-up")
    public String signUp(@Valid UserEntity userEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "sign-up";

        userEntity.setUserRole(UserRole.ROLE_USER);
        userService.save(userEntity);
        return "redirect:/?sign-up=true";
    }
}
