package com.dee.group.service.dto;

import lombok.Data;

@Data
public class MeetingDto {
    private int id;

    private int groupId;

    private String meetingName;


    private String time;
}
