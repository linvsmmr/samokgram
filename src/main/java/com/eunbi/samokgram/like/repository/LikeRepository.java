package com.eunbi.samokgram.like.repository;

import com.eunbi.samokgram.like.domain.Like;
import com.eunbi.samokgram.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    public int countLikeByContentsId(@RequestParam("contentsId") long contentsId);


    public boolean existsByContentsIdAndUserId(long contentsId, long userId);

}
