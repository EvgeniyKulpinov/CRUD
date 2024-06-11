package com.kulcorp.CRUD;

import com.kulcorp.CRUD.dao.RoleRepository;
import com.kulcorp.CRUD.dao.UserRepository;
import com.kulcorp.CRUD.model.Role;
import com.kulcorp.CRUD.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public CrudApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        BCryptPasswordEncoder encoder = applicationContext.getBean(BCryptPasswordEncoder.class);

        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));

        User user = new User(1L,"admin","admin","admin",1,"1",
                Set.of(roleRepository.findById(2L).get()));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}