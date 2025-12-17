package com.eunbi.samokgram.like.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(LikeId.class)
@Entity
@Table(name = "`like`")

public class Like {
    @Id
    private long contentsId;
    @Id
    private long userId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
