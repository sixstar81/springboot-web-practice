package com.sk.practice.member.domain;

public enum MemberRole {
    
    USER("일반 사용자", "ROLE_USER"),
    ADMIN("관리자", "ROLE_ADMIN");

    private final String description;
    private final String authority;

    MemberRole(String description, String authority) {
        this.description = description;
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthority() {
        return authority;
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
