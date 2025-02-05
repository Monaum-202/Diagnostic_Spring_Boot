package com.example.diagnostic_test.repository;


import com.example.diagnostic_test.entity.DiagonesticTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagonesticTestRepository extends JpaRepository<DiagonesticTest,Long> {
}
