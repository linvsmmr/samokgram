package com.eunbi.samokgram.comment.service;

import com.eunbi.samokgram.comment.domain.Comment;
import com.eunbi.samokgram.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepository;

    public boolean createComment(long contentsId, long userId, String comment) {

        Comment comments = Comment.builder().contentsId(contentsId).userId(userId).comments(comment).build();


        try {
            commentRepository.save(comments);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }
}
