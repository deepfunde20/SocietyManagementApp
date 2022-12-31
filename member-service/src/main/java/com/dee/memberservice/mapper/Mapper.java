package com.dee.memberservice.mapper;

import com.dee.memberservice.dto.MemberDto;
import com.dee.memberservice.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {

    @Autowired
    ModelMapper modelMapper;

   MemberDto memberToDto(Member member){
      return modelMapper.map(member,MemberDto.class);
   }

   Member dtoToMember(MemberDto memberDto){

      return modelMapper.map(memberDto, Member.class);
   }
}
