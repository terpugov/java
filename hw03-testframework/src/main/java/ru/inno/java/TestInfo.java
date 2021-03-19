package ru.inno.java;

import ru.inno.java.reflection.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class TestInfo {
    private String className;
    private Class<?> clazz;
    private List<Method> methods;
    private Object instance;


    public TestInfo(String className) {
        this.className = className;
        this.clazz = ReflectionUtils.getTestClass(className);
        this.instance = ReflectionUtils.initTestClass(clazz);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

}
