package com.telusko.HospitalManagementSystem.service;

import com.telusko.HospitalManagementSystem.model.Patient;
import com.telusko.HospitalManagementSystem.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService
{
    //To print the log messages
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page , int size)
    {
        try
        {
//            return patientRepository.findAll();
            Pageable pageable = PageRequest.of(page,size);
            return patientRepository.findAll(pageable);

        }
        catch(Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            logger.error("An error occured while fetching all patients:{}",e.getMessage());

            throw new RuntimeException("Patient not found");
        }

    }
    public Patient getPatientById(Long id)
    {
        try
        {
            Optional<Patient> patient = patientRepository.findById(id);
            return patient.orElse(null);
        } catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            logger.error("An error occured while fetching patient with Id:{} : {}", id ,e.getMessage());
            throw new RuntimeException("Patient not found");
        }

    }

    public Patient createPatient(Patient patient)
    {
       try
       {
           return patientRepository.save(patient);

       } catch (Exception e)
       {
           logger.error("An error occured while creating patient:{}",e.getMessage());
           throw new RuntimeException("Cannot create patient");
       }

    }


    public void deletePatient(Long id)
    {
        try
        {
            if(patientRepository.existsById(id))
            {
                patientRepository.deleteById(id);
                logger.info("Patient deleted successfully with id: {}", id);
            }
            else
            {
                logger.warn("Patient with id {} not found", id);
            }
        }
        catch (Exception e)
        {
            logger.error("An error occured while deleting patient:{}",e.getMessage());
            throw e;
        }

    }


    public Patient updatePatient(Long id , Patient updatedPatient)
    {
        try
        {
//            Object :
//            id = 2
//            name = Rahul
//            gender = Male
//            age = 25
//            findById(1) → Optional[Patient]
//
//            get() → Patient object

            Optional<Patient> existingPatient = patientRepository.findById(id);

            if(existingPatient.isPresent())
            {
                //Optional मधून actual object घेतो
                Patient p = existingPatient.get();
                p.setName(updatedPatient.getName());
                p.setGender(updatedPatient.getGender());
                p.setAge(updatedPatient.getAge());

                return patientRepository.save(p);

            }
            else
            {
                logger.error("Patient with the ID {} not found" , id);
                throw new RuntimeException("Patient not found");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            throw e;
        }

    }
}
