package com.sportyshoes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserData implements CommandLineRunner {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        /*UserEntity admin = new UserEntity("Riyaz J",
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
        userService.saveAll(users);*/
    }
}
