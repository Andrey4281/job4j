package threadpool;

public class PoolShutDownException extends RuntimeException {
    public PoolShutDownException(String message) {
        super(message);
    }
}
