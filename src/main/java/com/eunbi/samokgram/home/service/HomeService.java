package com.eunbi.samokgram.home.service;

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

import java.util.ArrayList;
import java.util.List;


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

            HomeDetail homeDetail = HomeDetail.builder()
                    .id(contents.getId()).contents(contents.getTextContents())
                    .imagePath(contents.getImageContents()).userId(contents.getUserId()).loginId(user.getLoginId())
                    .likeCount(likeCount).isLike(isLike).build();


            detailList.add(homeDetail);
        }

        return detailList;
    }

    public Contents getContents(long id) {
        return homeRepository.findById(id)
                .orElse(null);
    }



//    public boolean deleteContents(int id) {
//        return homeRepository.deleteContents(id);
//    }




}
