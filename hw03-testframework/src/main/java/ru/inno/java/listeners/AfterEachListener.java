package ru.inno.java.listeners;

import ru.inno.java.TestInfo;
import ru.inno.java.core.TestSuitImpl;

public class AfterEachListener implements EventListener {

    @Override
    public void onFinish(TestSuitImpl testDecriptor) {
        System.out.println("afterEach finished");
    }
}
