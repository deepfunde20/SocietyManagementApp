package com.dee.group.service.controller;

import com.dee.group.service.entity.Group_Member;
import com.dee.group.service.entity.MyGroup;
import com.dee.group.service.exception.GroupAlreadyExistException;
import com.dee.group.service.exception.GroupNotFoundException;
import com.dee.group.service.exception.MemberAlreadyInGroupException;
import com.dee.group.service.service.GroupServiceImp;
import com.dee.group.service.vo.Member;
import com.dee.group.service.vo.ResponseVoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupServiceImp groupServiceImp;

    @PostMapping("/createGroup")
    public MyGroup createGroup(@RequestBody MyGroup myGroup) throws GroupAlreadyExistException {

   return groupServiceImp.createGroup(myGroup);
    }

    @PostMapping("/{id}/addMember")
    public void addMember(@PathVariable("id") int groupId , @RequestBody Group_Member gm ) throws MemberAlreadyInGroupException {
        groupServiceImp.addMember(gm,groupId);
    }

    @GetMapping("/{id}")
    public ResponseVoTemplate getGroup(@PathVariable ("id") int groupId) throws GroupNotFoundException {
        return groupServiceImp.getGroup(groupId);
    }

    @GetMapping("/member/{id}")
    public List<MyGroup> getAllGroups(@PathVariable ("id") int memId) throws GroupNotFoundException {
      return groupServiceImp.findGroupsByMemberId(memId);
    }

}
