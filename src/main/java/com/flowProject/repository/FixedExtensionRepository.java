package com.flowProject.repository;

import com.flowProject.entity.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FixedExtensionRepository extends JpaRepository<FixedExtension, Long> {
    Optional<FixedExtension> findByNameAndIsBlocked(String name, boolean isBlocked);

    Optional<FixedExtension> findByName(String extension);
}
