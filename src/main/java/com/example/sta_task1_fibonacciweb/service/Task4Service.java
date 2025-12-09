package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;

@Service
public class Task4Service {

    // Ищем индекс ключа в исходном массиве
    public int searchIndexOriginalArray(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    // Проверка бинарного поиска на отсортированном массиве (не для индекса пользователя)
    public boolean binarySearchLogic(int[] arr, int key) {
        int[] sorted = arr.clone();
        java.util.Arrays.sort(sorted);

        int left = 0;
        int right = sorted.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sorted[mid] == key) return true;
            else if (sorted[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
