package zserio.service.rest.java.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;
import lombok.extern.slf4j.Slf4j;
import zserio.runtime.ZserioError;
import zserio.service.rest.java.api.ZserioCalculatorServiceInterface;

@RestController
@ControllerAdvice
@Slf4j
public class ZserioCalculatorServiceController implements ZserioCalculatorServiceInterface {
    private ZserioCalculatorServiceImpl calculatorServiceImpl = new ZserioCalculatorServiceImpl();

    @Override
    public ResponseEntity<U64> powerOfTwo(I32 request) {
        if (request != null) {
            try {
                U64 result = calculatorServiceImpl.powerOfTwoImpl(request, null);
                return ResponseEntity.ok().body(result);
            } catch (ZserioError e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        log.warn("no or invalid request data");
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Double> squareRoot(Double request) {
        if (request != null) {
            try {
                Double result = calculatorServiceImpl.squareRootImpl(request, null);
                return ResponseEntity.ok().body(result);
            } catch (ZserioError e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        log.warn("no or invalid request data");
        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAnyException(RuntimeException exception) {
        return "server exception: " + exception.getMessage();
    }
}
