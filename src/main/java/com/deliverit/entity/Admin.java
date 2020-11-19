package com.deliverit.entity;

import com.deliverit.utility.dto.AdminDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.TABLE_ADMIN)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique=true)
    private String email;
    private String mobileNo;
    private String password;
    private String createDateTime;
    private String lastUpDatedDateTime;
    private Boolean isValid;

    public static Admin of(AdminDto adminDto, ModelMapper modelMapper) {
        return modelMapper.map(adminDto, Admin.class);
    }
}
