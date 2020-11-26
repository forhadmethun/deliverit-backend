package com.deliverit.service.interfaces;

import com.deliverit.utility.dto.UserDto;
import com.deliverit.utility.request.UserRequest;

public interface UserService {
    UserDto save(UserRequest userRequest);
    UserDto find(String email);
}
