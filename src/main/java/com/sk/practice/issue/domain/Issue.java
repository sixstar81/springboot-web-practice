package com.sk.practice.issue.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import com.sk.practice.common.BaseTimeEntity;

@Entity
@Table(name = "issues")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "author_id", nullable = false)
    private String authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_type_id", nullable = false)
    private IssueType issueType;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @Builder
    private Issue(String title, String content, String authorId, 
                 IssueType issueType, String status) {
        validateTitle(title);
        validateAuthorId(authorId);
        validateIssueType(issueType);
        
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.issueType = issueType;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 필수 입력값입니다.");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("제목은 100자를 초과할 수 없습니다.");
        }
    }

    private void validateAuthorId(String authorId) {
        if (authorId == null || authorId.trim().isEmpty()) {
            throw new IllegalArgumentException("작성자는 필수 입력값입니다.");
        }
    }

    private void validateIssueType(IssueType issueType) {
        if (issueType == null) {
            throw new IllegalArgumentException("이슈 타입은 필수 입력값입니다.");
        }
    }

    public void addAttachments(List<Attachment> attachments) {
        this.attachments.addAll(attachments);
        attachments.forEach(attachment -> attachment.setIssue(this));
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setIssue(this);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setIssue(null);
    }

    public void update(Issue updateIssue) {
        this.title = updateIssue.title;
        this.content = updateIssue.content;
        this.issueType = updateIssue.issueType;
    }
}    