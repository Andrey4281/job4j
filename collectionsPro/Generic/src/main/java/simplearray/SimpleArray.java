package simplearray;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private int maxSize;
    private int countOfElement;
    private long modCount;
    private final Object[] array;

    public SimpleArray(int maxSize) {
        this.maxSize = maxSize;
        this.array = new Object[maxSize + 1];
        this.countOfElement = 0;
        this.modCount = 0;
    }

    public void add(T model) {
        if (isFull()) {
            throw new DemensionOfExceedException();
        }
        array[countOfElement++] = model;
        modCount++;
    }

    public void set(int index, T model) {
        if (index >= countOfElement) {
            throw  new IndexOutOfBoundsException();
        }
        array[index] = model;
        modCount++;
    }

    public T get(int index) {
        if (index >= countOfElement) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void remove(int index) {
        if (index >= countOfElement) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index,
                countOfElement - index);
        countOfElement--;
        modCount++;
    }

    public int getCountOfElement() {
        return countOfElement;
    }

    public long getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>(this);
    }

    private boolean isFull() {
        return countOfElement == maxSize;
    }
}
