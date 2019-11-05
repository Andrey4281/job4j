package counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountImpl implements Count {
    @GuardedBy("this")
    private int value;

    @Override
    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    @Override
    public int get() {
        synchronized (this) {
            return this.value;
        }
    }
}

