package threadpool;

import producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool implements ThreadPool {
    private static final class ThreadForSolveTask extends Thread {
        private final SimpleBlockingQueue<Runnable> queue;

        private ThreadForSolveTask(SimpleBlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    this.queue.poll().run();
                } catch (InterruptedException e) {
                    this.interrupt();
                }
            }
        }
    }


    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private volatile boolean shutDownFlag = false;

    public SimpleThreadPool(int sizeOfQueue) {
        tasks = new SimpleBlockingQueue<>(sizeOfQueue);
    }

    @Override
    public void inicialize() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread currentThread = new ThreadForSolveTask(this.tasks);
            threads.add(currentThread);
            currentThread.start();
        }
    }

    @Override
    public void work(Runnable job) {
        if (shutDownFlag) {
            throw new PoolShutDownException("Work of pool was stopped");
        }

        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        shutDownFlag = true;
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new SimpleThreadPool(2);
        pool.inicialize();
        pool.work(()->System.out.println("Hello world!"));
        pool.work(()->System.out.println("Hello Andrey!"));
        pool.work(()->System.out.println("Hello Petr!"));
        pool.shutdown();
        pool.work(()->System.out.println("Goodbuy"));
    }
}
