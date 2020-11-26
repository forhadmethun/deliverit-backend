package com.deliverit.controller;

import com.deliverit.service.interfaces.UserService;
import com.deliverit.utility.dto.UserDto;
import com.deliverit.utility.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.deliverit.utility.response.UserResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    final ModelMapper modelMapper;

    @PostMapping(value = "/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse createProfile(@Valid @RequestBody UserRequest request) {
        log.info("ProfileController:: createProfile: request: " + request.toString());
        return UserResponse.of(userService.save(request), modelMapper);
    }

    @GetMapping(value = "/user")
    public UserResponse getProfile() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("ProfileController:: getProfile: userName: {}", userName);
        return UserResponse.of(userService.find(userName), modelMapper);
    }
}