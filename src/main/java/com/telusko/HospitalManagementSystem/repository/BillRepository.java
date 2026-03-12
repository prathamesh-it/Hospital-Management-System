package com.telusko.HospitalManagementSystem.repository;

import com.telusko.HospitalManagementSystem.model.Bill;
import com.telusko.HospitalManagementSystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long>
{
    boolean existsByAppointmentId(Long appointmentId);
}
