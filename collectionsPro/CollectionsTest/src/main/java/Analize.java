import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Analize {
    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        if (previous != null && current != null) {
            Map<Integer, User> mapForCurrent = saveCurrentToMap(current);

            for (User userFromPrevious:
                 previous) {
                User userFromCurrent =
                        mapForCurrent.get(userFromPrevious.id);
                if (userFromCurrent == null) {
                    result.deleted++;
                } else if (!userFromPrevious.name.equals(userFromCurrent.name)) {
                    result.changed++;
                }
            }
            result.added = current.size() - (previous.size() - result.deleted);
        }
        return result;
    }

    private Map<Integer, User> saveCurrentToMap(List<User> current) {
        Map<Integer, User> mapForCurrent =
                new HashMap<>(current.size() + 1, 1);

        for (User user:
                current) {
            mapForCurrent.put(user.id, user);
        }

        return mapForCurrent;
    }
}