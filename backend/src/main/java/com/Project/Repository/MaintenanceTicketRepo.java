package com.Project.Repository;

import com.Project.Entity.MaintenanceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTicketRepo extends JpaRepository<MaintenanceTicket, Long> {
}
