package com.hyeonah.urlshorteningservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "URL_SEQ_GENERATOR", sequenceName = "URL_SEQ", initialValue = 1000000, allocationSize = 1)
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "URL_SEQ_GENERATOR")
    private Long id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "shortening_key")
    private String shorteningKey;

    @Column(name = "req_count")
    private int reqCount;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void addReqCount() {
        this.reqCount += 1;
    }
}
