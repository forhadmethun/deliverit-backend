package com.deliverit.entity.user;

import com.deliverit.entity.TableConstants;
import com.deliverit.utility.dto.UserDto;
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
public class User implements Serializable {

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
    private UserType userType;

    public static User of(UserDto userDto, ModelMapper modelMapper) {
        return modelMapper.map(userDto, User.class);
    }
}
