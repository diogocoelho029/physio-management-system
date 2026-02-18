package com.physio.authservice.bootstrap;

import com.physio.authservice.model.Role;
import com.physio.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ADMIN"));
        }

        if (roleRepository.findByName("STAFF").isEmpty()) {
            roleRepository.save(new Role(null, "STAFF"));
        }
    }
}
