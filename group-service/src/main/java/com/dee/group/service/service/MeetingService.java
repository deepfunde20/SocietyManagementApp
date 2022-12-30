package com.dee.group.service.service;

import com.dee.group.service.entity.Meeting;
import com.dee.group.service.exception.MeetingNotFoundException;

import java.util.List;

public interface MeetingService {

    Meeting createMeeting (Meeting meeting);

    Meeting updateMeeting (Meeting meeting);

    List<Meeting> meetingList(int groupId);

    void removeMeeting(int meetingId) throws MeetingNotFoundException;
}
