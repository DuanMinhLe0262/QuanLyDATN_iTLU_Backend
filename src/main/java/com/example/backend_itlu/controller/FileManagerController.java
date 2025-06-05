package com.example.backend_itlu.controller;

import com.example.backend_itlu.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@RestController
public class FileManagerController {

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("upload-files")
    public void uploadFile(@RequestParam("files") MultipartFile[] files) {
        try {
            fileStorageService.saveFiles(files);
        } catch (IOException e) {
            log.error("Error saving file", e);
        }
    }

    @GetMapping("download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
        try {
            var fileToDownload = fileStorageService.getDownloadFile(fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new FileSystemResource(fileToDownload));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
