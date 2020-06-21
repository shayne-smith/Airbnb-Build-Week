package com.lambdaschool.airbnbbuildweek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AirbnbBuildweekApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AirbnbBuildweekApplication.class,
            args);
    }

}
