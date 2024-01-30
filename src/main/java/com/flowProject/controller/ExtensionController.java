package com.flowProject.controller;

import com.flowProject.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

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
    public String addCustomExtension(@RequestParam String name) {
        extensionService.addCustomExtension(name);
        return "redirect:/extensions";
    }

    @PostMapping("/custom-extensions/{id}/remove")
    public String removeCustomExtension(@PathVariable Long id) {
        extensionService.removeCustomExtension(id);
        return "redirect:/extensions";
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
}

