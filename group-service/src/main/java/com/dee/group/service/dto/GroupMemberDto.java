package com.dee.group.service.dto;


import lombok.Data;

@Data
public class GroupMemberDto {
    private int id;
    private int groupId;
    private int memberId;
    private int userId;
}
