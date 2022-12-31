package com.dee.group.service.service;

import com.dee.group.service.dto.GroupMemberDto;
import com.dee.group.service.entity.Group_Member;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.GroupAlreadyExistException;
import com.dee.group.service.exception.GroupNotFoundException;
import com.dee.group.service.exception.MemberAlreadyInGroupException;
import com.dee.group.service.vo.Member;
import com.dee.group.service.vo.ResponseVoTemplate;

import java.util.List;

public interface GroupService {

    MyGroup createGroup(MyGroup myGroup) throws GroupAlreadyExistException;
    void addMember(GroupMemberDto gm, int groupId) throws MemberAlreadyInGroupException;
    List<Member> getMemberList();
    MyGroup updateGroupDetails();

    ResponseVoTemplate getGroup(int id) throws GroupNotFoundException;

}
