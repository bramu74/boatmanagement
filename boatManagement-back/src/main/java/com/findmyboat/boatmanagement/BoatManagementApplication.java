package com.findmyboat.boatmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class BoatManagementApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BoatManagementApplication.class, args);
    }

}
