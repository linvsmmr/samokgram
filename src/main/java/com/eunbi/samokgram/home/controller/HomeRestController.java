package com.eunbi.samokgram.home.controller;

import com.eunbi.samokgram.home.repository.HomeRepository;
import com.eunbi.samokgram.home.service.HomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/home")
@RestController
public class HomeRestController {
    private final HomeService homeService;

    public HomeRestController(HomeService homeService) {
        this.homeService = homeService;
    }

    @PostMapping("/upload-process")
    public Map<String, String> upload(@RequestParam MultipartFile imageFile,
                                      @RequestParam(required = false) String contents,
                                      HttpSession session) {

        long userId = (long) session.getAttribute("userId");


        Map<String, String> resultMap = new HashMap<>();

        if (homeService.createContents(userId,imageFile,contents)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }


//    @DeleteMapping("/delete")
//    public Map<String, String> delete(@RequestParam int id,
//                                      HttpSession session) {
//
//        int contentsId = (int) session.getAttribute("contentsId");
//
//
//        Map<String, String> resultMap = new HashMap<>();
//
//        if (homeService.deleteContents(contentsId)) {
//            resultMap.put("result", "success");
//        } else {
//            resultMap.put("result", "fail");
//        }
//
//        return resultMap;
//    }


}
