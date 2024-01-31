package com.flowProject.service;

import com.flowProject.entity.UploadedFile;
import com.flowProject.repository.UploadedFileRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileUploadService {

    @Autowired
    private final UploadedFileRepository uploadedFileRepository;

    public FileUploadService(UploadedFileRepository uploadedFileRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Autowired
    private ExtensionService extensionService;

    public void uploadFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extensionService.isBlocked(extension)) {
            throw new IllegalArgumentException("차단된 확장자입니다. : " + extension);
        }

        if (uploadedFileRepository.findByFileName(file.getOriginalFilename()) != null) {
            throw new IllegalArgumentException("이미 등록된 파일입니다. : " + file.getOriginalFilename());
        }

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName(file.getOriginalFilename());
        uploadedFileRepository.save(uploadedFile);
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFileRepository.findAll();
    }

    public void deleteFile(String fileName) {
        uploadedFileRepository.deleteByFileName(fileName);
    }
}
