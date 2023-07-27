package com.fc.boardproject.dto;

import com.fc.boardproject.domain.UserAccount;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserAccountDto {
    private Long id;
    private String userId;
    private String userPassword;
    private String email;
    private String nickname;
    private String memo;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public UserAccountDto() {
    }

    public UserAccountDto(Long id,
                          String userId,
                          String userPassword,
                          String email,
                          String nickname,
                          String memo,
                          LocalDateTime createdAt,
                          String createdBy,
                          LocalDateTime modifiedAt,
                          String modifiedBy) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static UserAccountDto of(Long id,
                                    String userId,
                                    String userPassword,
                                    String email,
                                    String nickname,
                                    String memo,
                                    LocalDateTime createdAt,
                                    String createdBy,
                                    LocalDateTime modifiedAt,
                                    String modifiedBy) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }

}
