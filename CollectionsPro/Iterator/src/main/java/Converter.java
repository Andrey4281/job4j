import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {
                boolean res = true;
                while (it.hasNext() && !current.hasNext()) {
                    current = it.next();
                }
                if (!it.hasNext() && !current.hasNext()) {
                    res = false;
                }
                return res;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}

