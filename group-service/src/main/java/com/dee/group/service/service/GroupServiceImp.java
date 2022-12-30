package com.dee.group.service.service;

import com.dee.group.service.entity.Group_Member;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.GroupAlreadyExistException;
import com.dee.group.service.exception.GroupNotFoundException;
import com.dee.group.service.exception.MemberAlreadyInGroupException;
import com.dee.group.service.repository.GroupMemberRepository;
import com.dee.group.service.repository.GroupRepository;
import com.dee.group.service.vo.Member;
import com.dee.group.service.vo.ResponseVoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Override
    public MyGroup createGroup(MyGroup myGroup) throws GroupAlreadyExistException {
        MyGroup tempGroup = groupRepository.findByGroupName(myGroup.getGroupName());
        if (tempGroup == null) {
            return groupRepository.save(myGroup);
        } else {
            throw new GroupAlreadyExistException("This group is already registerd try with some different name");
        }

    }

    @Override
    public void addMember(Group_Member gm, int groupId) throws MemberAlreadyInGroupException {
        gm.setGroupId(groupId);
        Group_Member memberPresentInGroup = groupMemberRepository.findByMemberIdAndGroupId(gm.getMemberId(), groupId);
        if (memberPresentInGroup == null) {
            groupMemberRepository.save(gm);
        } else {
            throw new MemberAlreadyInGroupException("The member with id : " + gm.getMemberId() + " already present in group");
        }
    }

    @Override
    public List<Member> getMemberList() {
        return null;
    }

    @Override
    public MyGroup updateGroupDetails() {
        return null;
    }

    @Override
    public ResponseVoTemplate getGroup(int groupId) throws GroupNotFoundException {


        ResponseVoTemplate responseVoTemplate = new ResponseVoTemplate();

        boolean groupPresent = groupRepository.findById(groupId).isPresent();

        if (groupPresent == true) {
            MyGroup group = groupRepository.findById(groupId).get();
            List<Group_Member> list = groupMemberRepository.findByGroupId(groupId);
            List<Member> member = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                int memId = list.get(i).getMemberId();
                Member tempMem = restTemplate.getForObject("http://localhost:8080/" + memId, Member.class);
                member.add(tempMem);
            }
            responseVoTemplate.setMember(member);
            responseVoTemplate.setMyGroup(group);
            return responseVoTemplate;
        } else {
            throw new GroupNotFoundException("Group not found with the id: " + groupId);
        }


    }

    public List<MyGroup> findGroupsByMemberId(int memId) throws GroupNotFoundException {

        List<Group_Member> group = groupMemberRepository.findByMemberId(memId);
        List<MyGroup> groupList = new ArrayList<>();
        for (int i = 0; i < group.size(); i++) {
            if (groupRepository.findById(group.get(i).getGroupId()).isPresent()) {
                groupList.add(groupRepository.findById(group.get(i).getGroupId()).get());
            } else {
                throw new GroupNotFoundException("The group associated with the member do not exist");
            }

        }

        return groupList;

    }
}
