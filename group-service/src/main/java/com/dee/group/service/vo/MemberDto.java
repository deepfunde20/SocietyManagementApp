package com.dee.group.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    private int id;
    private String name;
    private String mobile;
    private String email;


}
