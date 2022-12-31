package com.dee.memberservice.service;

import com.dee.memberservice.dto.MemberDto;
import com.dee.memberservice.entity.Member;
import com.dee.memberservice.newException.MemberAlreadyExistException;
import com.dee.memberservice.newException.UserNotFoundException;

import java.util.List;

public interface MemberService {

    Member saveUser(MemberDto memberDto) throws MemberAlreadyExistException;
    MemberDto getUser(int id) throws UserNotFoundException;

}
