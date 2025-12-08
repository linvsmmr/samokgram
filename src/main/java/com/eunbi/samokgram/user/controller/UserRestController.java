package com.eunbi.samokgram.user.controller;

import com.eunbi.samokgram.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join-process")
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

    @GetMapping("/duplicate-id")
    public Map<String,Boolean> isDuplicateId(@RequestParam String loginId) {
        Map<String, Boolean> resultMap = new HashMap<>();

        if (userService.isDuplicateId(loginId)) {
            resultMap.put("isDuplicate", true);
        } else {
            resultMap.put("isDuplicate", false);
        }
        return resultMap;
    }



}
