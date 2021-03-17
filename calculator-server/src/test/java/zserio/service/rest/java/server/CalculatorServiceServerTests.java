package zserio.service.rest.java.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;

@ActiveProfiles("test")
@ContextConfiguration(classes = CalculatorServiceServerApplication.class)
@EnableFeignClients
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CalculatorServiceServerTests {
    @Autowired
    private CalculatorServiceTestClient client;

    @Test
    public void contextLoads() {
        assert (client != null);
    }

    @Test
    public void testPowerOfTwoCorrectValues() {
        I32 request = new I32(5);
        ResponseEntity<U64> response = client.powerOfTwo(request);
        assert (response.getStatusCode().equals(HttpStatus.OK));
        assertEquals(response.getBody().getValue().intValueExact(), 25);
    }

    @Test
    public void testPowerOfTwoIncorrectValues() {
        I32 request = new I32(5);
        ResponseEntity<U64> response = client.powerOfTwo(request);
        assert (response.getStatusCode().equals(HttpStatus.OK));
        assertNotEquals(response.getBody().getValue().intValueExact(), request.getValue() + 1);
    }

    @Test
    public void testSquareRootCorrectValues() {
        Double request = new Double(36.0);
        ResponseEntity<Double> response = client.squareRoot(request);
        assert (response.getStatusCode().equals(HttpStatus.OK));
        assertEquals(response.getBody().getValue(), 6.0);
    }

    @Test
    public void testSquareRootIncorrectValues() {
        Double request = new Double(36.0);
        ResponseEntity<Double> response = client.squareRoot(request);
        assert (response.getStatusCode().equals(HttpStatus.OK));
        assertNotEquals(response.getBody().getValue(), request.getValue());
    }
}
