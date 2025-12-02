package com.Project.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "class_registrations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "group_class_session_id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_class_session_id", nullable = false)
    private GroupClassSession groupClassSession;

}
