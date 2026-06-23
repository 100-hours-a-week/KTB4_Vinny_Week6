package com.vinny.project.terms;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="terms",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_terms_title_version",
                        columnNames = {"title", "version"}
                )
        })
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,  unique = true)
    private String title;

    private String content_url;

    private Boolean isRequired;

    @Column(nullable = false, length = 10)
    private String version;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "terms")
    private List<TermsAgreement> agreements = new ArrayList<>();

    protected Terms() {}

    public Terms(String title, String content_url, Boolean isRequired, String version) {
        this.title = title;
        this.content_url = content_url;
        this.isRequired = isRequired;
        this.version = version;
    }
}
