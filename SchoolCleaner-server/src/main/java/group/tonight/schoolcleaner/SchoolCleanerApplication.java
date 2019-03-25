package group.tonight.schoolcleaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchoolCleanerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolCleanerApplication.class, args);
    }

}
