package com.sk.practice.issue.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.practice.issue.application.IssueService;
import com.sk.practice.issue.controller.dto.IssueResponse;
import com.sk.practice.issue.domain.Issue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/issues")
public class IssueViewController {

    private final IssueService issueService;
    
    @GetMapping
    public String listIssues(Model model) {
        List<Issue> issues = issueService.findAllIssues();
        List<IssueResponse> issueResponses = issues.stream()
                .map(issue -> new IssueResponse(issue.getId(), issue.getTitle(), issue.getAuthorId(), issue.getIssueTypeId(), issue.getCreatedAt()))
                .collect(Collectors.toList());
        model.addAttribute("issues", issueResponses);
        //Thymeleaf 의 fragment 기능을 사용하여 부분 템플릿 삽입
        model.addAttribute("content", "issue/list :: content");
        return "layout/main";
    }

    @GetMapping("list")
    public String listIssues2(Model model) {
        List<Issue> issues = issueService.findAllIssues();
        List<IssueResponse> issueResponses = issues.stream()
                .map(issue -> new IssueResponse(issue.getId(), issue.getTitle(), issue.getAuthorId(), issue.getIssueTypeId(), issue.getCreatedAt()))
                .collect(Collectors.toList());
        model.addAttribute("issues", issueResponses);
        //Thymeleaf 의 fragment 기능을 사용하여 부분 템플릿 삽입
        return "issue/list";
    }
}
