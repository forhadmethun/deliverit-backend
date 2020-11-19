package com.deliverit.utility.response;

import com.deliverit.utility.Utility;
import com.deliverit.utility.dto.AdminDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String name;
    private String email;
    private String mobileNo;

    public static ProfileResponse of(AdminDto admin, ModelMapper modelMapper) {
        return modelMapper.map(admin, ProfileResponse.class);
    }
}
