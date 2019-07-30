package simplearray;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<E> implements Iterator<E> {
    private final SimpleArray<E> array;
    private int index = 0;
    private long currentModCount = 0;

    public SimpleArrayIterator(SimpleArray<E> array) {
        this.array = array;
        this.currentModCount = array.getModCount();
    }

    @Override
    public boolean hasNext() {
        if (currentModCount != array.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return index < array.getCountOfElement();
    }

    @Override
    public E next() {
        if (currentModCount != array.getModCount()) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array.get(index++);
    }
}