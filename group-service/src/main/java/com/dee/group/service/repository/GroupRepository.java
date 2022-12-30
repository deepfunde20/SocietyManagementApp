package com.dee.group.service.repository;

import com.dee.group.service.entity.MyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<MyGroup, Integer> {
    MyGroup findByGroupName(String groupName);
}
