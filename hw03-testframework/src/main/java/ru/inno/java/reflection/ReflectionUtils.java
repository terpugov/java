package ru.inno.java.reflection;

import ru.inno.java.annatations.AfterEach;
import ru.inno.java.annatations.BeforeEach;
import ru.inno.java.annatations.Test;
import ru.inno.java.core.TestMethodImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {
    public static Object initTestClass(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        Constructor<?> constructor = Arrays.stream(constructors).filter(
                e -> e.getParameterCount() > 0 || Modifier.isPublic(e.getModifiers())
        ).findFirst().orElse(null);
        Object object = null;
        if (constructor == null) {
            System.err.println("TestedClass should have a default constructor");
            throw new RuntimeException("TestedClass should have a default constructor");
        }
        try {
            object = constructor.newInstance();
        } catch (InstantiationException ex) {
            System.err.println("Failed to create instance of Class");
            throw new RuntimeException("Failed to create instance of Class", ex.getCause());
        } catch (IllegalAccessException ex) {
            System.err.println("IllegalAccessException");
            throw new RuntimeException("IllegalAccessException", ex.getCause());
        } catch (InvocationTargetException ex) {
            System.err.println("InvocationTargetException");
            throw new RuntimeException("InvocationTargetException", ex.getCause());
        }
        return object;
    }

    public static Class<?> getTestClass(String className) {
        Class<?> testClazz = null;
        try {
            testClazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.err.println("Class with given name not found");
            throw new RuntimeException("ClassNotFoundException", e.getCause());
        }
        return testClazz;
    }

    public static List<Method> findBeforeEachMethod(Class<?> testingClazz) {
        return findAnnotaitedMethod(testingClazz, BeforeEach.class);
    }

    public static List<Method> findTestMethod(Class<?> testingClazz) {
        List<Method> methods = findAnnotaitedMethod(testingClazz, Test.class);
        List<TestMethodImpl> methodList = methods.stream().map(m -> new TestMethodImpl()).collect(Collectors.toList());
        methodList.forEach(e -> System.out.println(e.getClass()));

        return methods;
    }

    public static List<Method> findAfterEachMethod(Class<?> testingClazz) {
        return findAnnotaitedMethod(testingClazz, AfterEach.class);
    }

    public static List<Method> findAnnotaitedMethod(Class<?> testingClazz, Class<? extends Annotation> annotationClazz) {
        Method[] method = testingClazz.getMethods();
        return Arrays.stream(method).filter(e -> e.isAnnotationPresent(annotationClazz)).collect(Collectors.toList());
    }
}
