package com.practise.test.repository;

import com.practise.test.entity.Organization;
import com.practise.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationRepository extends JpaRepository<Organization, String>, JpaSpecificationExecutor<Organization> {
    Organization findByName(String name);
}
