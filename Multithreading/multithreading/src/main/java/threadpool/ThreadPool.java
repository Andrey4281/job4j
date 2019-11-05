package threadpool;

public interface ThreadPool {
    void inicialize();
    void work(Runnable job);
    void shutdown();
}
