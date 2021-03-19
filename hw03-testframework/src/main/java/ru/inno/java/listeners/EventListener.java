package ru.inno.java.listeners;

import ru.inno.java.TestInfo;
import ru.inno.java.core.TestSuitImpl;

public interface EventListener {
    public void onFinish(TestSuitImpl testDecriptor);
}
