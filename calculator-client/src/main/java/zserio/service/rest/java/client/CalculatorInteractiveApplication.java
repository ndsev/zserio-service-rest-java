package zserio.service.rest.java.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(proxyBeanMethods = false)
@EnableAsync
@EnableFeignClients()
@Profile("default")
public class CalculatorInteractiveApplication implements ApplicationRunner {
    @Autowired
    private CalculatorServiceController service;

    public static void main(String[] args) {
        SpringApplication.run(CalculatorInteractiveApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Calculator calculator = new Calculator(service);
        calculator.runCalculator();
        System.exit(0);
    }
}
