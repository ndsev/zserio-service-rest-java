package zserio.service.rest.java.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import zserio.service.rest.java.spring.SpringZserioConverterRegistrar;

@SpringBootApplication(proxyBeanMethods = false)
@EnableAsync
@Import(SpringZserioConverterRegistrar.class)
public class CalculatorServiceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorServiceServerApplication.class, args);
    }
}
