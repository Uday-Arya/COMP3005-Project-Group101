package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    private final EquipmentRepo equipmentRepo;
    private final MaintenanceTicketRepo ticketRepo;

    public EquipmentService(EquipmentRepo equipmentRepo,
                            MaintenanceTicketRepo ticketRepo) {
        this.equipmentRepo = equipmentRepo;
        this.ticketRepo = ticketRepo;
    }

    public Equipment addEquipment(Equipment e) {
        return equipmentRepo.save(e);
    }

    public MaintenanceTicket createTicket(MaintenanceTicket t) {
        t.setCreatedAt(java.time.LocalDateTime.now());
        t.setStatus("OPEN");
        return ticketRepo.save(t);
    }
}
