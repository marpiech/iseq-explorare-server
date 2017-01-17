package pl.intelliseq.explorare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.intelliseq.explorare.model.phenoMarks.PhenoMarksParser;

@SpringBootApplication
@Configuration
public class ExplorareServer {

    public static void main(String[] args) {
        SpringApplication.run(ExplorareServer.class, args);
    }

    @Bean
    PhenoMarksParser medicMarksParser() {
        return new PhenoMarksParser();
    }
    
}