package com.eunbi.samokgram.home.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Contents {
    private long id;
    private int userId;
    private String textContents;
    private String imageContents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
