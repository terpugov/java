package ru.inno.java;

import ru.inno.java.annatations.AfterEach;
import ru.inno.java.annatations.Test;
import ru.inno.java.core.TestRunner;
import ru.inno.java.listeners.AfterEachListener;
import ru.inno.java.listeners.ReportListener;
import ru.inno.java.listeners.TestListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        EventManager eventManager = new EventManager();
        String className = "ru.inno.java.TestClass";
        TestRunner testRunner = new TestRunner(eventManager, className);
        testRunner.getEventManager().subscribe(AfterEach.name, new AfterEachListener());
        testRunner.getEventManager().subscribe(Test.name, new TestListener());
        testRunner.getEventManager().subscribe("Report", new ReportListener());
        String className1 = "ru.inno.java.TestClass";
        TestRunner testRunner1 = new TestRunner(eventManager, className);
        testRunner1.getEventManager().subscribe(AfterEach.name, new AfterEachListener());
        testRunner1.getEventManager().subscribe(Test.name, new TestListener());
        testRunner1.getEventManager().subscribe("Report", new ReportListener());
        Thread thread = new Thread(testRunner);
        Thread thread1 = new Thread(testRunner1);
        
        thread.start();
        thread1.start();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.isTerminated()
    }
}
