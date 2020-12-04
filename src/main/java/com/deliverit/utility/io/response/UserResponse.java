package com.deliverit.utility.io.response;

import com.deliverit.utility.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private String mobileNo;

    public static UserResponse of(UserDto userDto, ModelMapper modelMapper) {
        return modelMapper.map(userDto, UserResponse.class);
    }
}
