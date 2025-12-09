package com.eunbi.samokgram.home.service;

import com.eunbi.samokgram.home.repository.HomeRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

}
