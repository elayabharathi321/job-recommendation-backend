package com.example.jobrecommendation.model;

import java.util.List;

public class RecommendationResponse {

    private Long userId;
    private String userName;
    private List<String> userSkills;
    private List<JobResponse> jobs;

    public RecommendationResponse() {
    }

    public RecommendationResponse(
            Long userId,
            String userName,
            List<String> userSkills,
            List<JobResponse> jobs) {

        this.userId = userId;
        this.userName = userName;
        this.userSkills = userSkills;
        this.jobs = jobs;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<String> userSkills) {
        this.userSkills = userSkills;
    }

    public List<JobResponse> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobResponse> jobs) {
        this.jobs = jobs;
    }
}