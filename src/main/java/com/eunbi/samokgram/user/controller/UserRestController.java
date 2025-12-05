package com.eunbi.samokgram.user.controller;

import com.eunbi.samokgram.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    public Map<String, String> join(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String loginId,
            @RequestParam String password
    ) {
        Map<String, String> resultMap = new HashMap<>();
        if (userService.createUser(firstName, lastName, email, loginId, password)) {
            resultMap.put("result", "success");
        } else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }
}
