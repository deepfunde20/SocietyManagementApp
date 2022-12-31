package com.dee.group.service.service;

import com.dee.group.service.dto.MeetingDto;
import com.dee.group.service.entity.Meeting;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.MeetingNotFoundException;
import com.dee.group.service.repository.MeetingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.TypeToken;


@Service
public class MeetingServiceImp implements MeetingService{


    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Meeting createMeeting(MeetingDto meetingDto) {

       Meeting tempMeet = modelMapper.map(meetingDto,Meeting.class);
        return meetingRepository.save(tempMeet);

    }

    @Override
    public Meeting updateMeeting( Meeting meeting) {
        return null;
    }

    @Override
    public List<MeetingDto> meetingList(int groupId) {
        List<Meeting> meetings =  meetingRepository.findAllByGroupId(groupId);
        Type listType = new TypeToken<List<MeetingDto>>(){}.getType();

       return modelMapper.map(meetings, listType);

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
