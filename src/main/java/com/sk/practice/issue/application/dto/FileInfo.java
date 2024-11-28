package com.sk.practice.issue.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class FileInfo {

    @NotBlank(message = "원본 파일명은 필수입니다")
    @Size(max = 255, message = "파일명은 255자를 초과할 수 없습니다")
    private String originalFileName;

    @NotBlank(message = "저장 파일명은 필수입니다")
    @Size(max = 255, message = "파일명은 255자를 초과할 수 없습니다")
    private String savedFileName;

    @NotBlank(message = "파일 경로는 필수입니다")
    @Size(max = 1000, message = "파일 경로는 1000자를 초과할 수 없습니다")
    private String filePath;

    @NotBlank(message = "파일 타입은 필수입니다")
    @Pattern(regexp = "^[a-zA-Z0-9/.-]+$", message = "파일 타입 형식이 올바르지 않습니다")
    private String fileType;

    @Positive(message = "파일 크기는 0보다 커야 합니다")
    @Max(value = 10485760, message = "파일 크기는 10MB를 초과할 수 없습니다") // 10MB 제한 예시
    private long fileSize;
}
