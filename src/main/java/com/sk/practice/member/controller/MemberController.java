package com.sk.practice.member.controller;

import com.sk.practice.member.controller.dto.MemberRequest;
import com.sk.practice.member.controller.dto.MemberResponse;
import com.sk.practice.member.domain.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원관리", description = "Member API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 생성", description = "회원을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "회원 생성 성공")
    @PostMapping
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(
                new MemberResponse(
                        memberService.save(request.toEntity())
                )
        );
    }

    @Operation(summary = "회원 조회", description = "회원을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공")
    @GetMapping("/{employeeId}")
    public ResponseEntity<MemberResponse> findByEmployeeId(@PathVariable String employeeId) {
        return ResponseEntity.ok(
                new MemberResponse(
                        memberService.findByEmployeeId(employeeId)
                )
        );
    }
}
