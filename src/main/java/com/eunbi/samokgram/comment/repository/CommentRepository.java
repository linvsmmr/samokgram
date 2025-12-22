package com.eunbi.samokgram.comment.repository;

import com.eunbi.samokgram.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    public List<Comment> findByContentsId(long contentsId);




}
