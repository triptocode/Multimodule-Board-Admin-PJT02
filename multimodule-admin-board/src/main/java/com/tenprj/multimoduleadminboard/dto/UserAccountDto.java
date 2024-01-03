package com.tenprj.multimoduleadminboard.dto;

import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
//        Set<RoleType> roleTypes,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static UserAccountDto of(String userId,  String email, String nickname, String memo) {
        return new UserAccountDto(userId , email, nickname, memo, null, null, null, null);
    }

    public static UserAccountDto of(String userId, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId,  email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }


}
