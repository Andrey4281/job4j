package threadsafelist;

import list.LinkedListBasedContainer;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeList<E> extends LinkedListBasedContainer<E> {
    private final LinkedListBasedContainer<E> list = new LinkedListBasedContainer<>();

    @Override
    public void add(E e) {
        synchronized (list) {
            list.add(e);
        }
    }

    @Override
    public E removeLast() {
        synchronized (list) {
            return list.removeLast();
        }
    }

    @Override
    public E get(int index) {
        synchronized (list) {
            return list.get(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        synchronized (list) {
            return copy().iterator();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (list) {
            return list.isEmpty();
        }
    }

    private LinkedListBasedContainer copy() {
        LinkedListBasedContainer res = new LinkedListBasedContainer();
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            res.add(iterator.next());
        }
        return res;
    }
}
