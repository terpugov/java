package ru.inno.java;

import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        List<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.add(1);
        diyArrayList.add(2);
        ListIterator<Integer> listIterator = diyArrayList.listIterator();
        listIterator.next();
        listIterator.remove();

        System.out.println(diyArrayList.size());

    }
}
