package com.deliverit.service;

import com.deliverit.service.interfaces.DataService;
import com.deliverit.service.interfaces.ProfileService;
import com.deliverit.utility.Utility;
import com.deliverit.utility.dto.AdminDto;
import com.deliverit.utility.request.ProfileRequest;
import com.deliverit.utility.response.ProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final DataService dataService;
    private final PasswordEncoder bcryptEncoder;
    private final ModelMapper modelMapper;

    @Override
    public ProfileResponse createProfile(ProfileRequest request) {
        var adminDto = getAdminDto(request);
        adminDto = dataService.saveAdminInDB(adminDto);
        return ProfileResponse.of(adminDto, modelMapper);
    }

    @Override
    public ProfileResponse getProfile(String userName){
        var adminDto =  dataService.findByUserName(userName);
        return ProfileResponse.of(adminDto, modelMapper);
    }

    private AdminDto getAdminDto(ProfileRequest request) {
        return AdminDto.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobileNo(request.getMobileNo())
                .password(bcryptEncoder.encode(request.getPassword()))
                .createDateTime(Utility.formattedDateTime())
                .lastUpDatedDateTime(Utility.formattedDateTime())
                .isValid(true)
                .build();
    }

}
