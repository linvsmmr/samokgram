package com.eunbi.samokgram.home.controller;

import com.eunbi.samokgram.home.domain.Contents;
import com.eunbi.samokgram.home.dto.HomeDetail;
import com.eunbi.samokgram.home.service.HomeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/feed")
    public String feed(Model model) {

        List<HomeDetail> contentsList = homeService.getContentsList();

        model.addAttribute("contentsList", contentsList);

        return "home/feed";
    }


}
