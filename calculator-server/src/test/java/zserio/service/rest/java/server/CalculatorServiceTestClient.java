package zserio.service.rest.java.server;

import org.springframework.cloud.openfeign.FeignClient;

import zserio.service.rest.java.api.ZserioCalculatorServiceInterface;

@FeignClient(name = "calculator-client", url = "${feign.url}")
public interface CalculatorServiceTestClient extends ZserioCalculatorServiceInterface {
}
