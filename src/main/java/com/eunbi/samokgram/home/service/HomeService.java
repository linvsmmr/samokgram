package com.eunbi.samokgram.home.service;

import com.eunbi.samokgram.common.FileManager;
import com.eunbi.samokgram.home.domain.Contents;
import com.eunbi.samokgram.home.dto.HomeDetail;
import com.eunbi.samokgram.home.repository.HomeRepository;
import com.eunbi.samokgram.user.domain.User;
import com.eunbi.samokgram.user.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final UserService userService;

    public HomeService(HomeRepository homeRepository, UserService userService) {
        this.homeRepository = homeRepository;
        this.userService = userService;
    }

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

    public List<HomeDetail> getContentsList() {
        List<Contents> contentsList = homeRepository.findAll(Sort.by("id").descending());

        List<HomeDetail> detailList = new ArrayList<>();

        for (Contents contents:contentsList) {

            User user = userService.getUserById(contents.getUserId());

            HomeDetail homeDetail = HomeDetail.builder()
                    .id(contents.getId()).contents(contents.getTextContents())
                    .imagePath(contents.getImageContents()).userId(contents.getUserId()).loginId(user.getLoginId()).build();


            detailList.add(homeDetail);
        }

        return detailList;
    }

    public Contents getContents(long id) {
        return homeRepository.findById(id)
                .orElse(null);
    }

    public int getLikes(int id) {
        return homeRepository.countLikes(id);
    }



//    public boolean deleteContents(int id) {
//        return homeRepository.deleteContents(id);
//    }




}
