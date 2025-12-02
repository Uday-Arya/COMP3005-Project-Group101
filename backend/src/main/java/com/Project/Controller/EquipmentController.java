package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin(origins = "*")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) { this.equipmentService = equipmentService; }

    @PostMapping
    public ResponseEntity<Equipment> add(@RequestBody Equipment e) { return ResponseEntity.ok(equipmentService.addEquipment(e)); }

    @PostMapping("/{id}/tickets")
    public ResponseEntity<MaintenanceTicket> ticket(@PathVariable Long id, @RequestBody MaintenanceTicket t) {
        // set equipment id in ticket (for brevity assume user provided equipment object or will reconcile)
        return ResponseEntity.ok(equipmentService.createTicket(t));
    }
}