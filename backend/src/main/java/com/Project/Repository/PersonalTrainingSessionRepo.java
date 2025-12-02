package com.Project.Repository;

import com.Project.Entity.PersonalTrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonalTrainingSessionRepo extends JpaRepository<PersonalTrainingSession, Long> {
    List<PersonalTrainingSession> findByTrainerIdAndStartTimeBetween(Long trainerId, LocalDateTime from, LocalDateTime to);
    List<PersonalTrainingSession> findByRoomIdAndStartTimeBetween(Long roomId, LocalDateTime from, LocalDateTime to);
    List<PersonalTrainingSession> findByMemberIdAndStartTimeBetween(Long memberId, LocalDateTime from, LocalDateTime to);
}