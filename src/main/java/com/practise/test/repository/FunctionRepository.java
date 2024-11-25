package com.practise.test.repository;

import com.practise.test.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, String> {
    @Query("SELECT f.id, f.name, f.functionLink FROM Function f " +
            "JOIN f.permissions p " +
            "WHERE p.groupRoleId = :roleId AND p.isDeleted = false AND p.isActive = true " +
            "AND f.isActive = true AND f.isDeleted = false")
    List<Object[]> getCurrentUserPermissions(@Param("roleId") String roleId);
}
