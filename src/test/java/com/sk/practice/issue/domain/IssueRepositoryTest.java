package com.sk.practice.issue.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueTypeRepository issueTypeRepository;

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
}
