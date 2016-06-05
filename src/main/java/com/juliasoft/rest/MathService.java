package com.juliasoft.rest;

/**
 * Calculator (Math) Service interface
 * <p>
 * /add/{a}/{b}/{c}
 * /subtract/{a}/{b}/{c}
 * /multiply/{a}/{b}/{c}
 * /divide/{a}/{b}
 */
public interface MathService {

    double add(double... values);

    double subtract(double... values);

    double multiply(double... values);

    double divide(double... values);
}
