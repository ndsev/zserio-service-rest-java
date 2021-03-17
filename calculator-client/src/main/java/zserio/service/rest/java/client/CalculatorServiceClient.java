package zserio.service.rest.java.client;

import org.springframework.cloud.openfeign.FeignClient;

import zserio.service.rest.java.api.ZserioCalculatorServiceInterface;

@FeignClient(name = "calculator-client", url = "${feign.url}")
public interface CalculatorServiceClient extends ZserioCalculatorServiceInterface {
}
