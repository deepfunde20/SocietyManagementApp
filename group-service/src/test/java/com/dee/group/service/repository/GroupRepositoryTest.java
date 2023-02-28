package com.dee.group.service.repository;

import com.dee.group.service.entity.MyGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupRepositoryTest {

    @Autowired
    private GroupRepository underTest;

    @Test
    void itShouldFindByGroupName() {
        //given
        String groupName = "dummy";

        MyGroup dummy = new MyGroup();
        dummy.setGroupName(groupName);
        dummy.setImage("image");
        underTest.save(dummy);

        //when
      MyGroup temp=  underTest.findByGroupName(groupName);

        //then
        assertThat(temp.getGroupName()).isEqualTo(groupName);
    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    }