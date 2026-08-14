package com.example.jobrecommendation.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public Driver neo4jDriver() {

        String uri =
                "bolt+s://db-151ae9ef.databases.cognodb.com";

        String username = "cognodb";

        String password = "069fddd77bbca413d309f21c3629ae91";

        return GraphDatabase.driver(
                uri,
                AuthTokens.basic(username, password)
        );
    }
}