package com.sk.practice.issue.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    // 기본 페이징 조회
    @NonNull
    Page<Issue> findAll(@NonNull Pageable pageable);

    // 작성자별 페이징 조회
    Page<Issue> findByAuthorId(String authorId, Pageable pageable);

    // 이슈 타입별 페이징 조회
    Page<Issue> findByIssueType(IssueType issueType, Pageable pageable);

    // 키워드 검색 페이징 조회
    @Query("SELECT i FROM Issue i WHERE i.title LIKE %:keyword% OR i.content LIKE %:keyword%")
    Page<Issue> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
