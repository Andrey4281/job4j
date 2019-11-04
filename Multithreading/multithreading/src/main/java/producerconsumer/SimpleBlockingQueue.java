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

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() >= maxSizeOfQueue) {
                this.wait();
            }
            this.queue.offer(value);
            this.notify();
        }
    }

    public T poll() throws InterruptedException {
        T res = null;
        synchronized (this) {
            while (this.queue.isEmpty()) {
                this.wait();
            }
            res = this.queue.poll();
            this.notify();
        }
        return res;
    }

    public boolean isEmpty() {
        synchronized (this) {
            return this.queue.isEmpty();
        }
    }
}
