package com.sk.practice.member.controller;

import com.sk.practice.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {
    
    @NotBlank(message = "사번은 필수입니다.")
    private String employeeId;
    
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    
    public Member toEntity() {
        return Member.builder()
                .employeeId(employeeId)
                .name(name)
                .email(email)
                .build();
    }
}
