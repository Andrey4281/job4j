package counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountWithVolatileVariable implements Count {
    @GuardedBy("this")
    private volatile int value;

    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public int get() {
        return value;
    }
}
