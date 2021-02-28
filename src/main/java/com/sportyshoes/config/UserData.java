package com.sportyshoes.config;

import com.sportyshoes.entity.UserEntity;
import com.sportyshoes.model.UserRole;
import com.sportyshoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        UserEntity admin = new UserEntity("Riyaz J",
                "j.riyazu@gmail.com",
                "Male",
                passwordEncoder.encode("admin"),
                "KR Puram",
                "Bengaluru",
                "Karnataka",
                "560006",
                true,
                true,
                true,
                true,
                true,
                UserRole.ROLE_ADMIN);

        UserEntity user = new UserEntity("Khan",
                "user@gmail.com",
                "Male",
                passwordEncoder.encode("user"),
                "KR Puram",
                "Bengaluru",
                "Karnataka",
                "560006",
                true,
                true,
                true,
                true,
                true,
                UserRole.ROLE_USER);

        List<UserEntity> users = new ArrayList<>();
        users.add(admin);
        users.add(user);
        userService.saveAll(users);
    }
}
