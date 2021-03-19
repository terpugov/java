package ru.inno.java.core;

import ru.inno.java.Status;
import ru.inno.java.TestResult;

import java.lang.reflect.Method;
import java.util.List;

public class TestSuitImpl implements TestSuit {
    private String className;
    private String currentMethodName;
    private Status status;
    private String nameOfTestClass;
    private List<Method> beforeEach;
    private List<Method> afterEach;
    private List<Method> test;
    private TestResult testResult;
    private Class<?> instanse;


    public TestSuitImpl() {
        this.testResult = new TestResult();

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCurrentMethodName() {
        return currentMethodName;
    }

    public void setCurrentMethodName(String currentMethodName) {
        this.currentMethodName = currentMethodName;
    }

    public String getNameOfTestClass() {
        return nameOfTestClass;
    }

    public void setNameOfTestClass(String nameOfTestClass) {
        this.nameOfTestClass = nameOfTestClass;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Method> getBeforeEach() {
        return beforeEach;
    }

    public void setBeforeEach(List<Method> beforeEach) {
        this.beforeEach = beforeEach;
    }

    public List<Method> getAfterEach() {
        return afterEach;
    }

    public void setAfterEach(List<Method> afterEach) {
        this.afterEach = afterEach;
    }

    public List<Method> getTest() {
        return test;
    }

    public void setTest(List<Method> test) {
        this.test = test;
    }
}

