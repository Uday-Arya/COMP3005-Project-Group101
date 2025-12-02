package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final RoomRepo roomRepo;
    private final EquipmentRepo equipmentRepo;
    private final MaintenanceTicketRepo ticketRepo;
    private final GroupClassSessionRepo classRepo;

    public AdminService(RoomRepo roomRepo,
                        EquipmentRepo equipmentRepo,
                        MaintenanceTicketRepo ticketRepo,
                        GroupClassSessionRepo classRepo) {
        this.roomRepo = roomRepo;
        this.equipmentRepo = equipmentRepo;
        this.ticketRepo = ticketRepo;
        this.classRepo = classRepo;
    }

    public Room createRoom(Room r) {
        return roomRepo.save(r);
    }

    public Equipment addEquipment(Equipment e) {
        return equipmentRepo.save(e);
    }

    public MaintenanceTicket createTicket(MaintenanceTicket t) {
        t.setCreatedAt(java.time.LocalDateTime.now());
        t.setStatus("OPEN");
        return ticketRepo.save(t);
    }

    public GroupClassSession createClass(GroupClassSession c) {
        return classRepo.save(c);
    }
}