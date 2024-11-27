package com.sk.practice.member.controller.dto;

import com.sk.practice.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final String employeeId;
    private final String name;
    private final String email;
    
    public MemberResponse(Member member) {
        this.employeeId = member.getEmployeeId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
