package com.eunbi.samokgram.home.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`contents`")
@Entity
@Getter

public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String textContents;
    private String imageContents;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
