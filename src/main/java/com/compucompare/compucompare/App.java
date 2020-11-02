package com.compucompare.compucompare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class App
{
    /**
     * Start the Spring application.
     * 
     * @param args Command-Line Arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}