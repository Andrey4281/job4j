package mail;

import java.util.*;

public abstract class AbstractMailServiceBFS implements MailService {
    protected final Console console;
    protected Map<String, User> mapUserNameToUser;
    protected Map<String, Set<String>> mapEmailToUserName;

    protected AbstractMailServiceBFS(Console console) {
        this.console = console;
    }

    @Override
    public List<User> calculate() {
        Map<String, User> mapUserNameToUserDuplicate = new HashMap<>(mapUserNameToUser);
        List<User> res = new LinkedList<>();
        while (!mapUserNameToUserDuplicate.isEmpty()) {
            User currentUser = mapUserNameToUserDuplicate.entrySet().iterator().next().getValue();
            res.add(bfs(currentUser, mapUserNameToUserDuplicate));
        }
        return res;
    }


    private User bfs(User start, Map<String, User> map) {
        User result = start;
        LinkedList<User> queue = new LinkedList<>();
        queue.addLast(start);
        Set<String> isVisited = new HashSet<>();
        isVisited.add(start.getName());
        while (!queue.isEmpty()) {
            User currentVertex = queue.removeFirst();
            map.remove(currentVertex.getName());
            result = result.mergeUsers(currentVertex);
            for (String email: currentVertex.getEmails()) {
                for (String connectedUser: mapEmailToUserName.get(email)) {
                    if (!isVisited.contains(connectedUser)) {
                        queue.addLast(map.get(connectedUser));
                        isVisited.add(connectedUser);
                    }
                }
            }
        }
        return result;
    }

}
