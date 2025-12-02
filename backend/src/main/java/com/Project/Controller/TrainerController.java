package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@CrossOrigin(origins = "*")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) { this.trainerService = trainerService; }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer t) { return ResponseEntity.ok(trainerService.createTrainer(t)); }

    @PostMapping("/{id}/availability")
    public ResponseEntity<List<TrainerAvailability>> addAvailability(@PathVariable Long id, @RequestBody TrainerAvailability a) {
        return ResponseEntity.ok(trainerService.addAvailability(id, a));
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<List<PersonalTrainingSession>> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getSchedule(id));
    }
}
