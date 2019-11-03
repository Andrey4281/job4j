package producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    @GuardedBy("this")
    private final int maxSizeOfQueue;

    public SimpleBlockingQueue(int maxSizeOfQueue) {
        this.maxSizeOfQueue = maxSizeOfQueue;
    }

    public void offer(T value) {
        synchronized (this) {
            while (this.queue.size() >= maxSizeOfQueue) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.queue.offer(value);
            this.notify();
        }
    }

    public T poll() {
        T res = null;
        synchronized (this) {
            while (this.queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res = this.queue.poll();
            this.notify();
        }
        return res;
    }
}
