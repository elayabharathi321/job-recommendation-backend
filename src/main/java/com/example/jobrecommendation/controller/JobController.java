package com.example.jobrecommendation.controller;

import com.example.jobrecommendation.model.RecommendationResponse;
import com.example.jobrecommendation.service.JobService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/recommendations/{userId}")
    public RecommendationResponse getRecommendations(
            @PathVariable Long userId) {

        return jobService.getRecommendations(userId);
    }
}