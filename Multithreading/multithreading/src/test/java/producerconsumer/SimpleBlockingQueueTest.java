package producerconsumer;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(6);
        List<Integer> actualElements = new LinkedList<>();

        Thread producer = new Thread(()->
        {
            IntStream.range(0, 5).forEach(value -> {
                try {
                    queue.offer(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        Thread consumer = new Thread(()->{
        while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
            try {
                actualElements.add(queue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();

        assertThat(actualElements).containsSequence(0, 1, 2, 3, 4);
    }

}