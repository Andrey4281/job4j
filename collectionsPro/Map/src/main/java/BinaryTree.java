import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {
    private static final class NodeTree<K, V> {
        K key;
        V data;
        NodeTree<K, V> leftChild;
        NodeTree<K, V> rightChild;

        public NodeTree(K key, V data) {
            this.key = key;
            this.data = data;
        }

        boolean isLeaf() {
            return ((leftChild == null) && (rightChild == null));
        }
    }

    private static final class TreeIterator<K extends Comparable<K>, V>
            implements Iterator<Pair<K, V>> {
        private BinaryTree<K, V> tree;
        private LinkedList<NodeTree<K, V>> queue;
        private int modCount = 0;

        public TreeIterator(BinaryTree<K, V> tree) {
            this.queue = new LinkedList<>();
            this.tree = tree;
            this.modCount = tree.modCount;
            if (tree.root != null) {
                queue.addLast(tree.root);
            }
        }
        @Override
        public boolean hasNext() {
            if (this.modCount != tree.modCount) {
                throw new ConcurrentModificationException();
            }
            return !queue.isEmpty();
        }

        @Override
        public Pair<K, V> next() {
            if (this.modCount != tree.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            NodeTree<K, V> res = queue.removeFirst();
            if (res.leftChild != null) {
                queue.addLast(res.leftChild);
            }
            if (res.rightChild != null) {
                queue.addLast(res.rightChild);
            }
            return new Pair<>(res.key, res.data);
        }
    }

    private NodeTree<K, V> root;
    private int modCount = 0;

    /**
     *
     * @param key
     * @param data
     * @return If this tree contains element with {@code key},
     * old data in the node of tree will be changed to {@code data},
     * and it will be returned false, else it will be addeded new node
     * to tree and it will be returned true
     */
    public boolean insert(K key, V data) {
        NodeTree current = root;
        NodeTree parent = root;
        boolean isLeftChild = false;
        boolean flag = true;
        while (current != null) {
            parent = current;
            if (key.compareTo((K) current.key) < 0) {
                current = current.leftChild;
                isLeftChild = true;
            } else if (key.compareTo((K) current.key) > 0) {
                current = current.rightChild;
                isLeftChild = false;
            } else {
                current.data = data;
                flag = false;
                break;
            }
        }
        if (flag) {
            NodeTree<K, V> newNode = new NodeTree(key, data);
            if (parent == null) {
                root = newNode;
            } else if (isLeftChild) {
                parent.leftChild = newNode;
            } else {
                parent.rightChild = newNode;
            }
        }
        modCount++;
        return flag;
    }

    public V find(K key) {
        NodeTree current = root;
        V res = null;
        while (current != null) {
            if (key.compareTo((K) current.key) < 0) {
                current = current.leftChild;
            } else if (key.compareTo((K) current.key) > 0) {
                current = current.rightChild;
            } else {
                res = (V) current.data;
                break;
            }
        }
        return res;
    }

    public boolean delete(K key) {
        NodeTree deletedNode = root;
        NodeTree parentDeletedNode = root;
        boolean isLeftChild = false;
        boolean result = true;
        while (deletedNode != null) {
            if (key.equals(deletedNode.key))  {
                break;
            } else {
                parentDeletedNode = deletedNode;
                if (key.compareTo((K) deletedNode.key) < 0) {
                    deletedNode = deletedNode.leftChild;
                    isLeftChild = true;
                } else {
                    deletedNode = deletedNode.rightChild;
                    isLeftChild = false;
                }
            }
        }
        if (deletedNode == null) {
            result = false;
        } else {
            modCount++;
            if (deletedNode.isLeaf()) {
                if (deletedNode == root) {
                    root = null;
                } else if (isLeftChild)  {
                    parentDeletedNode.leftChild = null;
                } else {
                    parentDeletedNode.rightChild = null;
                }
            } else if ((deletedNode.leftChild == null)
                    && (deletedNode.rightChild != null)) {
                if (deletedNode == root)  {
                    root = deletedNode.rightChild;
                } else {
                    if (isLeftChild)  {
                        parentDeletedNode.leftChild = deletedNode.rightChild;
                    } else {
                        parentDeletedNode.rightChild = deletedNode.rightChild;
                    }
                }
            } else if ((deletedNode.leftChild != null)
                    && (deletedNode.rightChild == null)) {
                if (deletedNode == root) {
                    root = deletedNode.leftChild;
                } else {
                    if (isLeftChild) {
                        parentDeletedNode.leftChild = deletedNode.leftChild;
                    } else {
                        parentDeletedNode.rightChild = deletedNode.leftChild;
                    }
                }
            } else {
                NodeTree receiver = findReceiver(deletedNode);
                receiver.leftChild = deletedNode.leftChild;
                receiver.rightChild = deletedNode.rightChild;
                if (deletedNode == root) {
                    root = receiver;
                } else if (isLeftChild) {
                    parentDeletedNode.leftChild = receiver;
                } else {
                    parentDeletedNode.rightChild = receiver;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new TreeIterator<>(this);
    }

    private NodeTree findReceiver(NodeTree node) {
        NodeTree current = node.rightChild;
        NodeTree receiver = node.rightChild;
        NodeTree parentReceiver = node.rightChild;
        while (current != null) {
            parentReceiver = receiver;
            receiver = current;
            current = current.leftChild;
        }

        if (receiver != node.rightChild) {
            parentReceiver.leftChild = receiver.rightChild;
        } else {
            node.rightChild = null;
        }
        return receiver;
    }
}
