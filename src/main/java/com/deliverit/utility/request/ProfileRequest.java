package com.deliverit.utility.request;

import com.deliverit.utility.exceptions.ServiceError;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProfileRequest {
    @NotBlank(message = ServiceError.INVALID_REQUEST)
    private String name;

    @NotBlank(message = ServiceError.INVALID_REQUEST)
    private String email;

    @NotBlank(message = ServiceError.INVALID_REQUEST)
    private String mobileNo;

    @NotBlank(message = ServiceError.INVALID_REQUEST)
    private String password;

    private String address;
}
