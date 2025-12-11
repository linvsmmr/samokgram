package com.eunbi.samokgram.home.service;

import com.eunbi.samokgram.common.FileManager;
import com.eunbi.samokgram.home.domain.Contents;
import com.eunbi.samokgram.home.repository.HomeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
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

    public List<Contents> getContentsList() {
        return homeRepository.findAll();
    }

    public Contents getContents(long id) {
        return homeRepository.findById(id)
                .orElse(null);
    }







}
