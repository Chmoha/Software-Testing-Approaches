package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service
public class Task2Service {

    private static final int MAX_N = 100;

    public BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        if (n > MAX_N) {
            throw new IllegalArgumentException("Input must not exceed " + MAX_N);
        }

        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * Returns an array: [F(n-2), F(n-1), F(n)].
     */
    public BigInteger[] fibonacciWithPrevious(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        if (n > MAX_N) {
            throw new IllegalArgumentException("Input must not exceed " + MAX_N);
        }

        BigInteger[] result = new BigInteger[3];
        if (n == 0) {
            result[0] = null;
            result[1] = null;
            result[2] = BigInteger.ZERO;
            return result;
        }
        if (n == 1) {
            result[0] = null;
            result[1] = BigInteger.ZERO;
            result[2] = BigInteger.ONE;
            return result;
        }

        result[0] = fibonacci(n - 2);
        result[1] = fibonacci(n - 1);
        result[2] = fibonacci(n);
        return result;
    }
}
