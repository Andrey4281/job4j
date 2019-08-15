import java.util.List;
import java.util.Map;

public interface Script {
    List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId);
}
