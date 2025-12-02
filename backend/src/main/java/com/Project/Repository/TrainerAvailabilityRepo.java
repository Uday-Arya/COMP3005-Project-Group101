package com.Project.Repository;

import com.Project.Entity.TrainerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainerAvailabilityRepo extends JpaRepository<TrainerAvailability, Long> {
    List<TrainerAvailability> findByTrainerId(Long trainerId);
}