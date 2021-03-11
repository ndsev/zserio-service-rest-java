package zserio.service.rest.java.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.github.tomakehurst.wiremock.WireMockServer;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;

@ActiveProfiles("test")
@ContextConfiguration(classes = { CalculatorClientApplication.class, WireMockConfig.class })
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@TestInstance(Lifecycle.PER_CLASS)
public class CalculatorClientIntegrationTests {

    private final static I32 POWER_REQUEST_I32 = new I32(5);
    private final static U64 POWER_RESPONSE_U64 = new U64(BigInteger.valueOf(POWER_REQUEST_I32.getValue()).pow(2));

    private final static Double SQRT_REQUEST_DOUBLE = new Double(36.0);
    private final static Double SQRT_RESPONSE_DOUBLE = new Double(
            Math.sqrt(SQRT_REQUEST_DOUBLE.getValue()));

    @Autowired
    private CalculatorServiceController controller;

    @Autowired
    private WireMockServer wireMockServer;

    private CalculatorMocks mocks;

    @BeforeAll
    private void setUp() throws IOException {
        mocks = new CalculatorMocks();
        mocks.setupMockPowerResponse(wireMockServer, POWER_REQUEST_I32);
        mocks.setupMockSquareRootResponse(wireMockServer, SQRT_REQUEST_DOUBLE);
    }

    @Test
    public void contextLoads() throws Exception {
        assert (wireMockServer != null);
        assert (mocks != null);
        assert (controller != null);
    }

    @Test
    public void testPowerOfTwoCorrectValues() {
        U64 returnValue = controller.powerOfTwo(POWER_REQUEST_I32);
        assertEquals(POWER_RESPONSE_U64, returnValue);
    }

    @Test
    public void testPowerOfTwoIncorrectValues() {
        U64 compareValue = new U64(POWER_RESPONSE_U64.getValue().add(new BigInteger("1")));
        U64 returnValue = controller.powerOfTwo(POWER_REQUEST_I32);
        assertNotEquals(compareValue, returnValue);
    }

    @Test
    public void testSquareRootCorrectValues() {
        Double returnValue = controller.squareRoot(SQRT_REQUEST_DOUBLE);
        assertEquals(SQRT_RESPONSE_DOUBLE, returnValue);
    }

    @Test
    public void testSquareRootIncorrectValues() {
        Double compareValue = new Double(SQRT_RESPONSE_DOUBLE.getValue() + 1.0);
        Double returnValue = controller.squareRoot(SQRT_REQUEST_DOUBLE);
        assertNotEquals(compareValue, returnValue);
    }
}