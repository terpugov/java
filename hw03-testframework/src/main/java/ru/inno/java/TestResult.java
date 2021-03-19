package ru.inno.java;

public class TestResult {
    private int passedTest = 0;
    private int failedTest = 0;
    private int skippedTest = 0;
    private int testNumber = 0;

    public int getPassedTest() {
        return passedTest;
    }

    public void addPassedTest() {
        this.passedTest++;
    }

    public int getFailedTest() {
        return failedTest;
    }

    public void addFailedTest() {
        this.failedTest++;
    }

    public int getSkippedTest() {
        return skippedTest;
    }

    public void addSkipedTest() {
        this.skippedTest++;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void addTestNumber(int testNumber) {
        this.testNumber++;
    }
}
