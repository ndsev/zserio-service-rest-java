package zserio.service.rest.java.client;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;
import zserio.runtime.io.ByteArrayBitStreamWriter;
import zserio.service.rest.java.api.ZserioCalculatorServiceInterface;

public class CalculatorMocks {

    public void setupMockPowerResponse(WireMockServer mockService, I32 request) throws IOException {
        U64 response = new U64(BigInteger.valueOf(request.getValue()).pow(2));
        ByteArrayBitStreamWriter writer = new ByteArrayBitStreamWriter();
        response.write(writer, false);
        byte[] byteArray = writer.toByteArray();

        mockService.stubFor(WireMock.post(WireMock.urlEqualTo(ZserioCalculatorServiceInterface.POWER_OF_TWO))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE).withBody(byteArray)));
    }

    public void setupMockSquareRootResponse(WireMockServer mockService, Double request) throws IOException {
        Double response = new Double(Math.sqrt(request.getValue()));
        ByteArrayBitStreamWriter writer = new ByteArrayBitStreamWriter();
        response.write(writer, false);
        byte[] byteArray = writer.toByteArray();

        mockService.stubFor(WireMock.post(WireMock.urlEqualTo(ZserioCalculatorServiceInterface.SQUARE_ROOT))
                .willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE).withBody(byteArray)));
    }
}