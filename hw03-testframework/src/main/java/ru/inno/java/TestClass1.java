package ru.inno.java;

import ru.inno.java.annatations.AfterEach;
import ru.inno.java.annatations.BeforeEach;
import ru.inno.java.annatations.Test;

public class TestClass1 {
    DIYArrayList<Integer> diyArrayList;
    private int counter = 0;
    private static int anInt = 0;

    public TestClass1() {
        counter++;
    }

    public TestClass1(DIYArrayList<Integer> diyArrayList, int counter) {
        this.diyArrayList = diyArrayList;
        this.counter = counter;
    }

    @BeforeEach
    public void setUp() throws Exception {
        diyArrayList = new DIYArrayList<>();
        diyArrayList.add(1);
        diyArrayList.add(2);
        diyArrayList.add(3);
    }

    @Test
    public void someTest1() throws Exception {
        Thread thread = Thread.currentThread();
        System.out.println("thread: " + thread.getName());
        anInt++;
        if(diyArrayList == null) {
            throw new Exception("Тест не прошел");
        }
        System.out.println("тест прошел");
    }

    @Test
    public void someTest2() throws Exception {
        Thread thread = Thread.currentThread();
        anInt++;
        if(diyArrayList == null) {
            throw new Exception("Тест не прошел");
        }
        System.out.println("тест прошел");
    }

    @Test
    public void someTest3() throws Exception {
        Thread thread = Thread.currentThread();
        System.out.println("thread: " + thread.getName());
        anInt++;
        if(diyArrayList == null) {
            throw new Exception("Тест не прошел");
        }
        System.out.println("тест прошел");
    }

    @Test
    public void someTest() throws Exception {
        throw new CustomException("Тест не прошел");
    }

    @AfterEach
    public void doNothing() throws Exception {
        System.out.println("");
    }
}
