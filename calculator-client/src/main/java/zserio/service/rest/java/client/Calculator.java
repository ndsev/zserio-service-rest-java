package zserio.service.rest.java.client;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import example.calculator.Double;
import example.calculator.I32;
import example.calculator.U64;

public class Calculator {
    private CalculatorServiceController service;

    @Autowired
    public Calculator(CalculatorServiceController service) {
        this.service = service;
    }

    private enum Mode {
        POWER_OF_TWO, SQUARE_ROOT
    }

    public void runCalculator() {
        System.out.println("started calculator");
        System.out.println("write 'h' + ENTER for help");

        Mode mode = Mode.POWER_OF_TWO;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print((mode == Mode.POWER_OF_TWO ? "p" : "s") + "> ");

            if (!scanner.hasNextLine())
                break;

            final String input = scanner.nextLine();
            if (input.isEmpty())
                continue;

            if (input.charAt(0) == 'q') {
                System.out.println("quitting calculator");
                break;
            }

            if (input.charAt(0) == 'h') {
                printHelp();
                continue;
            }

            if (input.charAt(0) == 'p') {
                mode = Mode.POWER_OF_TWO;
                System.out.println("mode set to powerOfTwo(int32)");
                continue;
            }

            if (input.charAt(0) == 's') {
                mode = Mode.SQUARE_ROOT;
                System.out.println("mode set to squareRoot(double)");
                continue;
            }

            if (mode == Mode.POWER_OF_TWO)
                powerOfTwo(input);
            else
                squareRoot(input);
        }
        scanner.close();
    }

    private void powerOfTwo(String input) {
        I32 request;
        try {
            int value = Integer.parseInt(input);
            request = new I32(value);
        } catch (Exception e) {
            System.out.println("error: '" + input + "' cannot be converted to int32!");
            System.out.println(e.getMessage());
            return;
        }

        try {
            U64 response = service.powerOfTwo(request);
            System.out.println(response.getValue());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private void squareRoot(String input) {
        Double request;
        try {
            double value = java.lang.Double.parseDouble(input);
            request = new Double(value);
        } catch (Exception e) {
            System.out.println("error: '" + input + "' cannot be converted to double!");
            System.out.println(e.getMessage());
            return;
        }

        try {
            Double response = service.squareRoot(request);
            System.out.println(response.getValue());
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private void printHelp() {
        System.out.println("Help:");
        System.out.println(" INPUT        Any valid input for the current mode.");
        System.out.println(" p            Sets powerOfTwo(int32) mode.");
        System.out.println(" s            Sets squareRoot(double) mode.");
        System.out.println(" h            Prints this help.");
        System.out.println(" q            Quits the client.");
        System.out.println("");
        System.out.println("Note that the letter before the '>' denotes the current mode.");
    }
}
