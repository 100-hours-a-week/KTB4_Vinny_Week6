package com.vinny.project.terms;

import com.vinny.project.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TermsAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agreementId;

    @Column(nullable = false)
    private Boolean isAgreed;

    @Column(nullable = false)
    private LocalDateTime agreedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected TermsAgreement() {}

    public TermsAgreement(Boolean isAgreed, Terms terms, User user) {
        this.isAgreed = isAgreed;
        this.terms = terms;
        this.user = user;
    }

}
