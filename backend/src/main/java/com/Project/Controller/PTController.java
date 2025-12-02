package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pt-sessions")
@CrossOrigin(origins = "*")
public class PTController {
    private final PTService ptService;

    public PTController(PTService ptService) { this.ptService = ptService; }

    @PostMapping
    public ResponseEntity<PersonalTrainingSession> create(@RequestBody PersonalTrainingSession s) {
        return ResponseEntity.ok(ptService.save(s));
    }
}