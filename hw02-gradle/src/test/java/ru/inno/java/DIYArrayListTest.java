package ru.inno.java;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;


@DisplayName("List должен:")
class DIYArrayListTest {

    private static int INITIAL_CAPACITY = 16;
    static int counter = 0;
    static int berforeAll = 0;
    private int some = 0;

    public DIYArrayListTest(int some) {
        counter++;
        System.out.println("конструктор");
    }


    @BeforeAll
    public static void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field field = DIYArrayList.class.getDeclaredField("INITIAL_CAPACITY");
        field.setAccessible(true);
        DIYArrayList diyArrayList = new DIYArrayList();
        Object value = field.get(diyArrayList);
        System.out.println(value);
        INITIAL_CAPACITY = (int) value;
        berforeAll++;
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }

    @AfterEach
    public void afterEcach(){
        System.out.println("afterEach");
    }

    @DisplayName("поддерживать Collections.addAll()")
    @Test
    public void collectionsAddAll() throws Exception {
        DIYArrayList<Integer> diyArrayList = new DIYArrayList<>();
        Collections.addAll(diyArrayList, 0, 1, 2, 3, 4, 5);

        for (int i = 0; i < diyArrayList.size(); i++) {
            assertEquals(i, diyArrayList.get(i));
        }
        throw new Exception("тест не прошел");
    }

    @DisplayName("поддерживать Collections.sort()")
    @Test
    public void collectionsSort() {
        DIYArrayList<Integer> dayArrayListDescending = getDiyArrayListFromSizeTo0(20);
        DIYArrayList<Integer> diyArrayListAscending = getDiyArrayListFromSizeTo0(20);
        Collections.sort(dayArrayListDescending);
        for (int i = 0; i < dayArrayListDescending.size(); i++) {
            assertEquals(i, dayArrayListDescending.get(i));
        }
        for (int i = 0; i < diyArrayListAscending.size(); i++) {
            assertNotEquals(i, diyArrayListAscending.get(i));
        }
    }

    @DisplayName("поддерживать Collections.copy()")
    @Test
    public void collectionsCopy() {
        DIYArrayList<Integer> diyArrayList = getDiyArrayListFromSizeTo0(20);
        Collections.copy(diyArrayList, getFilledListOfSizeFrom0ToSize(10));
        for (int i = 0; i < 10; i++) {
            assertEquals(i, diyArrayList.get(i));
        }
        for (int i = 11; i < diyArrayList.size(); i++) {
            assertNotEquals(i, diyArrayList.get(i));
        }
        assertEquals(20, diyArrayList.size());
    }

    private DIYArrayList<Integer> getDiyArrayListFromSizeTo0(int size) {
        DIYArrayList<Integer> diyArrayList = new DIYArrayList<>();
        diyArrayList.addAll(getFilledListOfSizeFrom0ToSize(size));
        diyArrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        return diyArrayList;
    }

    @DisplayName("добавлять элементы list.add()")
    @Test
    public void add() {
        List<Integer> diyArrayList = new DIYArrayList<>();

        diyArrayList.add(10);
        diyArrayList.add(11);

        assertEquals(diyArrayList.get(0), 10);
        assertEquals(diyArrayList.get(1), 11);
    }

    @DisplayName("конвертиться в Array")
    @Test()
    public void toArray() {
        DIYArrayList<String> diyArrayList = new DIYArrayList<>();
        diyArrayList.add("G");
        diyArrayList.add("E");
        diyArrayList.add("A");
        diyArrayList.add("O");
        Object[] array = diyArrayList.toArray();
        for (int i = 0; i < array.length; i++) {
            assertEquals(diyArrayList.get(i), array[i]);
        }
    }

    @DisplayName("добавлять коллекцию элементов list.addAll() и расширяться при заполнености CURRENT_CAPACITY - 1")
    @Test
    public void addAll() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        List<Integer> list = getFilledListOfSizeFrom0ToSize(INITIAL_CAPACITY);
        diyArrayList.addAll(list);
        diyArrayList.add(2);
        assertEquals(17, diyArrayList.size());
    }

    @DisplayName("удалять элементы из коллекции list.remove() и возвращать удаленный")
    @Test
    public void remove() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        List<Integer> list = getFilledListOfSizeFrom0ToSize(INITIAL_CAPACITY);
        diyArrayList.addAll(list);
        assertEquals(2, diyArrayList.remove(2));
        assertEquals(15, diyArrayList.size());
    }

    @DisplayName("реализовывать и возвращать listIterator")
    @Test
    public void iterator() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        List<Integer> list = getFilledListOfSizeFrom0ToSize(INITIAL_CAPACITY);
        diyArrayList.addAll(list);
        ListIterator<Integer> listIterator = diyArrayList.listIterator();
        int counter = 0;
        while (listIterator.hasNext()) {
            assertEquals(counter++, listIterator.next());
        }
        counter = 15;
        while (listIterator.hasPrevious()) {
            assertEquals(--counter, listIterator.previous());
        }
    }

    @DisplayName("DiyArrayList.ListIterator должен падать с IllegalStateException при попытки вызвать remove без вызова next() or previous()")
    @Test
    public void listIteratorRemoveTwice() {
        List<Integer> diyArrayList = new DIYArrayList<>();
        List<Integer> list = getFilledListOfSizeFrom0ToSize(INITIAL_CAPACITY);
        diyArrayList.addAll(list);
        ListIterator<Integer> listIterator = diyArrayList.listIterator();
        assertThrows(IllegalStateException.class, () ->
                listIterator.remove());
        listIterator.next();
        listIterator.remove();
        assertThrows(IllegalStateException.class, () ->
                listIterator.remove());

    }

    private <T> List<T> getFilledListOfSizeFrom0ToSize(int size) {
        Object[] arr = new Object[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        List<T> listOfInt = (List<T>) Arrays.asList(arr);
        return listOfInt;
    }


    @DisplayName("удаление элемента из итератора уменшьшает размер коллекции")
    @Test
    public void iteratorRemove() {
        List<Integer> list = getDiyArrayListFromSizeTo0(16);
        ListIterator<Integer> listIterator = list.listIterator();
        listIterator.next();
        listIterator.remove();
        assertEquals(15, list.size());
    }

    @DisplayName("добавление элементов в итератор увеличивает размер коллекции")
    @Test
    public void iteratorAdd() {
        List<Integer> list = getDiyArrayListFromSizeTo0(16);
        ListIterator<Integer> listIterator = list.listIterator();
        listIterator.next();
        listIterator.add(1);
        System.out.println("INIT" + INITIAL_CAPACITY);
        assertEquals(17, list.size());
    }

    @DisplayName("неподдерживаемые операции должны выкидывать UnsupportedOperationException")
    @Test
    public void unsaported() {
        Integer[] arrayOfInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        DIYArrayList<Integer> diyArrayList = new DIYArrayList<>();
        List<Integer> list = getFilledListOfSizeFrom0ToSize(INITIAL_CAPACITY);
        diyArrayList.addAll(list);
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.contains(new Object()));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.toArray(arrayOfInt));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.remove(new Object()));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.containsAll(diyArrayList));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.addAll(1, diyArrayList));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.removeAll(diyArrayList));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.retainAll(diyArrayList));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.clear());
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.add(1, 2));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.indexOf(1));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.lastIndexOf(1));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.subList(1, 2));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.listIterator(1));
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.iterator().next());
        assertThrows(UnsupportedOperationException.class, () ->
                diyArrayList.iterator().hasNext());
    }

    @AfterAll
    public static void test() {
        System.out.println(berforeAll);
        System.out.println(counter);
    }
}