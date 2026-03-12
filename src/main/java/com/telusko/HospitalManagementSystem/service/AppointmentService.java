package com.telusko.HospitalManagementSystem.service;

import com.telusko.HospitalManagementSystem.model.Appointment;
import com.telusko.HospitalManagementSystem.model.Bill;
import com.telusko.HospitalManagementSystem.model.Doctor;
import com.telusko.HospitalManagementSystem.repository.AppointmentRepository;
import com.telusko.HospitalManagementSystem.repository.DoctorRepository;
import com.telusko.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService
{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BillService billService;

    public Page<Appointment> getAllAppointments(int page,int size)
    {
        try
        {
            Pageable pageable = PageRequest.of(page,size);
            return appointmentRepository.findAll(pageable);
        }
        catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            throw e;
        }

    }

    public Appointment getAppointmentById(Long id)
    {
        try
        {
            Optional<Appointment> getAppointment = appointmentRepository.findById(id);

            if(getAppointment.isPresent())
            {
                return getAppointment.get();
            }
            else
            {
                throw new RuntimeException("Appointment with id " + id + " not found");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            throw e;
        }
    }

    public Appointment createAppointment(Appointment appointment)
    {
        try
        {
            //If patient is not present in the patient table
            if(!patientRepository.existsById(appointment.getPatientId()))
            {
                throw new RuntimeException(" Patient not exist so appointment can't created");
            }

            //if doctor is not present in the doctor table
            if(!doctorRepository.existsById(appointment.getDoctorId()))
            {
                throw new RuntimeException(" Doctor not exist so appointment can't created");
            }

            //Check if doctor has an appointment at the same date and time
            if(appointmentRepository.existsByDoctorIdAndDateAndTime(
                    appointment.getDoctorId(),
                    appointment.getDate(),
                    appointment.getTime()
            ))
            {
                throw new RuntimeException("Doctor already has an appointment at this time");
            }

            Appointment savedAppointment = appointmentRepository.save(appointment);

            // -------------------------------
            // Automatic Bill Creation
            // -------------------------------

            Doctor doctor = doctorRepository.findById(appointment.getDoctorId()).get();

            Bill bill = new Bill();
            bill.setAppointmentId(savedAppointment.getId());
            bill.setAmount(doctor.getConsultationFee());
            bill.setStatus("PAID");
            billService.createBill(bill);

            return savedAppointment;

        }
        catch (Exception e)
        {
            System.out.println("Error message"+e.getMessage());
            throw e;
        }
    }

    public void deleteAppointment(Long id)
    {
        try
        {
            if(appointmentRepository.existsById(id))
            {
                appointmentRepository.deleteById(id);
            }
            else
            {
                throw new RuntimeException("Appointment does not exist");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Appointment updateAppointment(Long id , Appointment appointment)
    {

        try
        {
            Optional<Appointment> existingAppointment = appointmentRepository.findById(id);

            if(existingAppointment.isPresent())
            {
                Appointment newAppointment = existingAppointment.get();

                newAppointment.setDoctorId(appointment.getDoctorId());
                newAppointment.setPatientId(appointment.getPatientId());
                newAppointment.setDate(appointment.getDate());

                return appointmentRepository.save(newAppointment);
            }
            else
            {
                throw new RuntimeException("Appointment not found");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> getDoctorAppointments(Long doctorId, LocalDate date)
    {
        return appointmentRepository.findByDoctorIdAndDate(doctorId,date);
    }
}
