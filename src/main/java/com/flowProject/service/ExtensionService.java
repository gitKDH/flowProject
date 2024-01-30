package com.flowProject.service;

import com.flowProject.entity.CustomExtension;
import com.flowProject.entity.FixedExtension;
import com.flowProject.repository.CustomExtensionRepository;
import com.flowProject.repository.FixedExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtensionService {

    @Autowired
    private FixedExtensionRepository fixedExtensionRepository;

    @Autowired
    private CustomExtensionRepository customExtensionRepository;

    public List<FixedExtension> getFixedExtensions() {
        return fixedExtensionRepository.findAll();
    }

    public List<CustomExtension> getCustomExtensions() {
        return customExtensionRepository.findAll();
    }

    public void blockFixedExtension(Long id) {
        FixedExtension extension = fixedExtensionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 확장자 ID : " + id));
        extension.setIsBlocked(true);
        fixedExtensionRepository.save(extension);
    }

    public void unblockFixedExtension(Long id) {
        FixedExtension extension = fixedExtensionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 확장자 ID: " + id));
        extension.setIsBlocked(false);
        fixedExtensionRepository.save(extension);
    }

    public void addCustomExtension(String name) {
        if (name.length() > 20) {
            throw new IllegalArgumentException("확장자가 너무 깁니다 (20자 이내) : " + name);
        }
        Optional<CustomExtension> existingExtension = customExtensionRepository.findByName(name);
        if(existingExtension.isPresent()){
            throw new IllegalArgumentException("이미 차단된 확장자입니다." + name);
        }
        CustomExtension extension = new CustomExtension();
        extension.setName(name);
        customExtensionRepository.save(extension);
    }

    public void removeCustomExtension(Long id) {
        if (!customExtensionRepository.existsById(id)) {
            throw new IllegalArgumentException("잘못된 확장자 ID : " + id);
        }
        customExtensionRepository.deleteById(id);
    }

    public boolean isBlocked(String extension) {
        Optional<FixedExtension> fixedExtension = fixedExtensionRepository.findByNameAndIsBlocked(extension, true);
        if (fixedExtension.isPresent()) {
            return true;
        }

        Optional<CustomExtension> customExtension = customExtensionRepository.findByName(extension);
        return customExtension.isPresent();
    }


}

