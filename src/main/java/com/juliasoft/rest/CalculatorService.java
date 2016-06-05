package com.juliasoft.rest;

import java.util.Arrays;

/**
 * Basic Calculator service implementation
 */
public class CalculatorService implements MathService {
    public double add(double... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("Calculator require at least two values!");
        }
        return Arrays.stream(values).reduce(0, Double::sum);
    }

    public double subtract(double... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("Calculator require at least two values!");
        }
        return Arrays.stream(values, 1, values.length).reduce(values[0], (x, y) -> x - y);
    }

    public double multiply(double... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("Calculator require at least two values!");
        }
        return Arrays.stream(values).reduce(1, (x, y) -> x * y);
    }

    public double divide(double... values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("Calculator require at least two values!");
        }
        return Arrays.stream(values, 1, values.length).reduce(values[0], (x, y) -> x / y);
    }
}
