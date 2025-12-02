package com.Project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false, unique=true)
    private String email;

    private String phone;
    private String passwordHash;
    private LocalDate dateOfBirth;
    private String gender;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FitnessGoal> goals;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthMetric> metrics;

    @OneToMany(mappedBy = "member")
    private List<PersonalTrainingSession> personalSessions;

    @OneToMany(mappedBy = "member")
    private List<ClassRegistration> classRegistrations;

    @OneToMany(mappedBy = "member")
    private List<Invoice> invoices;

}
