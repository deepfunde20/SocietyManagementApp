package com.dee.memberservice.service;

import com.dee.memberservice.entity.Member;
import com.dee.memberservice.newException.MemberAlreadyExistException;
import com.dee.memberservice.newException.UserNotFoundException;

import java.util.List;

public interface MemberService {

    Member saveUser(Member member) throws MemberAlreadyExistException;
    Member getUser(int id) throws UserNotFoundException;

}
