package com.telusko.HospitalManagementSystem.controllers;

import com.telusko.HospitalManagementSystem.model.Appointment;
import com.telusko.HospitalManagementSystem.model.Doctor;
import com.telusko.HospitalManagementSystem.service.AppointmentService;
import com.telusko.HospitalManagementSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController
{
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue = "0")int page , @RequestParam(defaultValue = "3")int size)
    {
        System.out.println("Fetching all doctors ");
        return doctorService.getAllDoctors(page,size);

    }

    @PostMapping()
    public Doctor createDoctor(@RequestBody Doctor doctor)
    {
        System.out.println("Creating doctor");

        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id)
    {
        System.out.println("Fetching Doctor");

        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id)
    {
        System.out.println("Deleted Doctor");
        doctorService.deleteDoctor(id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id , @RequestBody Doctor doctor)
    {
        System.out.println("Updated Doctor");

        return doctorService.updateDoctor(id , doctor);
    }


    //Get all Appointments of a doctor by Date

    @GetMapping("/{doctorId}/{date}")
    public List<Appointment>getDoctorAppointments(@PathVariable Long doctorId,
                                                  @PathVariable LocalDate date)
    {
        return appointmentService.getDoctorAppointments(doctorId , date);
    }
}
