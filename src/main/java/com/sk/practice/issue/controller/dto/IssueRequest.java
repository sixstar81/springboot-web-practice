package com.sk.practice.issue.controller.dto;

import com.sk.practice.issue.domain.Issue;
import com.sk.practice.issue.domain.IssueType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class IssueRequest {

    private String title;
    private String content;
    private IssueType issueType;
    private List<MultipartFile> files;

    public Issue toEntity() {
        return Issue.builder()
                .title(title)
                .content(content)
                .issueType(issueType)
                .build();
    }
}
