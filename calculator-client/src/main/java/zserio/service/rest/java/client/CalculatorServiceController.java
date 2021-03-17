package zserio.service.rest.java.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CalculatorServiceController {
    @Autowired
    private CalculatorServiceClient client;

    public U64 powerOfTwo(I32 request) {
        return client.powerOfTwo(request).getBody();
    }

    public Double squareRoot(Double request) {
        return client.squareRoot(request).getBody();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAnyException(RuntimeException exception) {
        String message = "client exception: " + exception.getMessage();
        log.error(message);
        return message;
    }
}
