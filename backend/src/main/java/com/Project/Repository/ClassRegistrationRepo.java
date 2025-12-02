package com.Project.Repository;

import com.Project.Entity.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClassRegistrationRepo extends JpaRepository<ClassRegistration, Long> {
    Optional<ClassRegistration> findByMemberIdAndGroupClassSessionId(Long memberId, Long classId);
    long countByGroupClassSessionId(Long classId);
}