package list;

import list.ArrayBasedContainer;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private ArrayBasedContainer<E> innerArray;
    private boolean isAddedNullValue;

    public SimpleSet(int capacity) {

        innerArray = new ArrayBasedContainer<>(capacity);
        isAddedNullValue = false;
    }

    public void add(E e) {
        if (!ifExistDublicates(e)) {
            innerArray.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return innerArray.iterator();
    }

    private boolean ifExistDublicates(E e) {
        boolean flag = false;
        if (e == null) {
            flag = ifExistNullValueInSet();
        } else {
            for (E currentElement : innerArray) {
                if (e.equals(currentElement)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private boolean ifExistNullValueInSet() {
        boolean flag = false;
        if (isAddedNullValue) {
            flag = true;
        } else {
            isAddedNullValue = true;
        }
        return flag;
    }
}
