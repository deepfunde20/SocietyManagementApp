package com.dee.group.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group_Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int groupId;
    private int memberId;
    private int userId;

}
