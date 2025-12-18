package com.eunbi.samokgram.like.service;

import com.eunbi.samokgram.like.domain.Like;
import com.eunbi.samokgram.like.repository.LikeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public boolean createLike(long contentsId, long userId) {

        Like like = Like.builder().contentsId(contentsId).userId(userId).build();

        try {
            likeRepository.save(like);
        } catch (DataAccessException e) {
            return false;
        }


        return true;

    }

    public int countByPostId(long contentsId) {
        return likeRepository.countLikeByContentsId(contentsId);
    }



    public boolean isLikeByContentsIdAndUserId(long contentsId, long userId) {
        return likeRepository.existsByContentsIdAndUserId(contentsId, userId);
    };
}
