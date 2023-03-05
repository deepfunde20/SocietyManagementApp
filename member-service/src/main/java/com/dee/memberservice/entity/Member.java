package com.dee.memberservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
