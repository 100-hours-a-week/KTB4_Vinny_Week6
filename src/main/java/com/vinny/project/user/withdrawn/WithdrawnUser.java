package com.vinny.project.user.withdrawn;

import com.vinny.project.enums.WithdrawReasonType;
import com.vinny.project.enums.WithdrawReasonTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="withdrawn_users"
)
@Getter
public class WithdrawnUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime joinedAt;

    private LocalDateTime withdrawnAt;

    @Setter
    @Convert(converter = WithdrawReasonTypeConverter.class)
    @Column(columnDefinition = "TINYINT")
    private WithdrawReasonType withdrawReasonType;

    private String withdrawReasonDetail;

    protected WithdrawnUser() {}

    public WithdrawnUser(Long userId, LocalDateTime joinedAt, LocalDateTime withdrawnAt, WithdrawReasonType withdrawReasonType, String withdrawReasonDetail) {
        this.userId = userId;
        this.joinedAt = joinedAt;
        this.withdrawnAt = withdrawnAt;
        this.withdrawReasonType = withdrawReasonType;
        this.withdrawReasonDetail = withdrawReasonDetail;
    }

}
