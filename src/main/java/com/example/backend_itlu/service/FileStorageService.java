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

    public void saveFiles(MultipartFile[] filesToSave) throws IOException {

        if(filesToSave == null || filesToSave.length == 0){
            throw new IllegalArgumentException("fileToSave is null");
        }

        for (MultipartFile fileToSave : filesToSave) {
            saveFile(fileToSave);
        }
    }

    public void saveFile(MultipartFile fileToSave) throws IOException {

        if(fileToSave == null){
            throw new IllegalArgumentException("fileToSave is null");
        }
        var targetFile = new File(STORAGE_DIRECTORY + "/" + fileToSave.getOriginalFilename());
        if(!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)){
            throw new SecurityException("Unsupported filename!");
        }
        Files.copy(fileToSave.getInputStream(),targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public File getDownloadFile(String fileName) throws Exception {

        if(fileName == null){
            throw new IllegalArgumentException("fileName is null");
        }
        var fileToDownload = new File(STORAGE_DIRECTORY + "/" + File.separator + fileName);
        if(!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)){
            throw new SecurityException("Unsupported filename!");
        }
        if(!fileToDownload.exists()){
            throw new FileNotFoundException("No file named: " +  fileName);
        }
        return fileToDownload;
    }
}
