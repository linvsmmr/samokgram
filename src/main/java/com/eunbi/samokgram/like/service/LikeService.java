package com.eunbi.samokgram.like.service;

import com.eunbi.samokgram.like.domain.Like;
import com.eunbi.samokgram.like.domain.LikeId;
import com.eunbi.samokgram.like.repository.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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



    public boolean deleteLike(long contentsId, long userId) {
        LikeId likeId = LikeId.builder().contentsId(contentsId).userId(userId).build();
        Optional<Like> optionalLike = likeRepository.findById(likeId);

        if (optionalLike.isPresent()){
            try {
                likeRepository.delete(optionalLike.get());
            } catch (DataAccessException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


    @Transactional
    public void deleteLikeByContentsId(long contentsId) {
        likeRepository.deleteByContentsId(contentsId);
    }




}
