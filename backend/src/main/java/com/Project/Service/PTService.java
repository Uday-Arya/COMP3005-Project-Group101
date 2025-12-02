package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;

@Service
public class PTService {
    private final PersonalTrainingSessionRepo repo;

    public PTService(PersonalTrainingSessionRepo repo) {
        this.repo = repo;
    }

    public PersonalTrainingSession save(PersonalTrainingSession s) {
        return repo.save(s);
    }
}
