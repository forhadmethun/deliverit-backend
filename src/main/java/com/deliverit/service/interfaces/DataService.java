package com.deliverit.service.interfaces;

import com.deliverit.utility.dto.AdminDto;

public interface DataService {
    AdminDto saveAdminInDB(AdminDto adminDto);
    AdminDto findByUserName(String email);
}
