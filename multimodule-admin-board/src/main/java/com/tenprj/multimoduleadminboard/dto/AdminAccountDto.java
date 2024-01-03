package com.tenprj.multimoduleadminboard.dto;

import com.tenprj.multimoduleadminboard.domain.AdminAccount;
import com.tenprj.multimoduleadminboard.domain.constant.RoleType;

import java.time.LocalDateTime;
import java.util.Set;

public record AdminAccountDto(
        String userId,
        String userPassword,
        Set<RoleType> roleTypes,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
        // roleTypes 추가  /return new UserAccountDto()> return UserAccountDto.of()
    public static AdminAccountDto of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return AdminAccountDto.of(userId, userPassword, roleTypes, email, nickname, memo, null, null, null, null);
    }

    public static AdminAccountDto of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AdminAccountDto(userId, userPassword, roleTypes, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AdminAccountDto from(AdminAccount entity) {
        return new AdminAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getRoleTypes(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public AdminAccount toEntity() {
        return AdminAccount.of( // 내보내는 부분 / admin 관련된 부분에 user부분은 admin으로 수정하여 troulbleshooting
                userId,
                userPassword,
                roleTypes,
                email,
                nickname,
                memo
        );
    }

}
