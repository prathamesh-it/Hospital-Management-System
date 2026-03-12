package com.telusko.HospitalManagementSystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        name="appointments" ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctorId","date","time"})
        }
        )
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;

}
