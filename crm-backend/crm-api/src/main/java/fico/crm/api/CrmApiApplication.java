package fico.crm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "fico.crm.*")
public class CrmApiApplication {
    public static void main(String[] args) {
        //-------Run App------------
        SpringApplication.run(CrmApiApplication.class, args);
    }
}
