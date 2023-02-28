package com.dee.group.service.service;

import com.dee.group.service.dto.GroupMemberDto;
import com.dee.group.service.entity.Group_Member;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.GroupAlreadyExistException;
import com.dee.group.service.exception.GroupNotFoundException;
import com.dee.group.service.exception.MemberAlreadyInGroupException;
import com.dee.group.service.exception.MemberServiceDownException;
import com.dee.group.service.repository.GroupMemberRepository;
import com.dee.group.service.repository.GroupRepository;
import com.dee.group.service.repository.MeetingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GroupServiceImpTest {


    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupMemberRepository groupMemberRepository;

    private GroupServiceImp underTest;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        underTest = new GroupServiceImp(groupRepository, groupMemberRepository, modelMapper);
    }

    @Test
    void createGroup() throws GroupNotFoundException, MemberServiceDownException, GroupAlreadyExistException {
        //given
        MyGroup dummyGroup = new MyGroup();
        dummyGroup.setGroupName("ABC");
        dummyGroup.setImage("MyIMAGE");
        //when
        underTest.createGroup(dummyGroup);

        //then
        ArgumentCaptor<MyGroup> myGroupArgumentCaptor = ArgumentCaptor.forClass(MyGroup.class);
        verify(groupRepository).save(myGroupArgumentCaptor.capture());

        MyGroup capturedGroup = myGroupArgumentCaptor.getValue();
        assertThat(capturedGroup).isEqualTo(dummyGroup);
    }

    @Test
    void addMember() throws MemberAlreadyInGroupException {

        GroupMemberDto gm = new GroupMemberDto();
        gm.setGroupId(2);
        gm.setMemberId(1);

        underTest.addMember(gm, 2);
        Group_Member temp = modelMapper.map(gm, Group_Member.class);

        ArgumentCaptor<Group_Member> groupMemberDtoArgumentCaptor = ArgumentCaptor.forClass(Group_Member.class);
        verify(groupMemberRepository).save(groupMemberDtoArgumentCaptor.capture());

        Group_Member capturedgm = groupMemberDtoArgumentCaptor.getValue();
        assertThat(capturedgm).isEqualTo(temp);


    }

    @Test
    @Disabled
    void getMemberList() {
    }

    @Test
    @Disabled
    void updateGroupDetails() {
    }

    @Test
    @Disabled
    void getGroup() {

    }

    @Test
    @Disabled
    void findGroupsByMemberId() {
    }
}