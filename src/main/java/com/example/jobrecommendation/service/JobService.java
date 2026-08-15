package com.example.jobrecommendation.service;

import com.example.jobrecommendation.model.JobResponse;
import com.example.jobrecommendation.model.RecommendationResponse;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobService {

    private final Driver driver;

    public JobService(Driver driver) {
        this.driver = driver;
    }

    public RecommendationResponse getRecommendations(Long userId) {

        String userQuery = """
                MATCH (u:User {id: $userId})
                OPTIONAL MATCH (u)-[:HAS_SKILL]->(s:Skill)

                RETURN
                    u.id AS userId,
                    u.name AS userName,
                    collect(DISTINCT s.name) AS userSkills
                """;

        String jobQuery = """
                MATCH (u:User {id: $userId})
                      -[:HAS_SKILL]->(s:Skill)
                      <-[:REQUIRES]-(j:Job)
                      -[:OFFERED_BY]->(c:Company)

                RETURN DISTINCT
                    j.title AS job,
                    c.name AS company,
                    j.location AS location,
                    j.salary AS salary
                """;

        try (var session = driver.session()) {

            // Get user information
            Record userRecord = session.executeRead(tx ->
                    tx.run(
                            userQuery,
                            Map.of("userId", userId)
                    ).single()
            );

            Long id = userRecord.get("userId").asLong();

            String name = userRecord.get("userName").isNull()
                    ? "Unknown User"
                    : userRecord.get("userName").asString();

            List<String> skills =
                    userRecord.get("userSkills").asList(value ->
                            value.isNull() ? "" : value.asString()
                    );

            // Get recommended jobs
            List<JobResponse> jobs = session.executeRead(tx ->
                    tx.run(
                            jobQuery,
                            Map.of("userId", userId)
                    ).list(this::mapRecord)
            );

            return new RecommendationResponse(
                    id,
                    name,
                    skills,
                    jobs
            );
        }
    }

    private JobResponse mapRecord(Record record) {

        return new JobResponse(
                record.get("job").asString(),
                record.get("company").asString(),
                record.get("location").asString(),
                record.get("salary").asInt()
        );
    }
}