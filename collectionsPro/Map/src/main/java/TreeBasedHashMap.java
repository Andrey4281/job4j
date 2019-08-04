import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeBasedHashMap<K extends Comparable<K>, V> implements SimpleHashMap<K, V> {
    private static final class
    TreeBasedHashMapIterator<K extends Comparable<K>, V>
            implements Iterator<Pair<K, V>> {
        private TreeBasedHashMap<K, V> hashMap;
        private int indexCurrent = 0;
        private Iterator<Pair<K, V>> currentIterator;
        private int modCount = 0;

        public TreeBasedHashMapIterator(TreeBasedHashMap<K, V> hashMap) {
            this.hashMap = hashMap;
            this.currentIterator = hashMap.table[indexCurrent].iterator();
            this.modCount = hashMap.modCount;
        }

        @Override
        public boolean hasNext() {
            if (this.modCount != hashMap.modCount) {
                throw new ConcurrentModificationException();
            }
            boolean res = true;
            while (indexCurrent < hashMap.table.length - 1
                    && !currentIterator.hasNext()) {
                currentIterator = hashMap.table[++indexCurrent].iterator();
            }
            if (indexCurrent >= hashMap.table.length - 1
                    && !currentIterator.hasNext()) {
                res = false;
            }
            return res;
        }

        @Override
        public Pair<K, V> next() {
            if (this.modCount != hashMap.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentIterator.next();
        }
    }

    private BinaryTree<K, V>[] table;
    private int modCount = 0;
    private int fillFactor = 0;
    private int countOfElement;

    public TreeBasedHashMap() {
        this.table = createNewTable(10);
    }

    public TreeBasedHashMap(int size) {
        this.table = createNewTable(size);
    }

    @Override
    public boolean insert(K key, V value) {
        if (fillFactor >= 2) {
            resizeTable();
        }
        modCount++;
        boolean temp = table[hash(key, table.length)].insert(key, value);
        if (temp) {
            countOfElement++;
            fillFactor = countOfElement / table.length;
        }
        return temp;
    }

    @Override
    public V get(K key) {
        return table[hash(key, table.length)].find(key);
    }

    @Override
    public boolean delete(K key) {
        boolean temp = table[hash(key, table.length)].delete(key);
        if (temp) {
            modCount++;
            countOfElement--;
            fillFactor = countOfElement / table.length;
        }
        return temp;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new TreeBasedHashMapIterator<>(this);
    }

    private int hash(K key, int lenght) {
        return key.hashCode() % lenght;
    }

    private BinaryTree<K, V>[] createNewTable(int size) {
        BinaryTree<K, V>[] tableNew = new BinaryTree[size];
        for (int i = 0; i < size; i++) {
            tableNew[i] = new BinaryTree<>();
        }
        return tableNew;
    }

    private void resizeTable() {
        BinaryTree<K, V>[] resizedTable =
                createNewTable(this.table.length * 2);

        Iterator<Pair<K, V>> iterator = this.iterator();

        while (iterator.hasNext()) {
            Pair<K, V> pair = iterator.next();
            resizedTable[hash(pair.getKey(),
                    resizedTable.length)].insert(pair.getKey(),
                    pair.getValue());
        }

        this.table = resizedTable;
        this.fillFactor = countOfElement / this.table.length;
        this.modCount++;
    }
}
