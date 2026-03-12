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
@Table(name="patients")
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String name;
    private String gender;
    private int age;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;

}
