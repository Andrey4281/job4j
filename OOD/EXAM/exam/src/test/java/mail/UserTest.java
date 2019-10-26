package mail;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void whenCallMergeUsersForTwoDifferentUsersShouldGetUnitedUser() {
        Set<String> emailOne = new HashSet<>();
        emailOne.add("a");
        emailOne.add("b");
        User userOne = new User("user1", emailOne);
        Set<String> emailTwo = new HashSet<>();
        emailOne.add("b");
        emailOne.add("d");
        User userTwo = new User("user2", emailTwo);

        User result = userOne.mergeUsers(userTwo);

        assertThat(result.getName()).isEqualTo("user1");
        assertThat(result.getEmails()).containsOnly("a", "b", "d");
    }

    @Test
    public void whenCallMergeUsersForSameUserShouldGetTheSameUser() {
        Set<String> email = new HashSet<>();
        email.add("a");
        User user = new User("user", email);
        User theSameUser = user;

        User result = user.mergeUsers(theSameUser);

        assertThat(result.getName()).isEqualTo("user");
        assertThat(result.getEmails()).containsOnly("a");
    }
}