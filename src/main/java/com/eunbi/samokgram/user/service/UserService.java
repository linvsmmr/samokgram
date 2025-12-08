package com.eunbi.samokgram.user.service;

import com.eunbi.samokgram.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String firstName,
            String lastName,
            String email,
            String loginId,
            String password
    ) {
        int count = userRepository.insertUser(firstName, lastName, email, loginId, password);

        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.countByLoginId(loginId);

        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }



}
