import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private static final class TreeIterator<E extends Comparable<E>>
            implements Iterator<E> {
        private Tree<E> tree;
        private Queue<Node<E>> queue = new LinkedList<>();
        private int countOfModifications;

        public TreeIterator(Tree<E> tree) {
            this.tree = tree;
            this.countOfModifications = tree.countOfModifications;
            this.queue.add(this.tree.root);
        }

        @Override
        public boolean hasNext() {
            if (this.countOfModifications != this.tree.countOfModifications) {
                throw new ConcurrentModificationException();
            }
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            if (this.countOfModifications != this.tree.countOfModifications) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> res = queue.poll();
            for (Node<E> leaf: res.leaves()) {
                queue.offer(leaf);
            }
            return res.getValue();
        }
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    private Node<E> root;
    private int countOfModifications = 0;

    public Tree(E e) {
        this.root = new Node<>(e);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean temp = false;
        Optional<Node<E>> rsl = findBy(parent);
        if (!rsl.isEmpty() && !rsl.get().leaves().contains(child)) {
            temp = true;
            rsl.get().add(new Node<>(child));
            countOfModifications++;
        }
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(this);
    }
}
