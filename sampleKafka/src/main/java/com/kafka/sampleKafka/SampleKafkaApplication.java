package com.kafka.sampleKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SampleKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleKafkaApplication.class, args);
    }

}
