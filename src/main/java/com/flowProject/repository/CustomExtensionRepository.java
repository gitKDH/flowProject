package com.flowProject.repository;

import com.flowProject.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    @Query("SELECT c FROM CustomExtension c WHERE c.name = :name")
    Optional<CustomExtension> findByName(@Param("name") String name);

}
