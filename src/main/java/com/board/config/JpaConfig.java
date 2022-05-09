package com.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories({"com.recruit.assignment.domain"})
@Configuration
public class JpaConfig {
}
