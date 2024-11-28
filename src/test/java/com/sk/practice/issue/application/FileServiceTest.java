package com.sk.practice.issue.application;

import com.sk.practice.issue.application.dto.FileInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class FileServiceTest {

    private FileService fileService;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        FileConfig fileConfig = new FileConfig();
        fileConfig.setUploadPath(tempDir.toString());
        fileService = new FileService(fileConfig);
    }

    @Test
    @DisplayName("파일 저장 성공 테스트")
    void saveFileSuccess() throws IOException {
        // given
        String filename = "test.txt";
        String contentType = "text/plain";
        byte[] content = "테스트 파일 내용".getBytes();
        
        MockMultipartFile file = new MockMultipartFile(
            "file", 
            filename,
            contentType,
            content
        );

        // when
        FileInfo fileInfo = fileService.saveFile(Collections.singletonList(file)).get(0);

        // then
        assertThat(fileInfo).isNotNull();
        assertThat(fileInfo.getOriginalFileName()).isEqualTo(filename);
        assertThat(fileInfo.getFileSize()).isEqualTo(content.length);
        
        // 실제 파일이 저장되었는지 확인
        Path savedFilePath = Path.of(fileInfo.getFilePath());
        assertThat(Files.exists(savedFilePath)).isTrue();
        assertThat(Files.size(savedFilePath)).isEqualTo(content.length);
    }


}
