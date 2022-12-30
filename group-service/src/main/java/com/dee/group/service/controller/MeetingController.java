package com.dee.group.service.controller;

import com.dee.group.service.entity.Meeting;
import com.dee.group.service.exception.MeetingNotFoundException;
import com.dee.group.service.service.MeetingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingServiceImp meetingServiceImp;

    @PostMapping("/create")
    public Meeting createMeeting(@RequestBody Meeting meeting) {

        return meetingServiceImp.createMeeting(meeting);
    }

    @GetMapping("/group/{id}")
    public List<Meeting> getAllMeetings(@PathVariable("id") int groupId) {
        return meetingServiceImp.meetingList(groupId);
    }

    @GetMapping("/delete/{id}")
    public void deleteMeeting(@PathVariable("id") int meetingId) throws MeetingNotFoundException {
         meetingServiceImp.removeMeeting(meetingId);

    }
}
