package nonblockingcache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NonBlockingCacheTest {

    @Test
    public void whenAddObjectToCacheThenShouldGetIt() {
        NonBlockingCache cache = new NonBlockingCache();
        Base one = new Base(1);

        cache.add(one);

        assertThat(one).isEqualTo(cache.get(1));
    }

    @Test
    public void whenRemoveObjectFromCacheThenShouldLoseIt() {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(1));

        cache.delete(new Base(1));

        assertThat(cache.get(1)).isNull();
    }

    @Test
    public void whenUpdateObjectInCacheThenShouldGetFromCacheUpdatedValue() {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(1));
        Base expectedObject = new Base(1);
        expectedObject.setVersion(expectedObject.getVersion() + 1);

        cache.update(new Base(1));

        assertThat(cache.get(1)).isEqualTo(expectedObject);
    }

    @Test
    public void whenUseTwoThreadToUpdateSameObjectInCacheShouldThrownOptimisticException() throws InterruptedException {
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(new Base(1));
        AtomicReference<Exception> ex = new AtomicReference<>();

        Thread threadOne = new Thread(()-> {
            try {
                cache.update(new Base(1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });

        Thread threadTwo = new Thread(()-> {
            try {
                cache.update(new Base(1));
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });

        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        assertThat(ex.get()).isInstanceOf(OptimisticException.class);
        assertThat(ex.get()).hasMessage("Object was modified!");
    }
}