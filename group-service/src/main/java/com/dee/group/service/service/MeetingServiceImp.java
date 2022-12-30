package com.dee.group.service.service;

import com.dee.group.service.entity.Meeting;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.MeetingNotFoundException;
import com.dee.group.service.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImp implements MeetingService{


    @Autowired
    MeetingRepository meetingRepository;
    @Override
    public Meeting createMeeting(Meeting meeting) {

        return meetingRepository.save(meeting);

    }

    @Override
    public Meeting updateMeeting( Meeting meeting) {
        return null;
    }

    @Override
    public List<Meeting> meetingList(int groupId) {
        return  meetingRepository.findAllByGroupId(groupId);
    }

    @Override
    public void removeMeeting(int meetingId) throws MeetingNotFoundException {
      boolean tempMeeting =  meetingRepository.findById(meetingId).isPresent();
      if(tempMeeting==true){
          meetingRepository.delete( meetingRepository.findById(meetingId).get());
      }else{
          throw new MeetingNotFoundException("Meeting is not available with id : "+meetingId);
      }

    }
}
