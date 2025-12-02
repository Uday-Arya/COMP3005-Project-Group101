package com.Project.Repository;

import com.Project.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
}
