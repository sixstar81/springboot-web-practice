package com.sk.practice.member.controller;

import com.sk.practice.member.domain.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(
                new MemberResponse(
                        memberService.save(request.toEntity())
                )
        );
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<MemberResponse> findByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(
                new MemberResponse(
                        memberService.findByEmployeeId(employeeId)
                )
        );
    }
}
