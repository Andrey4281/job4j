package producerconsumer;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenAddTwoElementByOfferMethodThenShouldGetThemByPollMethod() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> actualElements = new LinkedList<>();

        Thread consumer = new Thread() {
            @Override
            public void run() {
                try {
                    actualElements.add(queue.poll());
                    actualElements.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    queue.offer(1);
                    queue.offer(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        producer.start();
        producer.join();
        consumer.start();
        consumer.join();

        assertThat(actualElements).containsSequence(1, 2);
    }

}