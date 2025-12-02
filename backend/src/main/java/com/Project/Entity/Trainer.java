package com.Project.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "trainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String specialization;
    private String passwordHash;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerAvailability> availabilities;

    @OneToMany(mappedBy = "trainer")
    private List<PersonalTrainingSession> personalSessions;

    @OneToMany(mappedBy = "trainer")
    private List<GroupClassSession> classSessions;
}