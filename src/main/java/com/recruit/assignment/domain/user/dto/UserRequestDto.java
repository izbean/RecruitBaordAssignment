package com.recruit.assignment.domain.user.dto;


import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {

    private static final long serialVersionUID = -1150644725759026071L;

    @NotEmpty
    private String userId;

    private String password;

    private String email;

}
