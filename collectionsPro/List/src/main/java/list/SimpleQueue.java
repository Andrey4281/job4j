package list;

public class SimpleQueue<T> {
    private SimpleStack<T> stackForPushingElements;
    private SimpleStack<T> stackForPollintElements;

    public SimpleQueue() {
        stackForPushingElements = new SimpleStack<>();
        stackForPollintElements = new SimpleStack<>();
    }

    public void push(T value) {
        stackForPushingElements.push(value);
    }

    public T poll() {
        if (stackForPollintElements.isEmpty()) {
            moveAllElementFromOneStackToAnother();
        }
        return stackForPollintElements.poll();
    }

    private void moveAllElementFromOneStackToAnother() {
        while (!stackForPushingElements.isEmpty()) {
            T temp = stackForPushingElements.poll();
            stackForPollintElements.push(temp);
        }
    }
}
