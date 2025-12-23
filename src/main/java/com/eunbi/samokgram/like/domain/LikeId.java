package com.eunbi.samokgram.like.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LikeId {
    private long contentsId;
    private long userId;
}
