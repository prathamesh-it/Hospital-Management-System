package com.telusko.HospitalManagementSystem.controllers;

import com.telusko.HospitalManagementSystem.model.Patient;
import com.telusko.HospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController
{
    @Autowired //try to inject the object of  attribute when object of controller is created
    private PatientService patientService;

    @GetMapping()
    public Page<Patient> getAllPatients(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "2") int size)
    {
        System.out.println("Fetching the patients");
        return patientService.getAllPatients(page,size);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id)
    {
        System.out.println("Fetching patient");

        return patientService.getPatientById(id);
    }

    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient)
    {
        System.out.println("Creating patient");
        return patientService.createPatient(patient);
    }


    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id)
    {
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id , @RequestBody Patient patient)
    {
        System.out.println("Updated Patient");
        return patientService.updatePatient(id,patient);

    }


}


