package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListBasedContainer<E> implements SimpleContainer<E> {
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    private static class LinkedListBasedContainerIterator<E>
            implements Iterator<E> {
        private LinkedListBasedContainer<E> list;
        private Node<E> currentPointer;
        private int modeCount;

        public LinkedListBasedContainerIterator(LinkedListBasedContainer<E> list) {
            this.list = list;
            this.currentPointer = list.first;
            this.modeCount = list.modCount;
        }
        @Override
        public boolean hasNext() {
            if (this.modeCount != list.modCount) {
                throw new ConcurrentModificationException();
            }
            return this.currentPointer != null;
        }

        @Override
        public E next() {
            if (this.modeCount != list.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                E res = currentPointer.data;
                currentPointer = currentPointer.next;
                return res;
            }
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int countOfElement = 0;
    private int modCount = 0;

    @Override
    public void add(E e) {
        Node<E> temp = new Node<>(e);
        if (countOfElement == 0) {
            first = temp;
            last = first;
        } else {
            last.next = temp;
            temp.prev = last;
            last = temp;
        }
        countOfElement++;
        modCount++;
    }

    public E removeLast() {
        E res;
        if (countOfElement == 0) {
            res = null;
        } else {
            res = last.data;
            if (countOfElement == 1) {
                first = null;
                last = null;
            } else {
                last.prev.next = null;
                last = last.prev;
            }
            countOfElement--;
            modCount++;
        }
        return res;
    }

    @Override
    public E get(int index) {
        if (index >= countOfElement) {
            throw new IndexOutOfBoundsException();
        } else {
            int distanceFromFirst = index;
            int distanceFromLast = countOfElement - index - 1;
            return findNeededElement(distanceFromFirst, distanceFromLast);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListBasedContainerIterator<>(this);
    }

    public boolean isEmpty() {
        return countOfElement == 0;
    }

    private E moveFromLastElementToNPositionAndReturnData(int numberOfPosition) {
        Node<E> currentNode = last;
        for (int i = numberOfPosition; i > 0; i--) {
            currentNode = currentNode.prev;
        }
        return currentNode.data;
    }

    private E moveFromFirstElementToNPositionAndReturnData(int numberOfPosition) {
        Node<E> currentNode = first;
        for (int i = 0; i < numberOfPosition; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    private E findNeededElement(int distanceFromFirst, int distanceFromLast) {
        E res;
        if (distanceFromFirst < distanceFromLast) {
            res = moveFromFirstElementToNPositionAndReturnData(distanceFromFirst);
        } else {
            res = moveFromLastElementToNPositionAndReturnData(distanceFromLast);
        }
        return res;
    }
}
