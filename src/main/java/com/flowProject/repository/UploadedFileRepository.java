package com.flowProject.repository;

import com.flowProject.entity.UploadedFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UploadedFile u WHERE u.fileName = :fileName")
    void deleteByFileName(@Param("fileName") String fileName);
    UploadedFile findByFileName(String fileName);
}
