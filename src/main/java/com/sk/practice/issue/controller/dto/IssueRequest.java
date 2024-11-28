package com.sk.practice.issue.controller.dto;

import com.sk.practice.issue.domain.Issue;
import com.sk.practice.issue.domain.IssueType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IssueRequest {

    private String title;
    private String content;
    private List<MultipartFile> files;

    public Issue toEntity() {
        return Issue.builder()
                .title(title)
                .content(content)
                .authorId("190726")
                .issueType(IssueType.builder().name("BUG").build())
                .build();
    }
}
