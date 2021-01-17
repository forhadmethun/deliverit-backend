package com.deliverit.service;

import com.deliverit.entity.user.User;
import com.deliverit.repository.AdminRepository;
import com.deliverit.service.interfaces.UserService;
import com.deliverit.utility.Utility;
import com.deliverit.dto.UserDto;
import com.deliverit.dto.io.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AdminRepository adminRepository;
    final ModelMapper modelMapper;
    private final PasswordEncoder bcryptEncoder;

    @Override
    public UserDto save(UserRequest request) {
        return UserDto.of(adminRepository.save(getAdmin(request)), modelMapper);
    }

    @Override
    public UserDto find(String email) {
        return UserDto.of(adminRepository.findByEmail(email), modelMapper);
    }

    private User getAdmin(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobileNo(request.getMobileNo())
                .password(bcryptEncoder.encode(request.getPassword()))
                .createDateTime(Utility.formattedDateTime())
                .lastUpDatedDateTime(Utility.formattedDateTime())
                .userType(request.getUserType())
                .isValid(true)
                .build();
    }
}
