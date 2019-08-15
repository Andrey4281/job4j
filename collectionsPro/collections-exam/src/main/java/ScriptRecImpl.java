import java.util.*;

public class ScriptRecImpl implements Script{
    @Override
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        LinkedHashSet<Integer> res = new LinkedHashSet<>();
        loadRec(ds, scriptId, res);
        return new LinkedList<>(res);
    }
    
    private void loadRec(Map<Integer, List<Integer>> ds, Integer scriptId, Set<Integer> res) {
        List<Integer> currentList = ds.get(scriptId);
        for (Integer el: currentList) {
            loadRec(ds, el, res);
        }
        res.add(scriptId);
    }
}
