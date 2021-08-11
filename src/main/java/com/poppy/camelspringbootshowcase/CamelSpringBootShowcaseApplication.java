package com.poppy.camelspringbootshowcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@SpringBootApplication
public class CamelSpringBootShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelSpringBootShowcaseApplication.class, args);
    }

    @Bean
    EmbeddedKafkaBroker broker() {
        return new EmbeddedKafkaBroker(1)
                .kafkaPorts(9092)
                .brokerProperty("auto.create.topics.enable", "true")
                .brokerProperty("listeners", "PLAINTEXT://localhost:9092")
                .brokerProperty("advertised.listeners", "PLAINTEXT://localhost:9092")
                .brokerProperty("listener.security.protocol.map", "PLAINTEXT:PLAINTEXT,REMOTE:PLAINTEXT")
                .brokerListProperty("spring.kafka.bootstrap-servers");
    }

}
