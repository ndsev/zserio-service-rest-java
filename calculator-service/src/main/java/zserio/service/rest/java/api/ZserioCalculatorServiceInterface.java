package zserio.service.rest.java.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;

public interface ZserioCalculatorServiceInterface {

    // use different URLs as generated (Calculator.SERVICE_FULL_NAME = "example.calculator.Calculator")
    // to work together with Python implementation provided in
    // https://github.com/ndsev/zserio-service-http-python
    public final static String POWER_OF_TWO = "/calculator/Calculator/powerOfTwo";
    public final static String SQUARE_ROOT = "/calculator/Calculator/squareRoot";

    @PostMapping(value = POWER_OF_TWO, consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE }, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<U64> powerOfTwo(@RequestBody I32 request);

    @PostMapping(value = SQUARE_ROOT, consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE }, produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<Double> squareRoot(@RequestBody Double request);
}
