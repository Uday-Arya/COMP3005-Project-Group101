package com.Project.Repository;

import com.Project.Entity.FitnessGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessGoalRepo extends JpaRepository<FitnessGoal, Long> {
}
