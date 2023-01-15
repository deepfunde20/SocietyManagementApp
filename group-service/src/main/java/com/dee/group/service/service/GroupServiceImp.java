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
import com.dee.group.service.vo.MemberDto;
import com.dee.group.service.vo.ResponseVoTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
    ModelMapper modelMapper;

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
    public void addMember(GroupMemberDto gmdto, int groupId) throws MemberAlreadyInGroupException {
        gmdto.setGroupId(groupId);
        Group_Member memberPresentInGroup = groupMemberRepository.findByMemberIdAndGroupId(gmdto.getMemberId(), groupId);
        if (memberPresentInGroup == null) {
            groupMemberRepository.save(modelMapper.map(gmdto, Group_Member.class));
        } else {
            throw new MemberAlreadyInGroupException("The member with id : " + gmdto.getMemberId() + " already present in group");
        }
    }

    @Override
    public List<MemberDto> getMemberList() {
        return null;
    }

    @Override
    public MyGroup updateGroupDetails() {
        return null;
    }

    @Override
    public ResponseVoTemplate getGroup(int groupId, String jwtToken) throws MemberServiceDownException, GroupNotFoundException {
        ResponseVoTemplate responseVoTemplate = new ResponseVoTemplate();
        boolean groupPresent = groupRepository.findById(groupId).isPresent();
        if (groupPresent == true) {
            System.out.println("You are in true state");
            MyGroup group = groupRepository.findById(groupId).get();
            List<Group_Member> list = groupMemberRepository.findByGroupId(groupId);
            List<MemberDto> member = new ArrayList();
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.add("Authorization", jwtToken);

                HttpEntity request = new HttpEntity(headers);
                for (int i = 0; i < list.size(); i++) {
                    int memId = list.get(i).getMemberId();
                    ResponseEntity<MemberDto> response = restTemplate.exchange(
                            "http://MEMBER-SERVICE/member/" + memId,
                            HttpMethod.GET,
                            request,
                            MemberDto.class,
                            1
                    );
                    member.add(response.getBody());
                }
            } catch (ResourceAccessException e) {
                e.printStackTrace();
                throw new MemberServiceDownException("Member service is down");
            } catch (IllegalStateException e) {
                e.printStackTrace();
                throw new MemberServiceDownException("Member service is down");
            }
            responseVoTemplate.setMember(member);
            responseVoTemplate.setMyGroup(group);
            return responseVoTemplate;
        } else {
            System.out.println("You are in false state");
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
