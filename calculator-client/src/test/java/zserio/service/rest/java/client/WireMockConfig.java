package zserio.service.rest.java.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.WireMockServer;

@ActiveProfiles("test")
@TestConfiguration
public class WireMockConfig {
    @Value("${wiremock.port}")
    String port;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockCalculatorService() {
        return new WireMockServer(Integer.decode(port));
    }
}