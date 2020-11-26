package com.deliverit.utility.dto;

import com.deliverit.entity.user.User;
import com.deliverit.utility.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private Boolean isValid;
    private String createDateTime;
    private String lastUpDatedDateTime;

    public static UserDto of(User user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserDto.class);
    }
    public static UserDto of(UserRequest user, ModelMapper modelMapper) {
        return modelMapper.map(user, UserDto.class);
    }
}
