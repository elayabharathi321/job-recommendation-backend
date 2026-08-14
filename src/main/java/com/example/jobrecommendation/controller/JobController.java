package com.example.jobrecommendation.controller;

import com.example.jobrecommendation.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/recommendations/{userId}")
    public List<Map<String, Object>> getRecommendedJobs(
            @PathVariable Long userId) {

        return jobService.getRecommendedJobs(userId);
    }
}