package com.practise.test.repository;

import com.practise.test.entity.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GroupRoleRepository")
public interface GroupRoleRepository extends JpaRepository<GroupRole, String> {
    @Query("SELECT g FROM GroupRole g WHERE g.isDeleted = false")
    List<GroupRole> findAllActiveGroupRoles();
}
