package com.flowProject.controller;

import com.flowProject.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

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
            attributes.addFlashAttribute("message", "업로드 할 수 없는 확장자입니다. " + e.getMessage());
        }
        model.addAttribute("uploadedFiles", fileUploadService.getUploadedFiles());
        return "redirect:/";
    }
}
