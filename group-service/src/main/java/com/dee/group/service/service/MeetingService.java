package com.dee.group.service.service;

import com.dee.group.service.dto.MeetingDto;
import com.dee.group.service.entity.Meeting;
import com.dee.group.service.exception.MeetingNotFoundException;

import java.util.List;

public interface MeetingService {

    Meeting createMeeting (MeetingDto meetingDto);

    Meeting updateMeeting (Meeting meeting);

    List<MeetingDto> meetingList(int groupId);

    void removeMeeting(int meetingId) throws MeetingNotFoundException;
}
