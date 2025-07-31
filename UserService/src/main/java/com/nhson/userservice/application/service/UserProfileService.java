package com.nhson.userservice.application.service;

import com.nhson.userservice.application.dto.ProfileResponse;
import com.nhson.userservice.application.dto.RegisterReq;
import com.nhson.userservice.application.model.UserProfile;
import com.nhson.userservice.application.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public ProfileResponse createUserProfile(RegisterReq registerReq) {
        UserProfile userProfile = new UserProfile(registerReq);
        UserProfile save = userProfileRepository.save(userProfile);
        return new ProfileResponse(save.getId(), save.getFullName(), save.getEmail(), save.getTelegramId());
    }

}