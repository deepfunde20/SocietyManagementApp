package com.dee.memberservice.service;

import com.dee.memberservice.dto.MemberDto;
import com.dee.memberservice.entity.Member;

import com.dee.memberservice.newException.MemberAlreadyExistException;
import com.dee.memberservice.newException.UserNotFoundException;
import com.dee.memberservice.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    MemberRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Member saveUser(MemberDto memberDto) throws MemberAlreadyExistException {
        Member member = modelMapper.map(memberDto, Member.class);
       Member tempMember = repository.findByEmail(member.getEmail());
       if(tempMember==null){
           repository.save(member);
           return member;
       }else{
           throw new MemberAlreadyExistException("User with this email already exist");
       }


    }

    @Override
    public MemberDto getUser(int id) throws UserNotFoundException {
      boolean userPresent =  repository.findById(id).isPresent();

      if(userPresent==true){
          Member member = repository.findById(id).get();
          return  modelMapper.map(member, MemberDto.class);
      }else{
          throw new UserNotFoundException("This member id do not exist");
      }
    }

    @Override
    public List<MemberDto> getAllMembersByUseId(int userId) {
     List<Member>  members =   repository.findByUserId(userId);

        List<MemberDto> dtos = members
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());

        return  dtos;
    }

}
