package com.telusko.HospitalManagementSystem.controllers;

import com.telusko.HospitalManagementSystem.model.Appointment;
import com.telusko.HospitalManagementSystem.model.Patient;
import com.telusko.HospitalManagementSystem.service.AppointmentService;
import com.telusko.HospitalManagementSystem.service.WebHookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointMentController
{
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private WebHookService webHookService;

    @GetMapping()
    public Page<Appointment> getAllAppointments( @RequestParam(defaultValue = "0")int page , @RequestParam(defaultValue = "2")int size )
    {
        System.out.println("Fetching the appointments");
        return appointmentService.getAllAppointments(page,size);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id)
    {
        System.out.println("Fetching Appointment by ID" + id);
        return appointmentService.getAppointmentById(id);

    }

    @PostMapping()
    public Appointment createAppointment(@RequestBody Appointment appointment)
    {
        System.out.println("Creating patient");

       Appointment appointment1 = appointmentService.createAppointment(appointment);

       //object database madhe save zalyvar apan  tyala khali vaprat ahe
//        //Prepare the WebHook Payload
//        Map<String , Object> payload = new HashMap<>();
//        payload.put("appointmentId" , appointment1.getId());
//        payload.put("patientId" , appointment1.getPatientId());
//        payload.put("doctorId" , appointment1.getDoctorId());
//        payload.put("appointmentDate" , appointment1.getDate());
//
//        //Send The WebHook
//        String webHookUrl = "http://localhost:8081/webHook"; //Replace with webhook actual
//        webHookService.sendWebHook(webHookUrl , payload);

        return appointment1;
    }


    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id)
    {
        appointmentService.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id , @RequestBody Appointment appointment)
    {
        return appointmentService.updateAppointment(id , appointment);
    }
}
