package com.Project.Repository;

import com.Project.Entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmailIgnoreCase(String email);
}