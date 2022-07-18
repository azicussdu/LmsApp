package kz.alm.LmsApp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleToUserDTO {
    @NotBlank(message = "username can not be empty")
    private String username;
    @NotBlank(message = "rolename can not be empty")
    private String rolename;
}
