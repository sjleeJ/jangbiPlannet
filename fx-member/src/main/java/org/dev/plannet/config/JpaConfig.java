package org.dev.plannet.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.dev.plannet")
@EnableJpaRepositories(basePackages = "org.dev.plannet")
public class JpaConfig {
}