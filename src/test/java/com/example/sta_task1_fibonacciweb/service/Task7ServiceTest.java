package com.example.sta_task1_fibonacciweb.service;

import com.example.sta_task1_fibonacciweb.model.BinarySearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7ServiceTest {

    private Task7Service service;

    @BeforeEach
    void setup() {
        service = new Task7Service();
    }

    @Test
    void testCase1_emptyArray() {
        int[] arr = {};
        BinarySearchResult result = service.binarySearch(arr, 5);

        assertEquals(-1, result.getResult());
        assertEquals("Path 1", result.getPathType());
    }

    @Test
    void testCase2_targetInMiddle() {
        int[] arr = {1, 2, 3, 4, 5};
        BinarySearchResult result = service.binarySearch(arr, 3);

        assertEquals(2, result.getResult());
        assertEquals("Path 2", result.getPathType());
    }

    @Test
    void testCase3_targetAtStart() {
        int[] arr = {1, 2, 3, 4, 5};
        BinarySearchResult result = service.binarySearch(arr, 1);

        assertEquals(0, result.getResult());
        assertEquals("Path 4", result.getPathType());
    }

    @Test
    void testCase4_targetAtEnd() {
        int[] arr = {1, 2, 3, 4, 5};
        BinarySearchResult result = service.binarySearch(arr, 5);

        assertEquals(4, result.getResult());
        assertEquals("Path 4", result.getPathType());
    }

    @Test
    void testCase5_targetNotFound_goRight() {
        int[] arr = {1, 3, 5, 7, 9};
        BinarySearchResult result = service.binarySearch(arr, 4);

        assertEquals(-1, result.getResult());
        assertEquals("Path 3", result.getPathType());
    }

    @Test
    void testCase6_singleElementFound() {
        int[] arr = {10};
        BinarySearchResult result = service.binarySearch(arr, 10);

        assertEquals(0, result.getResult());
        assertEquals("Path 2", result.getPathType());
    }

    @Test
    void testCase7_singleElementNotFound() {
        int[] arr = {10};
        BinarySearchResult result = service.binarySearch(arr, 5);

        assertEquals(-1, result.getResult());
        assertEquals("Path 1", result.getPathType());
    }

    @Test
    void testCase8_twoElements_targetFirst() {
        int[] arr = {2, 9};
        BinarySearchResult result = service.binarySearch(arr, 2);

        assertEquals(0, result.getResult());
        assertEquals("Path 2", result.getPathType()); // Было движение влево? Нет → Path 2, но mid=0 сразу совпал → Path 2
    }

    @Test
    void testCase9_twoElements_targetSecond() {
        int[] arr = {2, 9};
        BinarySearchResult result = service.binarySearch(arr, 9);

        assertEquals(1, result.getResult());
        assertEquals("Path 4", result.getPathType()); // mid=0 → идём вправо → вошёл right → Path 4
    }

    @Test
    void testCase10_negativeNumbers() {
        int[] arr = {-10, -5, 0, 5, 10};
        BinarySearchResult result = service.binarySearch(arr, -5);

        assertEquals(1, result.getResult());
        assertEquals("Path 4", result.getPathType()); // mid=2 → идём влево → wentLeft → Path 4
    }

    @Test
    void testCase11_targetBetweenElements_notFound() {
        int[] arr = {1, 4, 8, 12};
        BinarySearchResult result = service.binarySearch(arr, 5);

        assertEquals(-1, result.getResult());
        assertEquals("Path 3", result.getPathType()); // путь вправо был
    }

    @Test
    void testCase12_withDuplicates() {
        int[] arr = {1, 2, 2, 2, 3};
        BinarySearchResult result = service.binarySearch(arr, 2);

        // может вернуть 1, 2 или 3 — зависит от mid
        assertTrue(result.getResult() >= 1 && result.getResult() <= 3);

        // mid=2 → сразу найден → ни влево ни вправо → Path 2
        assertEquals("Path 2", result.getPathType());
    }

    @Test
    void testCase13_largeArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i;

        BinarySearchResult result = service.binarySearch(arr, 777);

        assertEquals(777, result.getResult());
        assertEquals("Path 4", result.getPathType()); // обязательно движение вправо есть
    }

}
