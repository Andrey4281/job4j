package cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

public class CacheDemonstration {

    public static void main(String[] args) {
        Cache<String, String> cache = new Cache<>(2);
        Function<String, String> loadBehaviour = key->{
            String data = null;
            try (Scanner in = new Scanner(new FileInputStream(key))) {
                data = in.useDelimiter("\\Z").next();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        };
        cache.setLoadBehaviour(loadBehaviour);

        System.out.println(cache.getValueFromCacheByKey("Names.txt"));
        System.out.println(cache.getValueFromCacheByKey("Names.txt"));
        System.out.println(cache.getValueFromCacheByKey("Address.txt"));
    }
}
