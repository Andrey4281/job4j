package stories;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore users;
    private RoleStore roles;

    @Before
    public void setUsersAndRoles() {
        users = new UserStore(3);
        roles = new RoleStore(3);
    }

    @Test
    public void sequentionalOfAddMethodInvocation() {
        users.add(new User("Andrey"));
        users.add(new User("Peter"));
        users.add(new User("Andrei"));

        assertThat(users.findById("Andrey").getId(), is("Andrey"));
        assertThat(users.findById("Peter").getId(), is("Peter"));
        assertThat(users.findById("Andrei").getId(), is("Andrei"));
    }

    @Test
    public void replaceMethodInvocation() {
        roles.add(new Role("Admin"));
        roles.replace("Admin", new Role("Mentor"));

        assertThat(roles.findById("Mentor").getId(), is("Mentor"));
    }

    @Test
    public void deleteMethodInvocation() {
        roles.add(new Role("Admin"));
        roles.delete("Admin");

        assertEquals(roles.findById("Admin"), null);
    }

}