package com.sk.practice.issue.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IssueTest {

    @Nested
    @DisplayName("이슈 생성 시")
    class CreateIssue {
        
        @Test
        @DisplayName("필수값이 모두 있다면 생성에 성공한다")
        void createSuccess() {
            // given
            String title = "테스트 이슈";
            String content = "테스트 내용";
            String authorId = "TEST001";
            IssueType issueType = createIssueType("BUG", "버그");

            // when
            Issue issue = Issue.builder()
                .title(title)
                .content(content)
                .authorId(authorId)
                .issueType(issueType)
                .build();

            // then
            assertThat(issue.getTitle()).isEqualTo(title);
            assertThat(issue.getContent()).isEqualTo(content);
            assertThat(issue.getAuthorId()).isEqualTo(authorId);
            assertThat(issue.getIssueType()).isEqualTo(issueType);
        }

        @ParameterizedTest
        @DisplayName("제목이 null이거나 비어있으면 예외가 발생한다")
        @NullAndEmptySource
        @ValueSource(strings = {" ", "  "})
        void throwExceptionWhenTitleIsNullOrEmpty(String title) {
            // given
            String authorId = "TEST001";
            IssueType issueType = createIssueType("BUG", "버그");

            // when & then
            assertThatThrownBy(() -> Issue.builder()
                .title(title)
                .content("내용")
                .authorId(authorId)
                .issueType(issueType)
                .build()
            )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제목은 필수 입력값입니다.");
        }

        @ParameterizedTest
        @DisplayName("작성자 ID가 null이거나 비어있으면 예외가 발생한다")
        @NullAndEmptySource
        @ValueSource(strings = {" ", "  "})
        void throwExceptionWhenAuthorIdIsNullOrEmpty(String authorId) {
            // given
            IssueType issueType = createIssueType("BUG", "버그");

            // when & then
            assertThatThrownBy(() -> Issue.builder()
                .title("제목")
                .content("내용")
                .authorId(authorId)
                .issueType(issueType)
                .build()
            )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("작성자는 필수 입력값입니다.");
                
        }

        @Test
        @DisplayName("이슈 타입이 null이면 예외가 발생한다")
        void throwExceptionWhenIssueTypeIsNull() {
            // when & then
            assertThatThrownBy(() -> Issue.builder()
                .title("제목")
                .content("내용")
                .authorId("TEST001")
                .issueType(null)
                .build()
            )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이슈 타입은 필수 입력값입니다.");
        }
    }

    @Nested
    @DisplayName("첨부파일 관리")
    class AttachmentManagement {
        
        @Test
        @DisplayName("첨부파일을 추가할 수 있다")
        void addAttachment() {
            // given
            Issue issue = createIssue();
            Attachment attachment = createAttachment();

            // when
            issue.addAttachment(attachment);

            // then
            assertThat(issue.getAttachments()).hasSize(1);
            assertThat(issue.getAttachments()).contains(attachment);
        }

        @Test
        @DisplayName("첨부파일을 제거할 수 있다")
        void removeAttachment() {
            // given
            Issue issue = createIssue();
            Attachment attachment = createAttachment();
            issue.addAttachment(attachment);

            // when
            issue.removeAttachment(attachment);

            // then
            assertThat(issue.getAttachments()).isEmpty();
        }
    }

    // 테스트 헬퍼 메서드
    private IssueType createIssueType(String name, String description) {
        return IssueType.builder()
            .name(name)
            .description(description)
            .build();
    }

    private Issue createIssue() {
        return Issue.builder()
            .title("테스트 이슈")
            .content("테스트 내용")
            .authorId("TEST001")
            .issueType(createIssueType("BUG", "버그"))
            .build();
    }

    private Attachment createAttachment() {
        return Attachment.builder()
            .originalFileName("test.txt")
            .storedFileName("stored_test.txt")
            .filePath("/test/path")
            .fileSize(1000L)
            .contentType("text/plain")
            .build();
    }
}
