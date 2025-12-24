package com.eunbi.samokgram.comment.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDetail {

    private long id;

    private long userId;
    private String imagePath;

    private String loginId;
    private String contents;

    private int likeCount;
    private boolean isLike;
}
