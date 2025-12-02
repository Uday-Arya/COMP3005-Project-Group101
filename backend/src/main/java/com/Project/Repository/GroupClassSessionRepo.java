package com.Project.Repository;

import com.Project.Entity.GroupClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface GroupClassSessionRepo extends JpaRepository<GroupClassSession, Long> {
    List<GroupClassSession> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);
}