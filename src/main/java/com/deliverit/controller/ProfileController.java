package com.deliverit.controller;

import com.deliverit.service.interfaces.ProfileService;
import com.deliverit.utility.request.ProfileRequest;
import com.deliverit.utility.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping(value = "/profile")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest request) {
        log.info("ProfileController:: createProfile: request: " + request.toString());
        return profileService.createProfile(request);
    }

    @GetMapping(value = "/profile")
    public ProfileResponse getProfile() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("ProfileController:: getProfile: userName: {}", userName);
        return profileService.getProfile(userName);
    }
}

