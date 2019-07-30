import java.util.Iterator;
import java.util.NoSuchElementException;

/**Iterator for two-dimensional array
 * @author andreysemenov
 * @param <E>
 */
public class MatrixIterator<E> implements Iterator<E> {
    private final E[][] array;
    private int indexRow = 0;
    private int indexColumn = 0;

    public MatrixIterator(E[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return indexRow < array.length;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            E temp;
            if (indexColumn == (array[indexRow].length - 1)) {
                temp = array[indexRow++][indexColumn];
                indexColumn = 0;
            } else {
                temp = array[indexRow][indexColumn++];
            }
            return temp;
        }
    }
}
