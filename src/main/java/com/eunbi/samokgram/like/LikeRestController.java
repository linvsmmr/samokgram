package com.eunbi.samokgram.like;

import com.eunbi.samokgram.like.service.LikeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/home")
@RestController
public class LikeRestController {

    private final LikeService likeService;

    public LikeRestController(LikeService likeService) {
        this.likeService = likeService;
    }



    @PostMapping("/like")
    public Map<String, String> like(@RequestParam long contentsId,
                                    HttpSession session,
                                    Model model) {

        long userId = (Long) session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();

        if (likeService.createLike(contentsId, userId)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }



    @DeleteMapping("/unlike")
    public Map<String, String> unlike(@RequestParam long contentsId,
                                      HttpSession session) {

        long userId = (long) session.getAttribute("userId");


        Map<String, String> resultMap = new HashMap<>();

        if (likeService.deleteLike(contentsId, userId)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }
        return resultMap;

    }




}
