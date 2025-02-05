package com.example.diagnostic_test.repository.security;

import com.example.diagnostic_test.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    boolean existsByRoleNameIgnoreCase(String roleName);
}
