package com.telusko.HospitalManagementSystem.repository;

import com.telusko.HospitalManagementSystem.model.Appointment;
import com.telusko.HospitalManagementSystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>
{
    //    SpringBoot generate SQL query for this
//    SELECT COUNT(*)
//    FROM appointments
//    WHERE doctor_id = ?
//    AND date = ?
//    AND time = ?
    boolean existsByDoctorIdAndDateAndTime(Long doctorId, LocalDate date, LocalTime time);

    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);

}
