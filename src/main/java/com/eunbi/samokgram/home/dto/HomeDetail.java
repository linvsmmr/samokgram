package com.eunbi.samokgram.home.dto;


import com.eunbi.samokgram.comment.domain.Comment;
import com.eunbi.samokgram.comment.dto.CommentDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HomeDetail {
    private long id;

    private String contents;
    private String imagePath;

    private long userId;
    private String loginId;

    private int likeCount;
    private boolean isLike;

    private List<CommentDetail> comments;

}
