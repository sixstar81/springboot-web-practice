package com.sk.practice.issue.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueTypeRepository issueTypeRepository;

    @BeforeEach
    void setUp() {
        issueRepository.deleteAll();
        issueTypeRepository.deleteAll();
    }

    @Test
    void 이슈_페이징_조회() {
        // given
        IssueType issueType = IssueType.builder().name("장애").build();
        issueTypeRepository.save(issueType);
        for (int i = 0; i < 20; i++) {
            Issue issue = Issue.builder()
                    .title("테스트 이슈 " + i)
                    .authorId("190726")
                    .issueType(issueType)
                    .content("테스트 내용 " + i)
                    .build();
            issueRepository.save(issue);
        }

        // when
        Pageable pageable = PageRequest.of(0, 10);
        Page<Issue> issues = issueRepository.findAll(pageable);

        // then
        assertThat(issues).isNotNull();
        assertThat(issues.getContent()).hasSize(10);
        assertThat(issues.getTotalElements()).isEqualTo(20);
        assertThat(issues.getTotalPages()).isEqualTo(2);
    }

    @Test
    void 키워드로_이슈_검색() {
        // given
        IssueType issueType = IssueType.builder()
                .name("장애")
                .build();
        issueTypeRepository.save(issueType);

        Issue issue1 = Issue.builder()
                .title("테스트 이슈")
                .content("검색용 컨텐츠입니다")
                .authorId("190726")
                .issueType(issueType)
                .build();

        Issue issue2 = Issue.builder()
                .title("다른 이슈")
                .content("테스트 검색용 내용")
                .authorId("190726")
                .issueType(issueType)
                .build();

        Issue issue3 = Issue.builder()
                .title("관련없는 이슈")
                .content("관련없는 내용")
                .authorId("190726")
                .issueType(issueType)
                .build();

        issueRepository.saveAll(Arrays.asList(issue1, issue2, issue3));

        // when
        Pageable pageable = PageRequest.of(0, 10);
        Page<Issue> result = issueRepository.searchByKeyword("테스트", pageable);

        // then
        assertThat(result.getContent()).hasSize(2);

        assertThat(result.getContent())
        .extracting("title")
        .containsExactlyInAnyOrder("테스트 이슈", "다른 이슈");
        
        assertThat(result.getContent())
        .extracting("content")
        .containsExactlyInAnyOrder("검색용 컨텐츠입니다", "테스트 검색용 내용");
    }
}
