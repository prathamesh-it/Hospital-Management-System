package com.telusko.HospitalManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="doctors")
public class Doctor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String name;
    private String speciality;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;

    @Column(name = "consultation_fee")
    private double consultationFee;
}
