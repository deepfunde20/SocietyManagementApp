package com.dee.group.service.mapper;

import com.dee.group.service.dto.GroupMemberDto;
import com.dee.group.service.dto.MeetingDto;
import com.dee.group.service.entity.Group_Member;
import com.dee.group.service.entity.Meeting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public MeetingDto meetingToDto(Meeting meeting){
        MeetingDto meetingDto = this.modelMapper.map(meeting, MeetingDto.class);

        return meetingDto;
    }

    public Meeting dtoToMeeting(MeetingDto meetingDto){
        Meeting meeting = this.modelMapper.map(meetingDto, Meeting.class);

        return meeting;
    }

    public Group_Member dtoToGroupMember(GroupMemberDto groupMemberDto){
        Group_Member group_member = this.modelMapper.map(groupMemberDto, Group_Member.class);
        return  group_member;
    }

    public GroupMemberDto groupMemberToDto(Group_Member group_member){
      GroupMemberDto groupMemberDto =  this.modelMapper.map(group_member,GroupMemberDto.class);
      return  groupMemberDto;
    }

}
