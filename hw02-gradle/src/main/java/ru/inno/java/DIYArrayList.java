package ru.inno.java;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private final int INITIAL_CAPACITY = 16;
    private int initialCapacity = INITIAL_CAPACITY;
    private int size = 0;
    private int thresholdCapacity = initialCapacity - 1;

    public DIYArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }

    public DIYArrayList(E[] array) {
        size = array.length;
        this.array = array;
    }
    public void setArray(Object[] array) {
        this.array = array;
    }

    private Object[] array;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException();
            }

            @Override
            public E next() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        checkTreshHold();
        if (array.length > size) {
            array[size++] = e;
            return true;
        }
        return false;
    }

    private void checkTreshHold() {
        if(size >= thresholdCapacity) {
            initialCapacity = initialCapacity + initialCapacity /2;
            thresholdCapacity = initialCapacity - 1;
            Object[] increaseCapacityArray = new Object[initialCapacity];

            System.arraycopy(array, 0, increaseCapacityArray, 0, size);
            array = increaseCapacityArray;
        }
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        Object [] cArray = c.toArray();
        for (int i = 0; i < cArray.length; i++) {
            checkTreshHold();
            array[size++] = cArray[i];
            result = true;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        Object [] elementsAfterCursor = Arrays.copyOfRange(array ,index + 1, size-- + 1);

        for(int i = 0; i < elementsAfterCursor.length; i++) {
            array[index + i] = elementsAfterCursor[i];
        }
        return element;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            private boolean isModified = true;
            private int cursor = -1;
            @Override
            public boolean hasNext() {
                return size() - 1 != cursor;
            }

            @Override
            public E next() {
                isModified = false;
                return (E) array[++cursor];
            }

            @Override
            public boolean hasPrevious() {
                return cursor > 0;
            }

            @Override
            public E previous() {
                isModified = false;
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return (E) array[--cursor];
            }

            @Override
            public int nextIndex() {
                return array.length < cursor ? ++cursor : cursor;
            }

            @Override
            public int previousIndex() {
                return cursor > 0 ? --cursor : -1;
            }

            @Override
            public void remove() {
                if (isModified) {
                    throw new IllegalStateException();
                }
                isModified = true;
                Object [] elementsAfterCursor = Arrays.copyOfRange(array ,cursor + 1, array.length);

                for(int i = 0; i < elementsAfterCursor.length; i++) {
                    array[cursor + i] = elementsAfterCursor[i];
                }
                cursor--;
                size--;
            }

            @Override
            public void set(E e) {
                array[cursor] = e;
            }

            @Override
            public void add(E e) {
                array[cursor++] = e;
                size++;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
