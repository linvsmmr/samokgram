package com.eunbi.samokgram.home.service;

import com.eunbi.samokgram.comment.domain.Comment;
import com.eunbi.samokgram.comment.dto.CommentDetail;
import com.eunbi.samokgram.comment.service.CommentService;
import com.eunbi.samokgram.common.FileManager;
import com.eunbi.samokgram.home.domain.Contents;
import com.eunbi.samokgram.home.dto.HomeDetail;
import com.eunbi.samokgram.home.repository.HomeRepository;
import com.eunbi.samokgram.like.service.LikeService;
import com.eunbi.samokgram.user.domain.User;
import com.eunbi.samokgram.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
// 필수 멤버변수를 생성자를 통해 대응하도록 해주는 어노테이션
@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

//    public HomeService(HomeRepository homeRepository, UserService userService, LikeService likeService) {
//        this.homeRepository = homeRepository;
//        this.userService = userService;
//        this.likeService = likeService;
//    }


    public boolean createContents(
            long userId,
            MultipartFile imageFile,
            String contents
    ) {
        String imagePath = FileManager.saveFile(userId, imageFile);

        Contents upload = Contents.builder().userId(userId).imageContents(imagePath).textContents(contents).build();

        try{
            homeRepository.save(upload);
        } catch (DataAccessException e) {
            return false;
        }

        return true;
    }

    public List<HomeDetail> getContentsList(long userId) {
        List<Contents> contentsList = homeRepository.findAll(Sort.by("id").descending());

        List<HomeDetail> detailList = new ArrayList<>();

        for (Contents contents:contentsList) {

            User user = userService.getUserById(contents.getUserId());

            int likeCount = likeService.countByPostId(contents.getId());
            boolean isLike = likeService.isLikeByContentsIdAndUserId(contents.getId(), userId);

            List<CommentDetail> commentList = commentService.getCommentsByContentsId(contents.getId());

            HomeDetail homeDetail = HomeDetail.builder()
                    .id(contents.getId()).contents(contents.getTextContents())
                    .imagePath(contents.getImageContents()).userId(contents.getUserId()).loginId(user.getLoginId())
                    .likeCount(likeCount).isLike(isLike).comments(commentList).build();


            detailList.add(homeDetail);
        }

        return detailList;
    }

    public Contents getContents(long id) {
        return homeRepository.findById(id)
                .orElse(null);
    }



    public boolean deleteContents(long id, long userId) {
         Optional<Contents> optionalContents = homeRepository.findById(id);
         if (optionalContents.isPresent()) {
             try {
                 Contents contents = optionalContents.get();

                 if (contents.getUserId() != userId) {
                     return false;
                 } else {
                     likeService.deleteLikeByContentsId(contents.getId());
                     homeRepository.delete(optionalContents.get());
                     FileManager.removeFile(contents.getImageContents());
                 }

             } catch (DataAccessException e) {
                 return false;
             }
         } else {
             return false;
         }
// 추가적으로 모달창 만들어보기
        // 모달은 하나인데 컨텐츠 아이디는 여러개임. 이때 모달을 눌렀을 때 특정 컨텐츠 아이디를 가진 모달로 삭제 기능을 수행하는 방법에 대해 고민해야 합니다
         return true;
    }


}
