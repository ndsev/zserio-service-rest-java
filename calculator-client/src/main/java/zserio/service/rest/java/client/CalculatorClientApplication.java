package zserio.service.rest.java.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(proxyBeanMethods = false)
@EnableAsync
@EnableFeignClients()
@Profile("test")
public class CalculatorClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorClientApplication.class, args);
    }
}
