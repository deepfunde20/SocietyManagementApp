package com.dee.memberservice.controller;

import com.dee.memberservice.dto.MemberDto;
import com.dee.memberservice.entity.Member;
import com.dee.memberservice.newException.MemberAlreadyExistException;
import com.dee.memberservice.newException.UserNotFoundException;
import com.dee.memberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService service;

    @PostMapping("/signup")
    public Member saveUser(@RequestBody @Valid MemberDto user) throws MemberAlreadyExistException {
     return   service.saveUser(user);

    }

    @GetMapping("/{id}")
    public MemberDto getUser(@PathVariable("id") int id) throws UserNotFoundException {
        return   service.getUser(id);
    }

    @GetMapping("/allMember")
    public List<MemberDto> getAllUsers(){
        return  service.getAllUsers();
    }

}
