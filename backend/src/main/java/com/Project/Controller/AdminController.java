package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) { this.adminService = adminService; }

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room r) { return ResponseEntity.ok(adminService.createRoom(r)); }

    @PostMapping("/equipment")
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment e) { return ResponseEntity.ok(adminService.addEquipment(e)); }

    @PostMapping("/equipment/{id}/tickets")
    public ResponseEntity<MaintenanceTicket> createTicket(@PathVariable Long id, @RequestBody MaintenanceTicket t) {
        // link equipment
        // simplified: assume equipment exists and is set in body
        return ResponseEntity.ok(adminService.createTicket(t));
    }

    @PostMapping("/classes")
    public ResponseEntity<GroupClassSession> createClass(@RequestBody GroupClassSession c) {
        return ResponseEntity.ok(adminService.createClass(c));
    }
}
