package com.sk.practice.issue.application;

import com.sk.practice.issue.domain.Issue;
import com.sk.practice.issue.domain.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {
    
    private final IssueRepository issueRepository;

    // 등록
    @Transactional
    public Long createIssue(Issue issue) {
        return issueRepository.save(issue).getId();
    }

    // 수정
    @Transactional
    public void updateIssue(Long id, Issue updateIssue) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));
        issue.update(updateIssue);
    }

    // 삭제
    @Transactional
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    // 단건 조회
    public Issue findIssue(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));
    }

    // 전체 조회
    public List<Issue> findAllIssues() {
        return issueRepository.findAll();
    }
}
