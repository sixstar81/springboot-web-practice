package com.sk.practice.member.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

    Optional<Member> findByEmployeeId(String employeeId);
    boolean existsByEmployeeId(String employeeId);
}
