package com.dee.memberservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MemberDto {

    private int id;

    @NotNull(message = "Name should not be null")
    private String name;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile umber added")
    private String mobile;

    @Email
    private String email;

    private String image;

    @NotNull(message = "User id should not be null")
    private int userId;
}
