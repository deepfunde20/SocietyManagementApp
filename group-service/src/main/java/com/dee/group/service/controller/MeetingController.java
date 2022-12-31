package com.dee.group.service.controller;

import com.dee.group.service.dto.MeetingDto;
import com.dee.group.service.entity.Meeting;
import com.dee.group.service.exception.MeetingNotFoundException;
import com.dee.group.service.service.MeetingServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingServiceImp meetingServiceImp;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public Meeting createMeeting(@RequestBody MeetingDto meetingDto) {


        return meetingServiceImp.createMeeting(meetingDto);
    }

    @GetMapping("/group/{id}")
    public List<MeetingDto> getAllMeetings(@PathVariable("id") int groupId) {
        return meetingServiceImp.meetingList(groupId);
    }

    @GetMapping("/delete/{id}")
    public void deleteMeeting(@PathVariable("id") int meetingId) throws MeetingNotFoundException {
         meetingServiceImp.removeMeeting(meetingId);

    }
}
