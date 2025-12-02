package com.Project.Controller;

import com.Project.Entity.*;
import com.Project.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @PostMapping("/register")
    public ResponseEntity<Member> register(@RequestBody Member member) {
        Member saved = memberService.registerMember(member);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable Long id) {
        return memberService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/metrics")
    public ResponseEntity<HealthMetric> addMetric(@PathVariable Long id, @RequestBody HealthMetric metric) {
        HealthMetric saved = memberService.addMetric(id, metric);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/goals")
    public ResponseEntity<FitnessGoal> addGoal(@PathVariable Long id, @RequestBody FitnessGoal goal) {
        FitnessGoal saved = memberService.addGoal(id, goal);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/classes/{classId}/register")
    public ResponseEntity<ClassRegistration> registerToClass(@PathVariable Long id, @PathVariable Long classId) {
        ClassRegistration reg = memberService.registerToClass(id, classId);
        return ResponseEntity.ok(reg);
    }

    @PostMapping("/{id}/pt-sessions")
    public ResponseEntity<PersonalTrainingSession> schedulePT(@PathVariable Long id, @RequestBody PersonalTrainingSession session) {
        PersonalTrainingSession saved = memberService.schedulePT(id, session);
        return ResponseEntity.ok(saved);
    }
}
