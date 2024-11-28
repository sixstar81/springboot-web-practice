package com.sk.practice.issue.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sk.practice.issue.domain.Attachment;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileConfig uploadPath;

    public List<Attachment> saveFiles(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }

        System.out.println("uploadPath: " + uploadPath.getPath());

        List<Attachment> attachments = new ArrayList<>();
        
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                Path filePath = Paths.get(uploadPath.getPath(), storedFileName);
                
                // 파일 저장
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                // Attachment 엔티티 생성
                Attachment attachment = Attachment.builder()
                    .originalFileName(originalFileName)
                    .storedFileName(storedFileName)
                    .filePath(filePath.toString())
                    .fileSize(file.getSize())
                    .contentType(file.getContentType())
                    .build();
                
                attachments.add(attachment);
            }
        }
        
        return attachments;
    }
}
