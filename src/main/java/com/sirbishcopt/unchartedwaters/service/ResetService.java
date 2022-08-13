package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

@Service
public class ResetService {

    // TODO weź dwa repozytoria i tutaj to zrób, zniszcz lidera
    private LeaderRepository leaderRepository;

    public ResetService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    // TODO change name, two methods - drop and prepare
    public void resetDatabase() {
        leaderRepository.resetTable();
    }

}