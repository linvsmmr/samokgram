package com.eunbi.samokgram.home.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HomeDetail {
    private long id;

    private String contents;
    private String imagePath;

    private long userId;
    private String loginId;

}
