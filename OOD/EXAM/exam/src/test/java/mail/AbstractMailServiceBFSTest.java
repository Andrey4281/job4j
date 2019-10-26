package mail;

import org.junit.Test;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AbstractMailServiceBFSTest {

    @Test
    public void whenCallCalculateMethodShouldGetUnitedUsers() {
        AbstractMailServiceBFS mailServiceBFS = new MailServiceBFSConsole(null);
        Map<String, Set<String>> mapEmailToUserName = new HashMap<>();
        mapEmailToUserName.put("a",new HashSet<>(Arrays.asList(new String[]{"user1"})));
        mapEmailToUserName.put("b",new HashSet<>(Arrays.asList(new String[]{"user1"})));
        mapEmailToUserName.put("c",new HashSet<>(Arrays.asList(new String[]{"user1", "user2"})));
        mapEmailToUserName.put("e",new HashSet<>(Arrays.asList(new String[]{"user2"})));
        mapEmailToUserName.put("d",new HashSet<>(Arrays.asList(new String[]{"user3"})));
        mailServiceBFS.mapEmailToUserName = mapEmailToUserName;
        Map<String, User> mapUserNameToUser = new HashMap<>();
        mapUserNameToUser.put("user1", new User("user1", new HashSet<>(Arrays.asList(new String[]{"a", "b", "c"}))));
        mapUserNameToUser.put("user2", new User("user2", new HashSet<>(Arrays.asList(new String[]{"c", "e"}))));
        mapUserNameToUser.put("user3", new User("user3", new HashSet<>(Arrays.asList(new String[]{"d"}))));
        mailServiceBFS.mapUserNameToUser = mapUserNameToUser;

        Iterator<User> iterator = mailServiceBFS.calculate().iterator();

        assertThat(iterator.next().getEmails()).containsOnly("a", "b", "c", "e");
        assertThat(iterator.next().getEmails()).containsOnly("d");
        assertThat(iterator.hasNext()).isEqualTo(false);
    }
}