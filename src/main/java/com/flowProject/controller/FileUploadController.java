package com.flowProject.controller;

import com.flowProject.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("uploadedFiles", fileUploadService.getUploadedFiles());
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes attributes) {
        try {
            fileUploadService.uploadFile(file);
            attributes.addFlashAttribute("message", "파일 업로드에 성공하였습니다. : " + file.getOriginalFilename());
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("message", "업로드 할 수 없습니다. " + e.getMessage());
        }
        model.addAttribute("uploadedFiles", fileUploadService.getUploadedFiles());
        return "redirect:/";
    }

    @DeleteMapping("/upload/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        try {
            fileUploadService.deleteFile(fileName);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
