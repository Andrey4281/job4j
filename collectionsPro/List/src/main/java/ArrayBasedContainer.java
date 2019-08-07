import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayBasedContainer<E> implements SimpleContainer<E> {
    private Object[] container;
    private int capacity = 10;
    private int countOfElement = 0;
    private int modCount = 0;

    public ArrayBasedContainer() {
        this.container = new Object[this.capacity];
    }

    public ArrayBasedContainer(int capacity) {
        this.capacity = capacity;
        this.container = new Object[this.capacity];
    }

    @Override
    public void add(E e) {
        if (countOfElement >= capacity) {
            expandContainer();
        }
        container[countOfElement++] = e;
        modCount++;
    }

    @Override
    public E get(int index) {
        if (index >= countOfElement) {
            throw new IndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayBasedContainerIterator<E>(this);
    }

    public int getCountOfElement() {
        return countOfElement;
    }

    public int getModCount() {
        return modCount;
    }

    public int getCapacity() {
        return capacity;
    }

    private void expandContainer() {
        capacity *= 2;
        Object[] newContainer = new Object[capacity];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }
}
