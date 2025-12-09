package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2ServiceTest {

    private final Task2Service service = new Task2Service();

    @Test
    void testFibonacciZero() {
        assertEquals(BigInteger.ZERO, service.fibonacci(0));
    }

    @Test
    void testFibonacciOne() {
        assertEquals(BigInteger.ONE, service.fibonacci(1));
    }

    @Test
    void testFibonacciFive() {
        assertEquals(BigInteger.valueOf(5), service.fibonacci(5));
    }
    @Test
    void testFibonacciSix() {
        assertEquals(BigInteger.valueOf(8), service.fibonacci(6));
    }
    @Test
    void testFibonacciMaxAllowed() {
        BigInteger result = service.fibonacci(100);
        assertEquals(new BigInteger("354224848179261915075"), result); // правильное значение F(100)
    }
    @Test
    void testFibonacciTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> service.fibonacci(101));
    }
    @Test
    void testFibonacciNegative() {
        assertThrows(IllegalArgumentException.class, () -> service.fibonacci(-1));
    }

//    @Test
//    void testFibonacciWithPreviousZero() {
//        BigInteger[] result = service.fibonacciWithPrevious(0);
//        assertEquals(BigInteger.ZERO, result[2]);
//    }
//    @Test
//    void testFibonacciWithPreviousOne() {
//        BigInteger[] result = service.fibonacciWithPrevious(1);
//        assertEquals(BigInteger.ZERO, result[1]);
//        assertEquals(BigInteger.ONE, result[2]);
//    }

}
