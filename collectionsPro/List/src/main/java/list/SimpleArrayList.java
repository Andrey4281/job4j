package list;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        E res;
        if (size == 0) {
            res = null;
        } else {
            res = first.data;
            first = first.next;
        }
        return res;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
