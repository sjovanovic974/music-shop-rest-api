package sasa.jovanovic.musicshop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("clean")
public class DBClean {

    @Bean
    public FlywayMigrationStrategy clean(){
        return flyway -> {
            log.info("Dropping tables from DB");
            flyway.clean();
            log.info("Recreating tables and inserting data to DB");
            flyway.migrate();
        };
    }
}
