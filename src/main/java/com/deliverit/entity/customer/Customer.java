package com.deliverit.entity.customer;

import com.deliverit.entity.TableConstants;
import com.deliverit.entity.user.UserType;
import com.deliverit.utility.dto.CustomerDto;
import com.deliverit.utility.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.TABLE_CUSTOMER)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String name;

    @Column(unique=true)
    private String email;
    private String mobileNo;

    public static Customer of(CustomerDto customerDto, ModelMapper modelMapper) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
