package ru.inno.java.core;

import ru.inno.java.EventManager;
import ru.inno.java.Status;
import ru.inno.java.reflection.ReflectionUtils;
import ru.inno.java.annatations.AfterEach;
import ru.inno.java.annatations.BeforeEach;
import ru.inno.java.annatations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class TestRunner implements Runnable {
    private EventManager eventManager;
    private String className;

    public TestRunner() {
    }

    public TestRunner(EventManager eventManager, String className) {
        this.eventManager = eventManager;
        this.className = className;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void run() {
        Class<?> clazz = ReflectionUtils.getTestClass(className);
        TestSuitImpl testDecriptor = new TestSuitImpl();
//        testDecriptor.setClassName(clazz.getCanonicalName());
////        testDecriptor.setBeforeEach(Utils.findBeforeEachMethod(clazz));
////        testDecriptor.setAfterEach(Utils.findAfterEachMethod(clazz));
        testDecriptor.setTest(ReflectionUtils.findTestMethod(clazz));
        for (Method method: testDecriptor.getTest()) {
            Object object = ReflectionUtils.initTestClass(clazz);
            runBeforeEachMethod(object, clazz);
            runTestMethod(method, object, testDecriptor);
            runAfterEachMethod(object, clazz);
        }
        eventManager.notify("Report", testDecriptor);
    }

    private void runAfterEachMethod(Object object, Class<?> clazz) {
        Status status = Status.FAILED;
        try {
            runEachMethod(ReflectionUtils.findAfterEachMethod(clazz), object, AfterEach.name);
            status = Status.SUCCESS;
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to run AfterEach methods", e.getCause());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void runTestMethod(Method method, Object obj, TestSuitImpl testDecriptor) {
        Status status = null;
        try {
            runMethod(method, obj);
            status = Status.SUCCESS;
        } catch (InvocationTargetException e) {
            status = Status.FAILED;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException");
        }
        testDecriptor.setStatus(status);
        testDecriptor.setCurrentMethodName(method.getName());
        eventManager.notify(Test.name, testDecriptor);
    }

    private void runBeforeEachMethod(Object object, Class<?> clazz) {
        try {
            runEachMethod(ReflectionUtils.findBeforeEachMethod(clazz), object, BeforeEach.name);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to run BeforeEach methods", e.getCause());
        } catch (IllegalAccessException e) {
            System.out.println("");
        }
    }

    private Object initTestClass(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> constructor = Arrays.stream(constructors).filter(
                e -> e.getParameterCount() > 0 || Modifier.isPublic(e.getModifiers())
        ).findFirst().orElse(null);
        Object object = null;
        if (constructor == null) {
            System.err.println("TestedClass should have a default constructor");
            System.exit(-1);
        }
        try {
            object = constructor.newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        return object;
    }

    private void runMethod(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
    }

    private void runEachMethod(List<Method> methods, Object obj, String event) throws InvocationTargetException, IllegalAccessException {
        for (Method method: methods) {
            runMethod(method, obj);
        }
    }

}
