package com.dee.group.service.vo;

import com.dee.group.service.entity.MyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {


    private int id;
    private String name;
    private String mobile;
    private String email;


}
