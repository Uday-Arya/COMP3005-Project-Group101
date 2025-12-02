package com.Project.Repository;

import com.Project.Entity.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthMetricRepo extends JpaRepository<HealthMetric, Long> {
}
