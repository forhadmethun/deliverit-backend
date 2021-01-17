package com.deliverit.service.interfaces;

import com.deliverit.dto.UserDto;
import com.deliverit.dto.io.request.UserRequest;

public interface UserService {
    UserDto save(UserRequest userRequest);
    UserDto find(String email);
}
