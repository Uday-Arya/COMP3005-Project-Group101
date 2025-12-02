package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassService {
    private final GroupClassSessionRepo repo;

    public ClassService(GroupClassSessionRepo repo) {
        this.repo = repo;
    }

    public List<GroupClassSession> findBetween(LocalDateTime from, LocalDateTime to) {
        return repo.findByStartTimeBetween(from, to);
    }

    public GroupClassSession findById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}