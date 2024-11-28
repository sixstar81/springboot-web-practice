package com.sk.practice.issue.controller;

import com.sk.practice.issue.application.FileService;
import com.sk.practice.issue.application.IssueService;
import com.sk.practice.issue.controller.dto.IssueRequest;
import com.sk.practice.issue.domain.Attachment;
import com.sk.practice.issue.domain.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createIssue(@ModelAttribute IssueRequest request) throws IOException {
        

        List<Attachment> saveFiles = fileService.saveFiles(request.getFiles());

        Issue entity = request.toEntity();

        entity.addAttachments(
            saveFiles
        );

        
        Long issueId = issueService.createIssue(entity);

        return ResponseEntity.ok(issueId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable Long id) {
        Issue issue = issueService.findIssue(id);
        return ResponseEntity.ok(issue);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.findAllIssues();
        return ResponseEntity.ok(issues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateIssue(@PathVariable Long id, @RequestBody Issue issue) {
        issueService.updateIssue(id, issue);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok().build();
    }
}
