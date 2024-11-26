package com.sk.practice.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "issues")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_type_id", nullable = false)
    private IssueType issueType;

    @Column
    private String status;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @Builder
    private Issue(String title, String content, String author, 
                 IssueType issueType, String status) {
        validateTitle(title);
        validateAuthor(author);
        validateIssueType(issueType);
        
        this.title = title;
        this.content = content;
        this.author = author;
        this.issueType = issueType;
        this.status = status != null ? status : "OPEN";
        this.createdAt = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수 입력값입니다.");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("제목은 100자를 초과할 수 없습니다.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("작성자는 필수 입력값입니다.");
        }
    }

    private void validateIssueType(IssueType issueType) {
        if (issueType == null) {
            throw new IllegalArgumentException("이슈 타입은 필수 입력값입니다.");
        }
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setIssue(this);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setIssue(null);
    }
}    
    
    
