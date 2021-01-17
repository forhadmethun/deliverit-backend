package com.deliverit.dto;

import com.deliverit.entity.customer.Customer;
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
public class CustomerDto implements Serializable {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNo;

    public static CustomerDto of(Customer user, ModelMapper modelMapper) {
        return modelMapper.map(user, CustomerDto.class);
    }

}
