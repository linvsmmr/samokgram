package com.eunbi.samokgram.home.controller;

import com.eunbi.samokgram.home.repository.HomeRepository;
import com.eunbi.samokgram.home.service.HomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeRestController {
    private final HomeService homeService;

    public HomeRestController(HomeService homeService) {
        this.homeService = homeService;
    }


}
