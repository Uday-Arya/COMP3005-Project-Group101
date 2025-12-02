package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepo memberRepo;
    private final HealthMetricRepo metricRepo;
    private final FitnessGoalRepo goalRepo;
    private final ClassRegistrationRepo registrationRepo;
    private final GroupClassSessionRepo classRepo;
    private final PersonalTrainingSessionRepo ptRepo;

    public MemberService(MemberRepo memberRepo, HealthMetricRepo metricRepo,
                         FitnessGoalRepo goalRepo, ClassRegistrationRepo registrationRepo,
                         GroupClassSessionRepo classRepo, PersonalTrainingSessionRepo ptRepo) {
        this.memberRepo = memberRepo;
        this.metricRepo = metricRepo;
        this.goalRepo = goalRepo;
        this.registrationRepo = registrationRepo;
        this.classRepo = classRepo;
        this.ptRepo = ptRepo;
    }

    public Member registerMember(Member m) {
        if (memberRepo.findByEmailIgnoreCase(m.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        // In production hash password
        return memberRepo.save(m);
    }

    public Optional<Member> getById(Long id) {
        return memberRepo.findById(id);
    }

    public HealthMetric addMetric(Long memberId, HealthMetric metric) {
        Member m = memberRepo.findById(memberId).orElseThrow();
        metric.setMember(m);
        if (metric.getRecordedAt() == null) metric.setRecordedAt(LocalDate.now());
        return metricRepo.save(metric);
    }

    public FitnessGoal addGoal(Long memberId, FitnessGoal goal) {
        Member m = memberRepo.findById(memberId).orElseThrow();
        goal.setMember(m);
        return goalRepo.save(goal);
    }

    @Transactional
    public ClassRegistration registerToClass(Long memberId, Long classId) {
        GroupClassSession session = classRepo.findById(classId).orElseThrow();
        long regCount = registrationRepo.countByGroupClassSessionId(classId);
        if (session.getCapacity() != null && regCount >= session.getCapacity()) {
            throw new IllegalStateException("Class is full");
        }
        // optional: check time conflict for member
        ClassRegistration existing = registrationRepo.findByMemberIdAndGroupClassSessionId(memberId, classId).orElse(null);
        if (existing != null) throw new IllegalStateException("Already registered");

        ClassRegistration r = new ClassRegistration();
        Member m = memberRepo.findById(memberId).orElseThrow();
        r.setMember(m);
        r.setGroupClassSession(session);
        r.setRegisteredAt(LocalDateTime.now());
        return registrationRepo.save(r);
    }

    @Transactional
    public PersonalTrainingSession schedulePT(Long memberId, PersonalTrainingSession request) {
        // simple checks: trainer availability + room conflicts + member conflicts
        // For brevity, only basic operations
        Member m = memberRepo.findById(memberId).orElseThrow();
        request.setMember(m);
        return ptRepo.save(request);
    }
}
