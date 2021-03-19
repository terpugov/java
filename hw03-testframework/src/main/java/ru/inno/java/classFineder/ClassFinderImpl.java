package ru.inno.java.classFineder;

import ru.inno.java.reflection.ReflectionUtils;

import java.util.List;

public class ClassFinderImpl implements FindClass {
    private String className;

    public ClassFinderImpl(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public List<Class<?>> findClasses() {
        return null;
    }

    @Override
    public Class<?> findClass() {
        return ReflectionUtils.getTestClass(className);

    }
}
