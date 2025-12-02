package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) { this.classService = classService; }

    @GetMapping("/available")
    public ResponseEntity<List<GroupClassSession>> available(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        if (from == null) from = LocalDateTime.now().minusYears(1);
        if (to == null) to = LocalDateTime.now().plusYears(1);
        return ResponseEntity.ok(classService.findBetween(from, to));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupClassSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(classService.findById(id));
    }
}
