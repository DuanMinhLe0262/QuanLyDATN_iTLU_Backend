package com.example.backend_itlu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private static final String STORAGE_DIRECTORY = "D:\\Backend_storage\\Storage";

    public String saveFile(MultipartFile fileToSave) throws IOException {
        if (fileToSave == null || fileToSave.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }

        // Đảm bảo thư mục tồn tại
        File dir = new File(STORAGE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = fileToSave.getOriginalFilename();
        File targetFile = new File(STORAGE_DIRECTORY + File.separator + fileName);

        if (!Objects.equals(targetFile.getParentFile().getAbsolutePath(), dir.getAbsolutePath())) {
            throw new SecurityException("Unsupported filename!");
        }

        Files.copy(fileToSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Trả về URL hoặc đường dẫn để lưu vào DB
        return "/files/" + fileName;
    }

    public File getDownloadFile(String fileName) throws Exception {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName is null");
        }

        File fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);

        if (!fileToDownload.getCanonicalFile().getParentFile().equals(new File(STORAGE_DIRECTORY).getCanonicalFile())) {
            throw new SecurityException("Invalid file path");
        }

        if (!fileToDownload.exists()) {
            throw new FileNotFoundException("No file named: " + fileName);
        }

        return fileToDownload;
    }
}
