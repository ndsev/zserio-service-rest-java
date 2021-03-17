package zserio.service.rest.java.server;

import java.math.BigInteger;

import example.calculator.Calculator.CalculatorService;
import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZserioCalculatorServiceImpl extends CalculatorService {
    @Override
    protected U64 powerOfTwoImpl(I32 request, Object context) {
        final U64 response = new U64(BigInteger.valueOf(request.getValue()).pow(2));
        log.info("CalculatorServer: powerOfTwoImpl called, request={}, response={}, context={}", request.getValue(),
                response.getValue(), context);
        return response;
    }

    @Override
    protected Double squareRootImpl(Double request, Object context) {
        final Double response = new Double(Math.sqrt(request.getValue()));
        log.info("CalculatorServer: squareRootImpl called, request={}, response={}, context={}", request.getValue(),
                response.getValue(), context);
        return response;
    }
}
