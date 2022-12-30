package com.dee.memberservice.service;

import com.dee.memberservice.entity.Member;

import com.dee.memberservice.newException.MemberAlreadyExistException;
import com.dee.memberservice.newException.UserNotFoundException;
import com.dee.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    MemberRepository repository;

    @Override
    public Member saveUser(Member member) throws MemberAlreadyExistException {
       Member tempMember = repository.findByEmail(member.getEmail());
       if(tempMember==null){
           repository.save(member);
           return member;
       }else{
           throw new MemberAlreadyExistException("User with this email already exist");
       }


    }

    @Override
    public Member getUser(int id) throws UserNotFoundException {
      boolean userPresent =  repository.findById(id).isPresent();

      if(userPresent==true){
          return repository.findById(id).get();
      }else{
          throw new UserNotFoundException("This member id do not exist");
      }
    }

}
