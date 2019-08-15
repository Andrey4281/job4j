import java.util.*;

public class ScriptStackImpl implements Script {
    private static final class NodeOfTree {
        private Integer root;
        private Iterator<Integer> childs;

        public NodeOfTree(Integer root, Iterator<Integer> childs) {
            this.root = root;
            this.childs = childs;
        }
    }

    @Override
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        LinkedList<NodeOfTree> stack = new LinkedList<>();
        LinkedHashSet<Integer> res = new LinkedHashSet<>();
        stack.addFirst(new NodeOfTree(scriptId, ds.get(scriptId).iterator()));
        while (!stack.isEmpty()) {
            if (stack.peek().childs.hasNext()) {
                Integer currentScript = stack.peek().childs.next();
                stack.addFirst(new NodeOfTree(currentScript, ds.get(currentScript).iterator()));
            } else {
                res.add(stack.removeFirst().root);
            }
        }
        return new LinkedList<>(res);
    }
}
