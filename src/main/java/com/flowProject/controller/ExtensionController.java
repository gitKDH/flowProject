package com.flowProject.controller;

import com.flowProject.service.ExtensionService;
import com.flowProject.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class ExtensionController {


    private final ExtensionService extensionService;

    private final FileUploadService fileUploadService;

    public ExtensionController(ExtensionService extensionService, FileUploadService fileUploadService) {
        this.extensionService = extensionService;
        this.fileUploadService = fileUploadService;

    }

    @PostMapping("/fixed-extensions/{id}/block")
    public String blockFixedExtension(@PathVariable Long id) {
        extensionService.blockFixedExtension(id);
        return "redirect:/";
    }

    @PostMapping("/fixed-extensions/{id}/unblock")
    public String unblockFixedExtension(@PathVariable Long id) {
        extensionService.unblockFixedExtension(id);
        return "redirect:/";
    }

    @PostMapping("/custom-extensions")
    public ResponseEntity<?> addCustomExtension(@RequestParam String name) {
        try {
            extensionService.addCustomExtension(name);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/custom-extensions/{id}/remove")
    public ResponseEntity<?> removeCustomExtension(@PathVariable Long id) {
        try {
            extensionService.removeCustomExtension(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/extensions")
    public String extensions(Model model) {
        model.addAttribute("fixedExtensions", extensionService.getFixedExtensions());
        model.addAttribute("customExtensions", extensionService.getCustomExtensions());
        return "extensions";
    }

    @PostMapping("/fixed-extensions/block-unblock")
    public String blockUnblockFixedExtensions(@RequestParam Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Long id = Long.valueOf(entry.getKey());
            boolean isBlocked = Boolean.valueOf(entry.getValue());
            if (isBlocked) {
                extensionService.blockFixedExtension(id);
            } else {
                extensionService.unblockFixedExtension(id);
            }
        }
        return "redirect:/extensions";
    }

    @PostMapping("/fixed-extensions/{id}/block-unblock")
    public ResponseEntity<?> blockUnblockFixedExtension(@PathVariable Long id, @RequestParam boolean isBlocked) {
        try {
            if (isBlocked) {
                extensionService.blockFixedExtension(id);
            } else {
                extensionService.unblockFixedExtension(id);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-extension/{extension}")
    public ResponseEntity<?> checkExtension(@PathVariable String extension) {
        boolean hasUploadedFile = fileUploadService.hasUploadedFileWithExtension(extension);
        return ResponseEntity.ok().body(Collections.singletonMap("hasUploadedFile", hasUploadedFile));
    }

    @PostMapping("/block-and-delete/{extension}")
    public ResponseEntity<?> blockAndDelete(@PathVariable String extension) {
        try {
            fileUploadService.deleteFilesWithExtension(extension);
            extensionService.blockExtension(extension);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

