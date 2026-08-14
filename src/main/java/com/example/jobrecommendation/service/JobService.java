package com.example.jobrecommendation.service;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    private final Driver driver;

    public JobService(Driver driver) {
        this.driver = driver;
    }

    public List<Map<String, Object>> getRecommendedJobs(Long userId) {

        String query = """
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

            return session.executeRead(tx ->
                    tx.run(
                                    query,
                                    Map.of("userId", userId)
                            )
                            .list(this::mapRecord)
            );
        }
    }

    private Map<String, Object> mapRecord(Record record) {

        return Map.of(
                "job", record.get("job").asString(),
                "company", record.get("company").asString(),
                "location", record.get("location").asString(),
                "salary", record.get("salary").asLong()
        );
    }
}