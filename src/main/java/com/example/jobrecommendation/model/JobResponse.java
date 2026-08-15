package com.example.jobrecommendation.model;

public class JobResponse {

    private String job;
    private String company;
    private String location;
    private Integer salary;

    public JobResponse() {
    }

    public JobResponse(
            String job,
            String company,
            String location,
            Integer salary) {

        this.job = job;
        this.company = company;
        this.location = location;
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}