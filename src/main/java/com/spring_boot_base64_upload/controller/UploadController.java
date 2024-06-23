package com.spring_boot_base64_upload.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody Map<String, String> data) {
        String fileName = data.get("file");
        String base64 = data.get("base64");

        if (fileName == null) {
            return ResponseEntity.badRequest().body("File name is required.");
        }

        if (base64 == null) {
            return ResponseEntity.badRequest().body("Base64 data is required.");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        long fileSize = decodedBytes.length;
        return ResponseEntity.ok("File received: " + fileName + " with size: " + fileSize + " bytes.");
    }
}