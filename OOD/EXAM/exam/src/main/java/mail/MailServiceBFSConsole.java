package mail;

import java.util.*;

public class MailServiceBFSConsole extends AbstractMailServiceBFS {

    public MailServiceBFSConsole(Console console) {
        super(console);
    }

    @Override
    public void readData() {
        int n, m;
        String input;
        String[] ss;
        input = console.intput();
        ss = input.split("\\s");
        n = Integer.parseInt(ss[0]);
        m = Integer.parseInt(ss[1]);

        mapUserNameToUser = new HashMap<>((int)((n / 0.75) + 1));
        mapEmailToUserName = new HashMap<>((int)((m / 0.75) + 1));

        for (int i = 0; i <n; i++) {
            input = console.intput();
            ss = input.split("(\\s*->\\s*|\\s*,\\s*)");
            Set<String> emails = new HashSet<>();
            for (int j = 1; j < ss.length; j++) {
                emails.add(ss[j]);
                if (mapEmailToUserName.get(ss[j]) == null) {
                    mapEmailToUserName.put(ss[j], new HashSet<>());
                }
                mapEmailToUserName.get(ss[j]).add(ss[0]);
            }
            mapUserNameToUser.put(ss[0], new User(ss[0], emails));
        }
    }

    @Override
    public void writeData(List<User> users) {
        users.stream().forEach(user->console.output(user.toString()));
    }
}
