package com.tenprj.multimoduleadminboard.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 하단 RoleType(String name) {this.name = name; } 지우고 -> 이 어노테이션을 추가
public enum RoleType {

        USER("ROLE_USER", "사용자"),
        MANAGER("ROLE_MANAGER", "운영자"),
        DEVELOPER("ROLE_DEVELOPER", "개발자"),
        ADMIN("ROLE_ADMIN", "관리자");

        @Getter private final String roleName; // name --> roleName
        @Getter private final String description; // name --> roleName

//        RoleType(String name) {
//            this.name = name;
//        }

}
