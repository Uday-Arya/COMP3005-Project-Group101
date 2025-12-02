package com.Project.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer capacity;

    @OneToMany(mappedBy = "room")
    private List<GroupClassSession> classSessions;

    @OneToMany(mappedBy = "room")
    private List<PersonalTrainingSession> personalSessions;

    @OneToMany(mappedBy = "room")
    private List<Equipment> equipment;

}
