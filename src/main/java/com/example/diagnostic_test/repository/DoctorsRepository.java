package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.entity.Doctors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Long> {


    @Query(value = "select * from doctors where department_id = :dep_id ", nativeQuery = true)
    List<Doctors> findAllByDepartmentId(@Param("dep_id") Long departmentId);

//    List<Doctors> findByDepartment(Long department_id);

    // Search by name, gender, specialty, or schedule time (useful for filtering)
    Page<Doctors> findByNameContainingIgnoreCaseOrGenderContainingIgnoreCaseOrSpecialtyContainingIgnoreCaseOrScheduleTimeContainingIgnoreCase(
            String name, String gender, String specialty, String scheduleTime, Pageable pageable);

    // Optional: If you need a method to search doctors by department, you can add this
    Page<Doctors> findByDepartmentId(Long departmentId, Pageable pageable);
}
