package com.dee.group.service.repository;

import com.dee.group.service.entity.Meeting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MeetingRepositoryTest {

    @Autowired
    MeetingRepository underTest;

    @Test
    void findAllByGroupId() {

        //given
        Meeting meeting = new Meeting();

        meeting.setMeetingName("Standup");
        meeting.setTime("20:11 AM");
        meeting.setGroupId(1);
        meeting.setScheduledTime("09:11 AM");
         underTest.save(meeting);

         //when
       List<Meeting> list = underTest.findAllByGroupId(1);

       //then
       assertThat(list.get(0).getMeetingName()).isEqualTo("Standup");

    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
}