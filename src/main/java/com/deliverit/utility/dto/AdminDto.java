package com.deliverit.utility.dto;

import com.deliverit.entity.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AdminDto implements Serializable {
    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private Boolean isValid;
    private String createDateTime;
    private String lastUpDatedDateTime;

    public static AdminDto of(Admin admin, ModelMapper modelMapper) {
        return modelMapper.map(admin, AdminDto.class);
    }
}
