package com.flowProject.config;

import com.flowProject.entity.FixedExtension;
import com.flowProject.repository.FixedExtensionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class ExtensionConfiguration {

    @Bean
    public CommandLineRunner insertFixedExtensions(FixedExtensionRepository fixedExtensionRepository) {
        return args -> {
            List<String> fixedExtensions = Arrays.asList("bat", "cmd", "com", "cpl", "exe", "scr", "js");

            for (String extension : fixedExtensions) {
                Optional<FixedExtension> existingExtension = fixedExtensionRepository.findByName(extension);
                if (!existingExtension.isPresent()) {
                    FixedExtension newExtension = new FixedExtension();
                    newExtension.setName(extension);
                    newExtension.setIsBlocked(false);
                    fixedExtensionRepository.save(newExtension);
                }
            }
        };
    }
}
