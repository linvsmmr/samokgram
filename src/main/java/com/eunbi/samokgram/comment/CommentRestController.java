package com.eunbi.samokgram.comment;


import com.eunbi.samokgram.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;


    @PostMapping("/home/comment/write")
    public Map<String, String> writeComment(@RequestParam long contentsId,
                                            @RequestParam String comment,
                                            HttpSession session) {
        long userId = (Long) session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();

        if(commentService.createComment(contentsId, userId, comment)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }


}
