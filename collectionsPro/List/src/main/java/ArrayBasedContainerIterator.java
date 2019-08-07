import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBasedContainerIterator<T> implements Iterator<T> {
    private final ArrayBasedContainer<T> container;
    private int index = 0;
    private int modCount;

    public ArrayBasedContainerIterator(ArrayBasedContainer<T> container) {
        this.container = container;
        this.modCount = container.getModCount();
    }

    @Override
    public boolean hasNext() {
        if (modCount != container.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return index < container.getCountOfElement();
    }

    @Override
    public T next() {
        if (modCount != container.getModCount()) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return container.get(index++);
        }
    }
}
