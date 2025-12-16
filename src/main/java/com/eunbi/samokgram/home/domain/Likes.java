package com.eunbi.samokgram.home.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`likes`")

public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int likedContentsId;
    private int likedUserId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
