package com.Project.Service;

import com.Project.Entity.*;
import com.Project.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepo trainerRepo;
    private final TrainerAvailabilityRepo availabilityRepo;
    private final PersonalTrainingSessionRepo ptRepo;

    public TrainerService(TrainerRepo trainerRepo,
                          TrainerAvailabilityRepo availabilityRepo,
                          PersonalTrainingSessionRepo ptRepo) {
        this.trainerRepo = trainerRepo;
        this.availabilityRepo = availabilityRepo;
        this.ptRepo = ptRepo;
    }

    public Trainer createTrainer(Trainer t) {
        return trainerRepo.save(t);
    }

    public List<TrainerAvailability> addAvailability(Long trainerId, TrainerAvailability availability) {
        Trainer t = trainerRepo.findById(trainerId).orElseThrow();
        availability.setTrainer(t);
        availabilityRepo.save(availability);
        return availabilityRepo.findByTrainerId(trainerId);
    }

    public List<PersonalTrainingSession> getSchedule(Long trainerId) {
        // naive: return all sessions of trainer
        return ptRepo.findByTrainerIdAndStartTimeBetween(trainerId,
                java.time.LocalDateTime.now().minusYears(1),
                java.time.LocalDateTime.now().plusYears(1));
    }
}