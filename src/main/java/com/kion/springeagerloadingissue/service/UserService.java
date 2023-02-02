package com.kion.springeagerloadingissue.service;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.kion.springeagerloadingissue.model.User;
import com.kion.springeagerloadingissue.repository.UserRepository;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String userId) {
        return this.userRepository.findById(userId);
    }
}
