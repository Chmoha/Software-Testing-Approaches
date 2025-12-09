package com.example.sta_task1_fibonacciweb.model;

import java.util.List;

public class TestResult {
    private String testId;
    private int[] inputArray;
    private int target;
    private int expectedResult;
    private int actualResult;
    private boolean passed;
    private final List<String> executionPath;
    private String actualPath;
    private String expectedPath;

    public TestResult(String testId, int[] inputArray, int target, int expectedResult,
                      int actualResult, boolean passed, List<String> executionPath,
                      String actualPath, String expectedPath) {
        this.testId = testId;
        this.inputArray = inputArray;
        this.target = target;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.passed = passed;
        this.executionPath = executionPath;
        this.actualPath = actualPath;
        this.expectedPath = expectedPath;
    }

    // Getters and Setters
    public String getTestId() { return testId; }
    public int[] getInputArray() { return inputArray; }
    public int getTarget() { return target; }
    public int getExpectedResult() { return expectedResult; }
    public int getActualResult() { return actualResult; }
    public boolean isPassed() { return passed; }
    public List<String> getExecutionPath() { return executionPath; }
    public String getActualPath() { return actualPath; }
    public String getExpectedPath() { return expectedPath; }
}