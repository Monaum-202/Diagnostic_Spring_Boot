package com.example.diagnostic_test.repository;

import com.example.diagnostic_test.entity.DoctorAppointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointments,Long> {

//
//    @Query(value = "SELECT * FROM doctor_appointments WHERE doctor_id = :doc_id ", nativeQuery = true)
//    List<DoctorAppointments> findAllPatientByDoctorId(@Param("doc_id") Long doctorId);


//    @Query(value = "SELECT * FROM doctor_appointments da " +
//            "JOIN doctors d ON da.doctor_id = d.id " +
//            "WHERE d.user_name = :user_name", nativeQuery = true)
//    List<DoctorAppointments> findAllPatientByDoctorUserName(@Param("user_name") String username);

    @Query(value = " SELECT da.id ,da.address,da.appointment_date,da.contact_number,da.created_at,da.email,da.message,da.patient_name,da.updated_at,da.department_id,da.doctor_id FROM doctor_appointments da " +
            " JOIN doctors d ON da.doctor_id = d.id " +
            " WHERE d.user_name = :userName", nativeQuery = true)
    List<DoctorAppointments> findAllPatientByDoctorUserName(@Param("userName") String username);



    @Query("SELECT d FROM DoctorAppointments d " +
            "WHERE (:patientName IS NULL OR LOWER(d.patientName) LIKE LOWER(CONCAT('%', :patientName, '%'))) " +
            "AND (:contactNumber IS NULL OR d.contactNumber LIKE CONCAT('%', :contactNumber, '%')) " +
            "AND (:email IS NULL OR LOWER(d.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:appointmentDate IS NULL OR d.appointmentDate = :appointmentDate)")
    Page<DoctorAppointments> searchAppointments(
            @Param("patientName") String patientName,
            @Param("contactNumber") String contactNumber,
            @Param("email") String email,
            @Param("appointmentDate") LocalDate appointmentDate,
            Pageable pageable
    );
}
