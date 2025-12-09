package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4ServiceTest {

    private Task4Service service;

    @BeforeEach
    void setUp() {
        service = new Task4Service();
    }

    // === Partition / Boundary Cases ===

    @Test
    void testKeyFound() {
        int[] arr = {3, 1, 5, 6, 2, 0};
        int key = 5;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(2, index); // индекс в исходном массиве
        assertTrue(logicCheck); // бинарный поиск должен найти элемент
    }

    @Test
    void testKeyNotFound() {
        int[] arr = {3, 1, 5, 6, 2, 0};
        int key = 10;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(-1, index);
        assertFalse(logicCheck);
    }

    @Test
    void testSingleElementArrayKeyFound() {
        int[] arr = {7};
        int key = 7;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(0, index);
        assertTrue(logicCheck);
    }

    @Test
    void testSingleElementArrayKeyNotFound() {
        int[] arr = {7};
        int key = 3;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(-1, index);
        assertFalse(logicCheck);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int key = 5;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(-1, index);
        assertFalse(logicCheck);
    }

    @Test
    void testKeyIsFirstElement() {
        int[] arr = {9, 3, 5};
        int key = 9;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(0, index);
        assertTrue(logicCheck);
    }

    @Test
    void testKeyIsMiddleElement() {
        int[] arr = {3, 9, 5};
        int key = 9;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(1, index);
        assertTrue(logicCheck);
    }

    @Test
    void testKeyIsLastElement() {
        int[] arr = {3, 5, 9};
        int key = 9;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(2, index);
        assertTrue(logicCheck);
    }

    @Test
    void testKeySmallerThanAll() {
        int[] arr = {3, 5, 7};
        int key = 0;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(-1, index);
        assertFalse(logicCheck);
    }

    @Test
    void testKeyLargerThanAll() {
        int[] arr = {3, 5, 7};
        int key = 10;

        int index = service.searchIndexOriginalArray(arr, key);
        boolean logicCheck = service.binarySearchLogic(arr, key);

        assertEquals(-1, index);
        assertFalse(logicCheck);
    }
}
