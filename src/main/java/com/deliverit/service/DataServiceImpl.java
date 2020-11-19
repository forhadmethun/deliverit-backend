package com.deliverit.service;

import com.deliverit.entity.Admin;
import com.deliverit.repository.AdminRepository;
import com.deliverit.service.interfaces.DataService;
import com.deliverit.utility.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final AdminRepository adminRepository;
    final ModelMapper modelMapper;

    @Override
    public AdminDto saveAdminInDB(AdminDto adminDto) {
        return AdminDto.of(adminRepository.save(Admin.of(adminDto, modelMapper)), modelMapper);
    }

    @Override
    public AdminDto findByUserName(String userName) {
        return AdminDto.of(adminRepository.findByEmail(userName), modelMapper);
    }
}
