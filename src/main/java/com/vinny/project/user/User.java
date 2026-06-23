package com.vinny.project.user;

import com.vinny.project.enums.UserStatus;
import com.vinny.project.enums.WithdrawReasonType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_USERS_EMAIL", columnNames = {"email"}),
                @UniqueConstraint(name = "UQ_USERS_NICKNAME", columnNames = {"nickname"})
        }
)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime lastLoginAt;

    private LocalDateTime deleteScheduledAt;

    @Column(columnDefinition = "TINYINT")
    private WithdrawReasonType withdrawReasonType;

    private String withdrawReasonDetail;

    protected User() {}

    @Builder
    public User(String email, String password, String nickname, String profileImageUrl, UserStatus status) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
    }

    public void updateLastLoginAt() {
        this.lastLoginAt = LocalDateTime.now();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateSleeperStatus() {
        this.status = UserStatus.SLEEPER;
    }

    public void withdrawUser(WithdrawReasonType withdrawReasonType, String withdrawReasonDetail) {
        this.status = UserStatus.WITHDRAW_PENDING;
        this.deleteScheduledAt = LocalDateTime.now().plusDays(30);
        this.withdrawReasonType = withdrawReasonType;
        this.withdrawReasonDetail = withdrawReasonDetail;
    }

    public void cancelWithdrawUser() {
        this.status = UserStatus.ACTIVE;
        this.deleteScheduledAt = null;
        this.withdrawReasonType = null;
        this.withdrawReasonDetail = null;
    }
}