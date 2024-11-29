package com.sk.practice.issue.controller.dto;

import java.time.LocalDateTime;

public record IssueResponse(
    Long id,
    String title,
    String author,
    Long type,
    LocalDateTime createdAt
) {
}
