package com.sk.practice.member.domain;

import org.springframework.util.StringUtils;

import com.sk.practice.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Builder
    private Member(String employeeId, String name, MemberRole role, 
                  String phoneNumber, String email) {
        validateEmployeeId(employeeId);
        validateName(name);
        
        this.employeeId = employeeId;
        this.name = name;
        this.role = role != null ? role : MemberRole.USER;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    private void validateEmployeeId(String employeeId){
        if(!StringUtils.hasText(employeeId)){
            throw new IllegalArgumentException("사원번호는 필수 입니다.");
        }
    }

    private void validateName(String name){
        if(!StringUtils.hasText(name)){
            throw new IllegalArgumentException("이름은 필수 입니다.");
        }
    }
}
