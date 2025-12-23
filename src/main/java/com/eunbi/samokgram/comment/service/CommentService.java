package com.eunbi.samokgram.comment.service;

import com.eunbi.samokgram.comment.domain.Comment;
import com.eunbi.samokgram.comment.dto.CommentDetail;
import com.eunbi.samokgram.comment.repository.CommentRepository;
import com.eunbi.samokgram.user.domain.User;
import com.eunbi.samokgram.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final UserService userService;

    public boolean createComment(long contentsId, long userId, String comment) {

        Comment comments = Comment.builder().contentsId(contentsId).userId(userId).comments(comment).build();


        try {
            commentRepository.save(comments);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }


    public List<CommentDetail> getCommentsByContentsId(long contentsId) {

        List<Comment> commentList = commentRepository.findByContentsId(contentsId);

        List<CommentDetail> commentDetailList = new ArrayList<>();


        for (Comment comment:commentList) {
            User user = userService.getUserById(comment.getUserId());
            CommentDetail commentDetail = CommentDetail.builder()
                    .id(comment.getId())
                    .userId(comment.getUserId())
                    .loginId(user.getLoginId())
                    .contents(comment.getComments())
                    .build();

            commentDetailList.add(commentDetail);
        }
        return commentDetailList;
    }

    @Transactional
    public void deleteCommentByContentsId(long contentsId) {
        commentRepository.deleteByContentsId(contentsId);
    }
}
