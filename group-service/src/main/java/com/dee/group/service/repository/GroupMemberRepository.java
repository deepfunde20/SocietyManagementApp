package com.dee.group.service.repository;

import com.dee.group.service.entity.Group_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<Group_Member, Integer> {

  List<Group_Member> findByGroupId(int id);

  List<Group_Member> findByMemberId(int memId);

  Group_Member findByMemberIdAndGroupId(int memberId, int groupId);

  List<Group_Member> findByUserId(int userId);
}
