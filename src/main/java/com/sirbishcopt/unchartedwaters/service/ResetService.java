package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

@Service
public class ResetService {

    private LeaderRepository leaderRepository;

    public ResetService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public void resetDatabase() {
        leaderRepository.resetTable();
    }

}