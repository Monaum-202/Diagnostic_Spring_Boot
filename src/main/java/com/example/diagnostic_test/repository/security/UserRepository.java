package com.example.diagnostic_test.repository.security;

import com.example.diagnostic_test.entity.security.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
    // Fetch all products with pagination and sorting
    Page<Users> findAll(Pageable pageable);


    Page<Users> findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String userName, String firstName, String lastName, String email, Pageable pageable);

}
