package com.telusko.HospitalManagementSystem.service;

import com.telusko.HospitalManagementSystem.model.Doctor;
import com.telusko.HospitalManagementSystem.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService
{
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    public Page<Doctor> getAllDoctors(int page , int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page, size);
            return doctorRepository.findAll(pageable);

        } catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            logger.error("An error occured while fetching all doctors:{}",e.getMessage());

            throw new RuntimeException("Doctors not found");
        }
    }

    public Doctor getDoctorById(Long id)
    {
       try
       {
            Optional<Doctor> doctor = doctorRepository.findById(id);
            return doctor.orElse(null);
       }
       catch (Exception e)
       {
           System.out.println("Error message"+e.getMessage());
           logger.error("An error occured while fetching doctor with Id:{} : {}", id ,e.getMessage());

           throw new RuntimeException("Doctor not found");
       }
    }

    public Doctor createDoctor(Doctor doctor)
    {
        try
        {
            return doctorRepository.save(doctor);
        }
        catch (Exception e)
        {
            logger.error("An error occured while creating doctor:{}",e.getMessage());
            throw e;

        }
    }

    public void deleteDoctor(Long id)
    {
       try
       {
           if(doctorRepository.existsById(id))
           {
               doctorRepository.deleteById(id);
               logger.info("Doctor deleted successfully with id: {}", id);
           }
           else
           {
               logger.warn("Doctor with id {} not found", id);
           }
       }
       catch (Exception e)
       {
           logger.error("An error occured while deleting doctor:{}",e.getMessage());
           System.out.println("Error message"+e.getMessage());
       }
    }

    public Doctor updateDoctor(Long id , Doctor updatedDoctor)
    {
          try
          {
              Optional<Doctor> existingDoctor = doctorRepository.findById(id);

              if(existingDoctor.isPresent())
              {
                  Doctor d = existingDoctor.get();

                  d.setName(updatedDoctor.getName());
                  d.setSpeciality(updatedDoctor.getSpeciality());

                  doctorRepository.save(d);

                  return d;

              }
              else
              {
                  logger.error("Doctor with the ID {} not found" , id);
                  throw new RuntimeException("Doctors not found");
              }

          }
          catch (Exception e)
          {
              logger.error("An error occured while updating doctor:{}",e.getMessage());
              throw new RuntimeException("Doctors not found");
          }
    }

}
