package stories;

import simplearray.SimpleArray;

public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    public AbstractStore(int sizeOfStore) {
        this.store = new SimpleArray<>(sizeOfStore);
    }

    @Override
    public void add(T model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = findIndexById(id);
        boolean res = false;
        if (index != -1) {
            store.set(index, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        boolean res = false;
        if (index != -1) {
            store.remove(index);
            res = true;
        }
        return res;
    }

    @Override
    public T findById(String id) {
        T res;
        int index = findIndexById(id);
        if (index == -1) {
            res = null;
        } else {
            res = this.store.get(index);
        }
        return res;
    }

    private int findIndexById(String id) {
        int index = -1, i = 0;
        for (T el: store) {
            if (el.getId().equals(id)) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }
}
