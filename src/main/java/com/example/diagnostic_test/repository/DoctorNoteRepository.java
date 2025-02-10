package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.entity.doctor.DoctorNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorNoteRepository extends JpaRepository<DoctorNote, Long> {
}