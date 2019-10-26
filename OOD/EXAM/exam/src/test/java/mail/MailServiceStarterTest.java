package mail;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class MailServiceStarterTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenCallStartMethodShouldGetUnitedEmails() {
        ByteArrayInputStream input = new ByteArrayInputStream(Joiner.on(LN).join("5 10", "user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru",
                "user2 ->foo@gmail.com,ups@pisem.net", "user3 ->xyz@pisem.net,vasya@pupkin.com",
                "user4 ->ups@pisem.net,aaa@bbb.ru", "user5 ->xyz@pisem.net", "").getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Console console = new SimpleConsole(new Scanner(input), new PrintStream(output));
        MailService mailService = new MailServiceBFSConsole(console);
        MailServiceStarter starter = new MailServiceStarter(mailService);

        starter.start();

        assertThat(output.toString()).isEqualTo(Joiner.on(LN).join("user1->aaa@bbb.ru,ups@pisem.net,lol@mail.ru,xxx@ya.ru,foo@gmail.com",
                "user5->vasya@pupkin.com,xyz@pisem.net", ""));
    }

}