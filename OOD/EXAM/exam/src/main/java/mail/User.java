package mail;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Set<String> emails;
    private static final String LN = System.getProperty("line.separator");

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmails() {
        return new HashSet<>(emails);
    }

    public User mergeUsers(User two) {
        User res = this;

        if (res != two) {
            Set<String> allEmails = new HashSet<>(this.emails);
            for (String email : two.emails) {
                if (!allEmails.contains(email)) {
                    allEmails.add(email);
                }
            }
            res = new User(this.name, allEmails);
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(name);
        res.append("->");
        emails.stream().forEach(s->{res.append(s).append(",");});
        res.replace(res.length() - 1, res.length(), LN);
        return res.toString();
    }

}
