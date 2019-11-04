package nonblockingcache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public Base delete(Base model) {
        return map.remove(model.getId());
    }

    public Base get(int key) {
        return map.get(key);
    }

    public void update(Base model) {
        Integer key = model.getId();
        map.computeIfPresent(key, (k, v)-> {
            if (map.get(key).getVersion() != model.getVersion()) {
            throw new OptimisticException("Object was modified!");
            }
            Base res = model;
            res.setVersion(res.getVersion() + 1);
            return res;});
    }
}
