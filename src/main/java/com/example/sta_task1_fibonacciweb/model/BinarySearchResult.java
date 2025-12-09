package com.example.sta_task1_fibonacciweb.model;

import java.util.List;

public class BinarySearchResult {
    private int result;
    private List<String> executionPath;
    private List<Integer> midValues;
    private String pathType;

    public BinarySearchResult(int result, List<String> executionPath, List<Integer> midValues, String pathType) {
        this.result = result;
        this.executionPath = executionPath;
        this.midValues = midValues;
        this.pathType = pathType;
    }

    // Getters and Setters
    public int getResult() { return result; }
    public List<String> getExecutionPath() { return executionPath; }
    public List<Integer> getMidValues() { return midValues; }
    public String getPathType() { return pathType; }
}