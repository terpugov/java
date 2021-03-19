package ru.inno.java.listeners;

import ru.inno.java.TestInfo;
import ru.inno.java.core.TestSuitImpl;

public class TestListener implements EventListener {

    @Override
    public void onFinish(TestSuitImpl testDecriptor) {
        if (testDecriptor != null && testDecriptor.getStatus() != null){
            System.out.println(testDecriptor.getStatus().name() + " | "  + testDecriptor.getClassName() + "::" + testDecriptor.getCurrentMethodName());
            switch (testDecriptor.getStatus()){
                case FAILED:
                    testDecriptor.getTestResult().addFailedTest();
                    break;
                case SUCCESS:
                    testDecriptor.getTestResult().addPassedTest();
                    break;
                case SKIPPED:
                    testDecriptor.getTestResult().addSkipedTest();
                    break;
            }
        }
    }
}
