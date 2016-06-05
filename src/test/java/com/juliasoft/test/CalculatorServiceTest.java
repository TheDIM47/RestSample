package com.juliasoft.test;

import com.juliasoft.rest.CalculatorService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests for CalculatorService
 */
public class CalculatorServiceTest {

    // Add
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNone() {
        new CalculatorService().add();
        fail("Should not happens");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSingle() {
        new CalculatorService().add(5);
        fail("Should not happens");
    }

    @Test
    public void testAdd() {
        assertThat(new CalculatorService().add(0, 0), is(0.0));
        assertThat(new CalculatorService().add(1, 2, 3), is(6.0));
    }

    // Subtract

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractNone() {
        assertThat(new CalculatorService().subtract(), is(0.0));
        fail("Should not happens");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractSingle() {
        assertThat(new CalculatorService().subtract(5), is(5.0));
        fail("Should not happens");
    }

    @Test
    public void testSubtract() {
        assertThat(new CalculatorService().subtract(0, 0), is(0.0));
        assertThat(new CalculatorService().subtract(1, 2, 3), is(-4.0));
    }

    // Multiply

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyNone() {
        new CalculatorService().multiply();
        fail("Should not happens");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplySingle() {
        new CalculatorService().multiply(5);
        fail("Should not happens");
    }

    @Test
    public void testMultiply() {
        assertThat(new CalculatorService().multiply(0, 0), is(0.0));
        assertThat(new CalculatorService().multiply(2, 1, 3), is(6.0));
    }

    @Test
    public void testMultiplyInfinity() {
        assertThat(new CalculatorService().multiply(Double.MAX_VALUE, Double.MAX_VALUE), is(Double.POSITIVE_INFINITY));
    }

    // Divide

    @Test(expected = IllegalArgumentException.class)
    public void testDivideNone() {
        new CalculatorService().divide();
        fail("Should not happens");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideSingle() {
        new CalculatorService().divide(5);
        fail("Should not happens");
    }

    @Test
    public void testDivideZero() {
        assertThat(new CalculatorService().divide(5, 1, 3, 0, 4, 7), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testDivideZeroOk() {
        assertThat(new CalculatorService().divide(0, 5, 1, 3, 4, 7), is(0.0));
    }

    @Test
    public void testDivide() {
        assertThat(new CalculatorService().divide(10, 2), is(5.0));
        assertThat(new CalculatorService().divide(100, 2, 5), is(10.0));
    }

}
