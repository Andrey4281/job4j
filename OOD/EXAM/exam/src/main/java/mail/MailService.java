package mail;

import java.util.List;

public interface MailService {
    void readData();
    List<User> calculate();
    void writeData(List<User> users);
}
