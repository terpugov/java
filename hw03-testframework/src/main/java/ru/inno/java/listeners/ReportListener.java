package ru.inno.java.listeners;

import ru.inno.java.core.TestSuitImpl;

public class ReportListener implements EventListener {
    @Override
    public void onFinish(TestSuitImpl testDecriptor) {
        System.out.println("Report");
        System.out.println("failed: " + testDecriptor.getTestResult().getFailedTest());
        System.out.println("skipped: " + testDecriptor.getTestResult().getSkippedTest());
        System.out.println("passed: " +  testDecriptor.getTestResult().getPassedTest());
    }
}
