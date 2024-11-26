package com.sk.practice.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member save(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public Member findByEmployeeId(String employeeId) {
        return memberRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사원입니다."));
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.existsByEmployeeId(member.getEmployeeId())) {
            throw new IllegalArgumentException("이미 존재하는 사원입니다.");
        }
    }
}
